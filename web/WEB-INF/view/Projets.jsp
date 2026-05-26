<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Advisor - Tout les projets</title>
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
  .accordion-list { display: flex; flex-direction: column; gap: 12px; flex: 1; overflow-y: auto; }
  .accordion-item { background: #fff; border-radius: 12px; border: 1.5px solid #e2e8f0; overflow: hidden; box-shadow: 0 2px 8px rgba(0,0,0,0.04); transition: box-shadow 0.2s; }
  .accordion-item:hover { box-shadow: 0 4px 16px rgba(37,99,235,0.10); }
  .accordion-header { display: flex; align-items: center; justify-content: space-between; padding: 16px 20px; cursor: pointer; user-select: none; background: #fff; transition: background 0.15s; }
  .accordion-header:hover { background: #f8fafc; }
  .accordion-title-block { flex: 1; min-width: 0; }
  .accordion-title { font-size: 14px; font-weight: 600; color: #1e293b; margin-bottom: 4px; }
  .accordion-desc { font-size: 12px; color: #64748b; line-height: 1.5; white-space: nowrap; overflow: hidden; text-overflow: ellipsis; max-width: 560px; }
  .accordion-arrow { width: 30px; height: 30px; display: flex; align-items: center; justify-content: center; border-radius: 50%; background: #f1f5f9; flex-shrink: 0; margin-left: 12px; transition: transform 0.25s, background 0.2s; }
  .accordion-arrow svg { width: 16px; height: 16px; color: #475569; transition: transform 0.25s; }
  .accordion-item.open .accordion-arrow { background: #dbeafe; }
  .accordion-item.open .accordion-arrow svg { transform: rotate(180deg); color: #2563eb; }
  .accordion-body { display: none; padding: 12px 20px 16px; font-size: 13px; color: #475569; line-height: 1.7; border-top: 1px solid #f1f5f9; }
  .accordion-item.open .accordion-body { display: block; }
</style>
</head>
<body>
<div class="dashboard">

  <aside class="sidebar">
    <div class="logo-wrap">
      <img src="asset/image 11.png" alt="Logo Advisor">
    </div>
    <nav>
      <a class="nav-item" href="dashboard.jsp">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <rect x="3" y="3" width="7" height="7" rx="1"/><rect x="14" y="3" width="7" height="7" rx="1"/>
          <rect x="3" y="14" width="7" height="7" rx="1"/><rect x="14" y="14" width="7" height="7" rx="1"/>
        </svg>
        Accueil
      </a>
      <a class="nav-item" href="users.jsp">
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
      <a class="nav-item active" href="projets.jsp">
        <svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2">
          <rect x="2" y="3" width="20" height="14" rx="2"/>
          <path d="M8 21h8M12 17v4"/>
        </svg>
        Tout les projets
      </a>
    </nav>
  </aside>

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
      <div class="stats-row">
        <div class="stat-card">
          <div class="stat-img-placeholder"><img src="asset/Task management, Kanban board interface with user icon and check mark, Project progress tracker.png" alt=""></div>
          <div class="stat-text"><div class="label">Projet disponible</div><div class="value">22</div></div>
        </div>
        <div class="stat-card">
          <div class="stat-img-placeholder"><img src="asset/User identification card symbolizing account profile and secure authentication.png" alt=""></div>
          <div class="stat-text"><div class="label">Nombre d'utilisateur</div><div class="value">803</div></div>
        </div>
        <div class="stat-card">
          <div class="stat-img-placeholder"><img src="asset/Heart in frame with orbit ring, Social media like, Content likes overview.png" alt=""></div>
          <div class="stat-text"><div class="label">Projets les populaire</div><div class="value">57</div></div>
        </div>
      </div>

      <div class="accordion-list">
        <div class="accordion-item">
          <div class="accordion-header" onclick="toggleAccordion(this)">
            <div class="accordion-title-block">
              <div class="accordion-title">Elévage de poulets</div>
              <div class="accordion-desc">L'élevage de poulets consiste à produire des volailles pour la consommation de viande ou la production d'œufs.</div>
            </div>
            <div class="accordion-arrow"><svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><polyline points="6 9 12 15 18 9"/></svg></div>
          </div>
          <div class="accordion-body">L'élevage de poulets consiste à produire des volailles pour la consommation de viande ou la production d'œufs. Ce projet inclut la gestion des poulaillers, l'alimentation, la santé animale et la commercialisation des produits.</div>
        </div>
        <div class="accordion-item">
          <div class="accordion-header" onclick="toggleAccordion(this)">
            <div class="accordion-title-block">
              <div class="accordion-title">Culture de tomates</div>
              <div class="accordion-desc">Le projet de culture de tomates consiste à produire des tomates destinées à la consommation locale, à la transformation industrielle ou à la commercialisation sur l...</div>
            </div>
            <div class="accordion-arrow"><svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><polyline points="6 9 12 15 18 9"/></svg></div>
          </div>
          <div class="accordion-body">Le projet de culture de tomates consiste à produire des tomates destinées à la consommation locale, à la transformation industrielle ou à la commercialisation sur les marchés régionaux et internationaux.</div>
        </div>
        <div class="accordion-item">
          <div class="accordion-header" onclick="toggleAccordion(this)">
            <div class="accordion-title-block">
              <div class="accordion-title">Elévage de poulets</div>
              <div class="accordion-desc">L'élevage de poulets consiste à produire des volailles pour la consommation de viande ou la production d'œufs.</div>
            </div>
            <div class="accordion-arrow"><svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><polyline points="6 9 12 15 18 9"/></svg></div>
          </div>
          <div class="accordion-body">L'élevage de poulets consiste à produire des volailles pour la consommation de viande ou la production d'œufs. Ce projet inclut la gestion des poulaillers, l'alimentation, la santé animale et la commercialisation des produits.</div>
        </div>
        <div class="accordion-item">
          <div class="accordion-header" onclick="toggleAccordion(this)">
            <div class="accordion-title-block">
              <div class="accordion-title">Culture de tomates</div>
              <div class="accordion-desc">Le projet de culture de tomates consiste à produire des tomates destinées à la consommation locale, à la transformation industrielle ou à la commercialisation sur l...</div>
            </div>
            <div class="accordion-arrow"><svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><polyline points="6 9 12 15 18 9"/></svg></div>
          </div>
          <div class="accordion-body">Le projet de culture de tomates consiste à produire des tomates destinées à la consommation locale, à la transformation industrielle ou à la commercialisation sur les marchés régionaux et internationaux.</div>
        </div>
      </div>
    </div>
  </main>
</div>
<script>
  function toggleAccordion(header) {
    const item = header.closest('.accordion-item');
    const isOpen = item.classList.contains('open');
    document.querySelectorAll('.accordion-item').forEach(i => i.classList.remove('open'));
    if (!isOpen) item.classList.add('open');
  }
</script>
</body>
</html>
