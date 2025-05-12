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

<div class="container">
  <h2>Asignar Módulos al Rol: <%= rol.getNombreRol() %></h2>

  <form action="<%=request.getContextPath()%>/GuardarAsignacionController" method="post">
    <input type="hidden" name="idRol" value="<%= rol.getIdRol() %>">

    <div class="checkboxes-container">
      <% for (Modulo modulo : modulos) {
        boolean asignado = modulosAsignados.contains(modulo.getIdModulo());
      %>
      <div class="checkbox-item">
        <input type="checkbox"
               id="modulo_<%= modulo.getIdModulo() %>"
               name="modulos"
               value="<%= modulo.getIdModulo() %>"
          <%= asignado ? "checked" : "" %>>
        <label for="modulo_<%= modulo.getIdModulo() %>"><%= modulo.getNombre() %></label>
      </div>
      <% } %>
    </div>

    <div class="form-actions">
      <button type="submit" class="btn primary">Guardar Asignación</button>
    </div>
  </form>
</div>

<script>
  history.replaceState({}, "", "<%=request.getContextPath()%>/app/asignarModulos");
</script>
</body>
</html>
