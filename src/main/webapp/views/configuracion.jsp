<%@ page import="development.team.Models.Modulo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Configuración</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/configuracion.css" />
</head>
<body>
  <div class="config-container">
    <div class="card-grid">
      <%
        // Recuperamos de sesión la lista completa de módulos permitidos
        @SuppressWarnings("unchecked") //se usa para indicarle al compilador de Java que ignore (suprima) la advertencia
        List<Modulo> modulos = (List<Modulo>) session.getAttribute("modulos_usuario");
        // ID del módulo padre “Configuración”
        final int CONFIG_ID = 9;

        if (modulos != null) {
          for (Modulo m : modulos) {
            Integer padre = m.getModuloPadreId();
            // Mostramos solo los submódulos cuyo padre sea CONFIG_ID
            if (padre != null && padre == CONFIG_ID) {
      %>
      <div class="config-card" onclick="cargarPagina('<%= m.getRuta() %>')">
        <i class="<%= m.getIcono() %>"></i>
        <p><%= m.getNombre() %></p>
      </div>
      <%
          }
        }
      } else {
      %>
      <p>No hay módulos de configuración disponibles para tu rol.</p>
      <%
        }
      %>
    </div>
  </div>

    <%--<div class="card-grid">
      <div class="config-card" onclick="cargarPagina('usuarios.jsp')">
        <i class="fas fa-users-gear"></i>
        <p>Usuarios</p>
      </div>
      <div class="config-card" onclick="cargarPagina('productos.jsp')">
        <i class="fas fa-boxes"></i>
        <p>Productos / Categorías</p>
      </div>
      <div class="config-card" onclick="cargarPagina('insumos.jsp')">
        <i class="fas fa-cubes"></i>
        <p>Insumos</p>
      </div>
      <div class="config-card" onclick="cargarPagina('producciones.jsp')">
        <i class="fas fa-industry"></i>
        <p>Producciones</p>
      </div>
      <div class="config-card" onclick="cargarPagina('recetas.jsp')">
        <i class="fas fa-utensils"></i>
        <p>Recetas</p>
      </div>
      <div class="config-card" onclick="cargarPagina('creditos.jsp')">
        <i class="fas fa-credit-card"></i>
        <p>Máximo de Créditos</p>
      </div>
      <div class="config-card" onclick="cargarPagina('mermas.jsp')">
        <i class="fas fa-trash-alt"></i>
        <p>Control de Mermas</p>
      </div>
      <div class="config-card" onclick="cargarPagina('defectuosos.jsp')">
        <i class="fas fa-exclamation-triangle"></i>
        <p>Productos Defectuosos</p>
      </div>
      <div class="config-card" onclick="cargarPagina('metodos_pago.jsp')">
        <i class="fas fa-money-bill-wave"></i>
        <p>Métodos de Pago</p>
      </div>
      <div class="config-card" onclick="cargarPagina('roles.jsp')">
        <i class="fas fa-id-badge"></i>
        <p>Roles de Empleados</p>
      </div>
    </div>--%>
  </div>
</body>
</html>
