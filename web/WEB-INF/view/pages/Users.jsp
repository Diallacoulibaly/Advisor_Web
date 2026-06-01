<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.java.model.classes.Utilisateur" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Advisor - Liste des Users</title>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
<style>
  * { margin: 0; padding: 0; box-sizing: border-box; }
  body { font-family: 'Poppins', sans-serif; background: #ebebed; min-height: 100vh; display: flex; align-items: center; justify-content: center; padding: 20px; }
  .dashboard { width: 1100px; height: 700px; background: #fff; border-radius: 20px; overflow: hidden; display: flex; box-shadow: 0 20px 60px rgba(0,0,0,0.15); }
  .sidebar { width: 210px; background: linear-gradient(180deg, #1a3a8f 0%, #1e3a8a 100%); display: flex; flex-direction: column; align-items: center; padding: 28px 0; flex-shrink: 0; border-radius: 20px 0 0 20px; }
  .logo-wrap { width: 120px; height: 120px; background: #fff; border-radius: 50%; display: flex; align-items: center; justify-content: center; margin-bottom: 36px; box-shadow: 0 4px 20px rgba(0,0,0,0.2); flex-shrink: 0; overflow: hidden; }
  .logo-wrap img { width: 100%; height: 100%; object-fit: contain; }
  nav { width: 100%; padding: 0 14px; display: flex; flex-direction: column; gap: 4px; }
  .nav-item { display: flex; align-items: center; gap: 10px; padding: 11px 16px; border-radius: 10px; color: rgba(255,255,255,0.8); font-size: 13px; font-weight: 500; cursor: pointer; text-decoration: none; transition: all 0.2s; }
  .nav-item:hover { background: rgba(255,255,255,0.12); color: #fff; }
  .nav-item.active { background: #fff; color: #1e3a8a; font-weight: 600; }
  .nav-item svg { width: 18px; height: 18px; flex-shrink: 0; }
  .main { flex: 1; display: flex; flex-direction: column; background: #ebebed; min-width: 0; }
  .header { background: #0f1f5c; padding: 16px 28px; display: flex; justify-content: flex-end; align-items: center; gap: 12px; }
  .user-info .name { color: #fff; font-size: 14px; font-weight: 600; text-align: right; }
  .user-info .role { color: rgba(255,255,255,0.55); font-size: 11px; text-align: right; }
  .avatar { width: 42px; height: 42px; border-radius: 50%; border: 2px solid rgba(255,255,255,0.3); display: flex; align-items: center; justify-content: center; background: rgba(255,255,255,0.1); }
  .avatar svg { width: 24px; color: #fff; }
  .content { flex: 1; padding: 20px 24px; display: flex; flex-direction: column; gap: 16px; overflow: hidden; }
  .stats-row { display: grid; grid-template-columns: repeat(3, 1fr); gap: 16px; flex-shrink: 0; }
  .stat-card { background: #fff; border-radius: 14px; padding: 16px 20px; display: flex; align-items: center; gap: 14px; box-shadow: 0 2px 10px rgba(0,0,0,0.06); }
  .stat-img-placeholder { width: 70px; height: 70px; border-radius: 10px; background: #f0f4ff; border: 2px dashed #c7d7ff; flex-shrink: 0; overflow: hidden; display: flex; align-items: center; justify-content: center; }
  .stat-img-placeholder img { width: 100%; height: 100%; object-fit: contain; }
  .stat-text .label { font-size: 12px; color: #64748b; font-weight: 500; margin-bottom: 2px; }
  .stat-text .value { font-size: 34px; font-weight: 700; color: #1e293b; line-height: 1; }
  .table-section { background: #fff; border-radius: 14px; box-shadow: 0 2px 10px rgba(0,0,0,0.06); flex: 1; display: flex; flex-direction: column; overflow: hidden; }
  .table-header { display: flex; justify-content: flex-end; padding: 14px 18px; border-bottom: 1px solid #f1f5f9; flex-shrink: 0; }
  .btn-new-user { display: flex; align-items: center; gap: 8px; background: #2563eb; color: #fff; border: none; border-radius: 8px; padding: 8px 18px; font-size: 13px; font-weight: 600; font-family: 'Poppins', sans-serif; cursor: pointer; transition: background 0.2s; }
  .btn-new-user:hover { background: #1d4ed8; }
  .btn-new-user svg { width: 16px; }
  .table-wrap { flex: 1; overflow-y: auto; }
  table { width: 100%; border-collapse: collapse; }
  thead th { background: #f8fafc; padding: 10px 16px; font-size: 12px; font-weight: 600; color: #475569; text-align: left; border-bottom: 1px solid #e2e8f0; position: sticky; top: 0; z-index: 1; }
  .sort-icon { color: #94a3b8; font-size: 10px; }
  tbody tr { border-bottom: 1px solid #f1f5f9; transition: background 0.15s; }
  tbody tr:hover { background: #f8fafc; }
  tbody td { padding: 11px 16px; font-size: 13px; color: #334155; vertical-align: middle; }
  .td-id { color: #64748b; font-size: 12px; }
  .td-name { display: flex; align-items: center; gap: 8px; }
  .td-name svg { width: 18px; color: #94a3b8; flex-shrink: 0; }
  .td-role { display: inline-block; font-size: 12px; font-weight: 500; color: #334155; }
  .td-role.ADMIN { color: #dc2626; font-weight: 600; }
  .td-role.MAITRE { color: #7c3aed; font-weight: 600; }
  .td-role.CLIENT { color: #334155; }
  .action-btn { background: none; border: none; cursor: pointer; color: #94a3b8; padding: 4px; border-radius: 4px; font-size: 18px; line-height: 1; transition: color 0.2s; }
  .action-btn:hover { color: #475569; }
  .dropdown { position: absolute; right: 0; top: 28px; background: #fff; border-radius: 8px; box-shadow: 0 4px 20px rgba(0,0,0,0.12); z-index: 10; min-width: 110px; overflow: hidden; display: none; }
  .dropdown.open { display: block; }
  .dropdown-item { padding: 9px 16px; font-size: 12px; font-weight: 500; cursor: pointer; transition: background 0.15s; text-decoration: none; display: block; }
  .dropdown-item:hover { background: #f1f5f9; }
  .dropdown-item.delete { color: #ef4444; }
  .dropdown-item.edit { color: #334155; }
  .action-cell { position: relative; }

  /* Modal */
  .modal-overlay { display: none; position: fixed; inset: 0; background: rgba(0,0,0,0.4); z-index: 100; align-items: center; justify-content: center; }
  .modal-overlay.open { display: flex; }
  .modal { background: #fff; border-radius: 16px; padding: 28px; width: 440px; box-shadow: 0 20px 60px rgba(0,0,0,0.2); }
  .modal h2 { font-size: 16px; font-weight: 700; color: #1e293b; margin-bottom: 20px; }
  .form-group { margin-bottom: 14px; }
  .form-group label { display: block; font-size: 12px; font-weight: 600; color: #475569; margin-bottom: 5px; }
  .form-group input, .form-group select { width: 100%; padding: 9px 12px; border: 1.5px solid #e2e8f0; border-radius: 8px; font-size: 13px; font-family: 'Poppins', sans-serif; color: #1e293b; outline: none; transition: border-color 0.2s; }
  .form-group input:focus, .form-group select:focus { border-color: #2563eb; }
  .form-row { display: grid; grid-template-columns: 1fr 1fr; gap: 12px; }
  .modal-actions { display: flex; justify-content: flex-end; gap: 10px; margin-top: 20px; }
  .btn-cancel { background: #f1f5f9; color: #475569; border: none; border-radius: 8px; padding: 9px 20px; font-size: 13px; font-weight: 600; cursor: pointer; font-family: 'Poppins', sans-serif; }
  .btn-submit { background: #2563eb; color: #fff; border: none; border-radius: 8px; padding: 9px 20px; font-size: 13px; font-weight: 600; cursor: pointer; font-family: 'Poppins', sans-serif; }
  .btn-submit:hover { background: #1d4ed8; }
</style>
</head>
<body>

<%
    // Récupérer la liste des utilisateurs passée par le Servlet
    List<Utilisateur> utilisateurs = (List<Utilisateur>) request.getAttribute("utilisateurs");
    if (utilisateurs == null) utilisateurs = new java.util.ArrayList<>();

    // Compter par rôle pour les stats
    long totalUsers = utilisateurs.size();
%>

<div class="dashboard">

  <!-- SIDEBAR -->
  <aside class="sidebar">
    <div class="logo-wrap">
      <img src="image 11.png" alt="Logo Advisor">
    </div>
    <nav>
      <a class="nav-item" href="dashboard.jsp">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <rect x="3" y="3" width="7" height="7" rx="1"/><rect x="14" y="3" width="7" height="7" rx="1"/>
          <rect x="3" y="14" width="7" height="7" rx="1"/><rect x="14" y="14" width="7" height="7" rx="1"/>
        </svg>
        Accueil
      </a>
      <a class="nav-item active" href="<%= request.getContextPath() %>/utilisateurs?action=liste">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/>
          <circle cx="9" cy="7" r="4"/>
          <path d="M23 21v-2a4 4 0 0 0-3-3.87"/><path d="M16 3.13a4 4 0 0 1 0 7.75"/>
        </svg>
        Liste des Users
      </a>
      <a class="nav-item" href="historiques.jsp">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <path d="M12 20h9"/>
          <path d="M16.5 3.5a2.121 2.121 0 0 1 3 3L7 19l-4 1 1-4L16.5 3.5z"/>
        </svg>
        Historiques
      </a>
      <a class="nav-item" href="projets.jsp">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <rect x="2" y="3" width="20" height="14" rx="2"/>
          <path d="M8 21h8M12 17v4"/>
        </svg>
        Tout les projets
      </a>
    </nav>
  </aside>

  <!-- MAIN -->
  <main class="main">
    <div class="header">
      <div class="user-info">
        <div class="name">Moussa Diarra</div>
        <div class="role">Admin</div>
      </div>
      <div class="avatar">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8">
          <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/>
          <circle cx="12" cy="7" r="4"/>
        </svg>
      </div>
    </div>

    <div class="content">

      <!-- Stats dynamiques -->
      <div class="stats-row">
        <div class="stat-card">
          <div class="stat-img-placeholder"><img src="Task management, Kanban board interface with user icon and check mark, Project progress tracker.png" alt=""></div>
          <div class="stat-text">
            <div class="label">Projet disponible</div>
            <div class="value">22</div>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-img-placeholder"><img src="User identification card symbolizing account profile and secure authentication.png" alt=""></div>
          <div class="stat-text">
            <div class="label">Nombre d'utilisateur</div>
            <!-- Valeur dynamique depuis la BDD -->
            <div class="value"><%= totalUsers %></div>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-img-placeholder"><img src="Heart in frame with orbit ring, Social media like, Content likes overview.png" alt=""></div>
          <div class="stat-text">
            <div class="label">Projets les populaire</div>
            <div class="value">57</div>
          </div>
        </div>
      </div>

      <!-- Tableau dynamique -->
      <div class="table-section">
        <div class="table-header">
          <button class="btn-new-user" onclick="ouvrirModal('ajouter')">
            <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5">
              <path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/>
              <circle cx="9" cy="7" r="4"/>
              <line x1="19" y1="8" x2="19" y2="14"/><line x1="16" y1="11" x2="22" y2="11"/>
            </svg>
            Nouvel utilisateur
          </button>
        </div>

        <div class="table-wrap">
          <table>
            <thead>
              <tr>
                <th>Id <span class="sort-icon">▼</span></th>
                <th>Nom &amp; Prénom</th>
                <th>Email</th>
                <th>Téléphone</th>
                <th>Rôle</th>
                <th>Action</th>
              </tr>
            </thead>
            <tbody>
              <%
                for (Utilisateur u : utilisateurs) {
                    String roleClass = u.getRole() != null ? u.getRole().name() : "CLIENT";
              %>
              <tr>
                <td class="td-id">#<%= u.getIdUtilisateur() %></td>
                <td>
                  <div class="td-name">
                    <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.8">
                      <path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/>
                      <circle cx="12" cy="7" r="4"/>
                    </svg>
                    <%= u.getNom() %> <%= u.getPrenom() %>
                  </div>
                </td>
                <td><%= u.getEmail() %></td>
                <td><%= u.getTelephone() != null ? u.getTelephone() : "-" %></td>
                <td><span class="td-role <%= roleClass %>"><%= roleClass %></span></td>
                <td class="action-cell">
                  <button class="action-btn" onclick="toggleMenu(this)">&#8942;</button>
                  <div class="dropdown">
                    <!-- Supprimer via GET -->
                    <a class="dropdown-item delete"
                       href="<%= request.getContextPath() %>/utilisateurs?action=supprimer&id=<%= u.getIdUtilisateur() %>"
                       onclick="return confirm('Supprimer cet utilisateur ?')">
                      Supprimer
                    </a>
                    <!-- Modifier via modal -->
                    <a class="dropdown-item edit"
                       href="#"
                       onclick="ouvrirModalModifier(
                         <%= u.getIdUtilisateur() %>,
                         '<%= u.getNom() %>',
                         '<%= u.getPrenom() %>',
                         '<%= u.getEmail() %>',
                         '<%= u.getTelephone() != null ? u.getTelephone() : "" %>'
                       )">
                      Modifier
                    </a>
                  </div>
                </td>
              </tr>
              <% } %>

              <% if (utilisateurs.isEmpty()) { %>
              <tr>
                <td colspan="6" style="text-align:center; color:#94a3b8; padding: 30px;">
                  Aucun utilisateur trouvé.
                </td>
              </tr>
              <% } %>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </main>
</div>

<!-- ── MODAL AJOUTER / MODIFIER ───────────────────────────────── -->
<div class="modal-overlay" id="modalOverlay">
  <div class="modal">
    <h2 id="modalTitre">Nouvel utilisateur</h2>
    <form method="post" action="<%= request.getContextPath() %>/utilisateurs">
      <input type="hidden" name="action" id="formAction" value="ajouter">
      <input type="hidden" name="id"     id="formId"     value="">

      <div class="form-row">
        <div class="form-group">
          <label>Nom</label>
          <input type="text" name="nom" id="formNom" placeholder="Diarra" required>
        </div>
        <div class="form-group">
          <label>Prénom</label>
          <input type="text" name="prenom" id="formPrenom" placeholder="Moussa" required>
        </div>
      </div>

      <div class="form-group">
        <label>Email</label>
        <input type="email" name="email" id="formEmail" placeholder="exemple@mail.com" required>
      </div>

      <div class="form-row">
        <div class="form-group">
          <label>Téléphone</label>
          <input type="text" name="telephone" id="formTelephone" placeholder="+223 70000000">
        </div>
        <div class="form-group" id="roleGroup">
          <label>Rôle</label>
          <select name="role" id="formRole">
            <option value="CLIENT">Client</option>
            <option value="ADMIN">Admin</option>
          </select>
        </div>
      </div>

      <div class="form-group" id="mdpGroup">
        <label>Mot de passe</label>
        <input type="password" name="motDePasse" id="formMdp" placeholder="••••••••">
      </div>

      <div class="modal-actions">
        <button type="button" class="btn-cancel" onclick="fermerModal()">Annuler</button>
        <button type="submit" class="btn-submit" id="btnSubmit">Ajouter</button>
      </div>
    </form>
  </div>
</div>

<script>
  // ── Toggle menu action ──
  function toggleMenu(btn) {
    const dropdown = btn.nextElementSibling;
    const isOpen = dropdown.classList.contains('open');
    document.querySelectorAll('.dropdown').forEach(d => d.classList.remove('open'));
    if (!isOpen) dropdown.classList.add('open');
  }

  document.addEventListener('click', function(e) {
    if (!e.target.classList.contains('action-btn')) {
      document.querySelectorAll('.dropdown').forEach(d => d.classList.remove('open'));
    }
  });

  // ── Modal Ajouter ──
  function ouvrirModal(type) {
    document.getElementById('modalTitre').textContent = 'Nouvel utilisateur';
    document.getElementById('formAction').value = 'ajouter';
    document.getElementById('formId').value = '';
    document.getElementById('formNom').value = '';
    document.getElementById('formPrenom').value = '';
    document.getElementById('formEmail').value = '';
    document.getElementById('formTelephone').value = '';
    document.getElementById('mdpGroup').style.display = 'block';
    document.getElementById('roleGroup').style.display = 'block';
    document.getElementById('btnSubmit').textContent = 'Ajouter';
    document.getElementById('modalOverlay').classList.add('open');
  }

  // ── Modal Modifier ──
  function ouvrirModalModifier(id, nom, prenom, email, telephone) {
    document.getElementById('modalTitre').textContent = 'Modifier l\'utilisateur';
    document.getElementById('formAction').value = 'modifier';
    document.getElementById('formId').value = id;
    document.getElementById('formNom').value = nom;
    document.getElementById('formPrenom').value = prenom;
    document.getElementById('formEmail').value = email;
    document.getElementById('formTelephone').value = telephone;
    document.getElementById('mdpGroup').style.display = 'none';
    document.getElementById('roleGroup').style.display = 'none';
    document.getElementById('btnSubmit').textContent = 'Enregistrer';
    document.getElementById('modalOverlay').classList.add('open');
  }

  function fermerModal() {
    document.getElementById('modalOverlay').classList.remove('open');
  }

  // Fermer en cliquant en dehors
  document.getElementById('modalOverlay').addEventListener('click', function(e) {
    if (e.target === this) fermerModal();
  });
</script>
</body>
</html>
