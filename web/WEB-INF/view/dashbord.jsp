<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Advisor - Admin Dashboard</title>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
<style>
  * { margin: 0; padding: 0; box-sizing: border-box; }
  body {
    font-family: 'Poppins', sans-serif;
    background: #ebebed;
    min-height: 100vh;
    display: flex;
    align-items: center;
    justify-content: center;
    padding: 20px;
  }
  .dashboard {
    width: 1100px; height: 700px;
    background: #fff;
    border-radius: 20px;
    overflow: hidden;
    display: flex;
    box-shadow: 0 20px 60px rgba(0,0,0,0.15);
  }
  .sidebar {
    width: 210px;
    background: linear-gradient(180deg, #1a3a8f 0%, #1e3a8a 100%);
    display: flex; flex-direction: column; align-items: center;
    padding: 28px 0;
    flex-shrink: 0;
    border-radius: 20px 0 0 20px;
  }
  .logo-wrap {
    width: 120px; height: 120px;
    background: #fff;
    border-radius: 50%;
    display: flex; align-items: center; justify-content: center;
    margin-bottom: 36px;
    box-shadow: 0 4px 20px rgba(0,0,0,0.2);
    flex-shrink: 0; overflow: hidden;
  }
  .logo-wrap img { width: 100%; height: 100%; object-fit: contain; }
  nav { width: 100%; padding: 0 14px; display: flex; flex-direction: column; gap: 4px; }
  .nav-item {
    display: flex; align-items: center; gap: 10px;
    padding: 11px 16px; border-radius: 10px;
    color: rgba(255,255,255,0.8);
    font-size: 13px; font-weight: 500;
    cursor: pointer; text-decoration: none;
    transition: all 0.2s;
  }
  .nav-item:hover { background: rgba(255,255,255,0.12); color: #fff; }
  .nav-item.active { background: #fff; color: #1e3a8a; font-weight: 600; }
  .nav-item svg { width: 18px; height: 18px; flex-shrink: 0; }
  .main { flex: 1; display: flex; flex-direction: column; background: #ebebed; min-width: 0; }
  .header {
    background: #0f1f5c; padding: 16px 28px;
    display: flex; justify-content: flex-end; align-items: center; gap: 12px;
  }
  .user-info .name { color: #fff; font-size: 14px; font-weight: 600; text-align: right; }
  .user-info .role { color: rgba(255,255,255,0.55); font-size: 11px; text-align: right; }
  .avatar {
    width: 42px; height: 42px; border-radius: 50%;
    border: 2px solid rgba(255,255,255,0.3);
    display: flex; align-items: center; justify-content: center;
    background: rgba(255,255,255,0.1);
  }
  .avatar svg { width: 24px; color: #fff; }
  .content { flex: 1; padding: 20px 24px; display: flex; flex-direction: column; gap: 16px; }
  .stats-row { display: grid; grid-template-columns: repeat(3, 1fr); gap: 16px; flex-shrink: 0; }
  .stat-card {
    background: #fff; border-radius: 14px;
    padding: 16px 20px; display: flex; align-items: center; gap: 14px;
    box-shadow: 0 2px 10px rgba(0,0,0,0.06);
  }
  .stat-img-placeholder {
    width: 70px; height: 70px; border-radius: 10px;
    background: #f0f4ff; border: 2px dashed #c7d7ff;
    flex-shrink: 0; overflow: hidden;
    display: flex; align-items: center; justify-content: center;
  }
  .stat-img-placeholder img { width: 100%; height: 100%; object-fit: contain; }
  .stat-text .label { font-size: 12px; color: #64748b; font-weight: 500; margin-bottom: 2px; }
  .stat-text .value { font-size: 34px; font-weight: 700; color: #1e293b; line-height: 1; }
  .bottom-row { display: grid; grid-template-columns: 190px 1fr; gap: 16px; flex: 1; }
  .donuts-col { display: flex; flex-direction: column; gap: 14px; }
  .donut-card {
    background: #fff; border-radius: 14px;
    padding: 14px 16px; box-shadow: 0 2px 10px rgba(0,0,0,0.06);
    flex: 1; display: flex; flex-direction: column; align-items: flex-start;
  }
  .donut-badge {
    background: #2563eb; color: #fff;
    font-size: 10px; font-weight: 600;
    padding: 3px 11px; border-radius: 20px;
    margin-bottom: 10px; white-space: nowrap;
  }
  .donut-body { width: 100%; display: flex; justify-content: center; }
  .donut-wrap { position: relative; width: 100px; height: 100px; }
  .donut-wrap svg { width: 100px; height: 100px; transform: rotate(-90deg); }
  .donut-center {
    position: absolute; inset: 0;
    display: flex; align-items: center; justify-content: center;
    font-size: 21px; font-weight: 700; color: #1e293b;
  }
  .chart-card {
    background: #fff; border-radius: 14px;
    padding: 18px 20px; box-shadow: 0 2px 10px rgba(0,0,0,0.06);
    display: flex; flex-direction: column;
  }
  .chart-legend { display: flex; gap: 18px; margin-bottom: 12px; }
  .legend-item { display: flex; align-items: center; gap: 6px; font-size: 11px; color: #64748b; font-weight: 500; }
  .legend-dot { width: 9px; height: 9px; border-radius: 50%; }
  .chart-area { flex: 1; min-height: 0; position: relative; }
  .chart-area canvas { width: 100% !important; height: 100% !important; }
</style>
</head>
<body>
<div class="dashboard">

  <aside class="sidebar">
    <div class="logo-wrap">
      <img src="asset/image 11.png" alt="Logo Advisor">
    </div>
    <nav>
      <a class="nav-item active" href="dashboard.jsp">
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
      <a class="nav-item" href="projets.jsp">
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
          <div class="stat-img-placeholder">
            <img src="asset/Task management, Kanban board interface with user icon and check mark, Project progress tracker.png" alt="">
          </div>
          <div class="stat-text">
            <div class="label">Projet disponible</div>
            <div class="value">22</div>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-img-placeholder">
            <img src="asset/User identification card symbolizing account profile and secure authentication.png" alt="">
          </div>
          <div class="stat-text">
            <div class="label">Nombre d'utilisateur</div>
            <div class="value">803</div>
          </div>
        </div>
        <div class="stat-card">
          <div class="stat-img-placeholder">
            <img src="asset/Heart in frame with orbit ring, Social media like, Content likes overview.png" alt="">
          </div>
          <div class="stat-text">
            <div class="label">Projets les populaire</div>
            <div class="value">57</div>
          </div>
        </div>
      </div>

      <div class="bottom-row">
        <div class="donuts-col">
          <div class="donut-card">
            <div class="donut-badge">Projet Terminé</div>
            <div class="donut-body">
              <div class="donut-wrap">
                <svg viewBox="0 0 36 36">
                  <circle cx="18" cy="18" r="14" fill="none" stroke="#e2e8f0" stroke-width="4"/>
                  <circle cx="18" cy="18" r="14" fill="none" stroke="#2563eb" stroke-width="4"
                    stroke-dasharray="70.4 17.6" stroke-linecap="round"/>
                </svg>
                <div class="donut-center">80%</div>
              </div>
            </div>
          </div>
          <div class="donut-card">
            <div class="donut-badge">Projet en cour</div>
            <div class="donut-body">
              <div class="donut-wrap">
                <svg viewBox="0 0 36 36">
                  <circle cx="18" cy="18" r="14" fill="none" stroke="#e2e8f0" stroke-width="4"/>
                  <circle cx="18" cy="18" r="14" fill="none" stroke="#2563eb" stroke-width="4"
                    stroke-dasharray="45.8 42.2" stroke-linecap="round"/>
                </svg>
                <div class="donut-center">52%</div>
              </div>
            </div>
          </div>
        </div>

        <div class="chart-card">
          <div class="chart-legend">
            <div class="legend-item">
              <div class="legend-dot" style="background:#2563eb;"></div>
              Utilisateur en cour de projet
            </div>
            <div class="legend-item">
              <div class="legend-dot" style="background:#93c5fd;"></div>
              Tendance
            </div>
          </div>
          <div class="chart-area">
            <canvas id="barChart"></canvas>
          </div>
        </div>
      </div>
    </div>
  </main>
</div>

<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/4.4.0/chart.umd.min.js"></script>
<script>
const ctx = document.getElementById('barChart').getContext('2d');
new Chart(ctx, {
  data: {
    labels: ['Janvier','Fevrier','Mars','Avril','Mai','Juin'],
    datasets: [
      {
        type: 'bar', label: 'Utilisateurs',
        data: [80, 210, 95, 110, 230, 195],
        backgroundColor: '#2563eb', borderRadius: 5,
        borderSkipped: false, barPercentage: 0.5, order: 2,
      },
      {
        type: 'line', label: 'Tendance',
        data: [200, 222, 190, 160, 260, 285],
        borderColor: '#93c5fd', backgroundColor: 'transparent',
        borderWidth: 2, pointBackgroundColor: '#2563eb',
        pointRadius: 4, tension: 0.4, order: 1,
      }
    ]
  },
  options: {
    responsive: true, maintainAspectRatio: false,
    plugins: { legend: { display: false } },
    scales: {
      x: { grid: { display: false }, ticks: { font: { family: 'Poppins', size: 10 }, color: '#94a3b8' } },
      y: { grid: { color: '#f1f5f9' }, ticks: { font: { family: 'Poppins', size: 10 }, color: '#94a3b8', stepSize: 50 }, beginAtZero: true, max: 310 }
    }
  }
});
</script>
</body>
</html>
