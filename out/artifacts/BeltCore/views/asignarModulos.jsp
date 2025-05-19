<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="development.team.Models.Modulo" %>
<%@ page import="development.team.Models.Rol" %>

<%
  Rol rol = (Rol) request.getAttribute("rolSeleccionado");
  List<Modulo> modulos = (List<Modulo>) request.getAttribute("modulos");
  List<Integer> modulosAsignados = (List<Integer>) request.getAttribute("modulosAsignados");
%>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Asignar Módulos</title>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/roles.css">
</head>
<body>


<div class="content-container">
  <h1>Asignar Módulos al Rol: <%= rol.getNombreRol() %></h1>

  <form action="<%=request.getContextPath()%>/GuardarAsignacionController" method="post">
    <input type="hidden" name="idRol" value="<%= rol.getIdRol() %>">

    <div class="table-container">
      <table class="user-table">
        <thead>
        <tr>
          <th>#</th>
          <th>Nombre del Módulo</th>
          <th>Asignar</th>
        </tr>
        </thead>
        <tbody>
        <% int index = 1;
          for (Modulo modulo : modulos) {
            boolean asignado = modulosAsignados.contains(modulo.getIdModulo());
        %>
        <tr>
          <td><%= index++ %></td>
          <td><%= modulo.getNombre() %></td>
          <td>
            <label class="toggle-switch">
              <input type="checkbox"
                     id="modulo_<%= modulo.getIdModulo() %>"
                     name="modulos"
                     value="<%= modulo.getIdModulo() %>"
                <%= asignado ? "checked" : "" %>>
              <span class="slider"></span>
            </label>
          </td>
        </tr>
        <% } %>
        </tbody>
      </table>
    </div>

    <div class="form-actions">
      <button type="button" class="btn cancel" onclick="window.location.href='<%=request.getContextPath()%>/app/roles'">Volver</button>
      <button type="submit" class="btn primary">Guardar Asignación</button>
    </div>
  </form>
</div>


<script>
  history.replaceState({}, "", "<%=request.getContextPath()%>/app/asignarModulos");
</script>
</body>
</html>
