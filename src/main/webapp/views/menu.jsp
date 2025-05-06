<%@ page import="development.team.Models.Modulo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>FinFlow Dashboard</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>
    <link rel="stylesheet" href="../assets/css/menu.css" />
  </head>
  <body>
    <!-- Sidebar -->
    <div class="sidebar">
      <div class="logo">
        <img src="../assets/img/LogoEmpresa.png" alt="Logo Empresa" style="width: 150px; height: auto;" />
      </div>

      <div class="nav-menu">
        <%-- Lista de módulos: modulos_usuario --%>
        <%
          List<Modulo> modulos = (List<Modulo>) session.getAttribute("modulos_usuario");
          if (modulos != null) {
            for (Modulo m : modulos) {
                // Mostrar solo los módulos principales (cuando moduloPadreId es null)
                if (m.getModuloPadreId() == null) {
        %>
        <a href="#" class="nav-item" onclick="cargarPagina('<%= m.getRuta() %>')">
          <i class="<%= m.getIcono() %>"></i>
          <span><%= m.getNombre() %></span>
        </a>
        <%
                }
            }
        }
        %>
<%--        <a href="#" class="nav-item active">
          <i class="fas fa-home"></i>
          <span>Inicio</span>
        </a>
        <a href="#" class="nav-item">
          <i class="fas fa-user-circle"></i>
          <span>Cuenta</span>
        </a>
        <a href="#" class="nav-item">
          <i class="fas fa-shopping-cart"></i>
          <span>Ventas</span>
        </a>
        <a href="#" class="nav-item">
          <i class="fas fa-users"></i>
          <span>Clientes</span>
        </a>
        <a href="#" class="nav-item">
          <i class="fas fa-box-open"></i>
          <span>Compras</span>
        </a>
        <a href="#" class="nav-item" onclick="cargarPagina('proveedores.jsp')">
          <i class="fas fa-dolly"></i>
          <span>Proveedores</span>
        </a>
        <a href="#" class="nav-item">
          <i class="fas fa-money-check-alt"></i>
          <span>Pagos y Créditos</span>
        </a>
        <a href="#" class="nav-item">
          <i class="fas fa-chart-line"></i>
          <span>Reportes</span>
        </a>
        <a href="#" class="nav-item" onclick="cargarPagina('configuracion.jsp')">
          <i class="fas fa-cogs"></i>
          <span>Configuración</span>
        </a>--%>
        <a href="../views/index.html" class="nav-item logout">
          <i class="fas fa-sign-out-alt"></i>
          <span>Cerrar Sesión</span>
        </a>
      </div>
    </div>

    <div class="main-content" id="contenido"></div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe"
    crossorigin="anonymous"></script>
    <!--SCRIPT PARA LLAMAR A FUNCIONES DE CADA HTML-->
    <script src="../assets/js/script.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  </body>
</html>