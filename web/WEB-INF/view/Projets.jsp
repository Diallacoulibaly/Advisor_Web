<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<body>
<<<<<<< HEAD
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
=======
    <h1>Liste des Projets</h1>
    <table border="1">
        <tr>
            <th>Titre</th><th>Niveau</th><th>Budget Max</th>
        </tr>
        <c:forEach var="p" items="${projets}">
            <tr>
                <td>${p.titre}</td>
                <td>${p.niveau}</td>
                <td>${p.budgetMax} FCFA</td>
            </tr>
        </c:forEach>
    </table>
>>>>>>> b348432cb7a3c523453e88c6a84585632ac0126e
</body>
</html>