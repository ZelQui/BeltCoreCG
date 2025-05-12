<%@ page import="development.team.DAO.RolDAO" %>
<%@ page import="development.team.Models.Rol" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Rol> roles = RolDAO.obtenerRols();
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Roles</title>
    <link rel="stylesheet" href="../assets/css/roles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>

<h2>Gestión de Roles</h2>
<button class="btn primary" onclick="openModal('addRolModal')">Agregar Rol</button>
<input type="text" class="search-input" placeholder="Buscar rol...">

<table class="styled-table">
    <thead>
    <tr><th>#</th><th>Nombre</th><th>Descripción</th><th>Acciones</th></tr>
    </thead>
    <tbody>
    <% int i = 1; for (Rol rol : roles) { %>
    <tr>
        <td><%= i++ %></td>
        <td><%= rol.getNombreRol() %></td>
        <td><%= rol.getDescripcion() %></td>
        <td>
            <button class="btn edit" onclick="editRol(<%= rol.getIdRol() %>)"><i class="fas fa-edit"></i></button>
            <button class="btn assign" onclick="redirigirAsignacion(<%= rol.getIdRol() %>)">
                <i class="fas fa-tasks"></i>
            </button>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>

<!-- MODAL AGREGAR ROL -->
<div id="addRolModal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <h2>Nuevo Rol</h2>
            <span class="close" onclick="closeModal('addRolModal')">&times;</span>
        </div>
        <form action="<%=request.getContextPath()%>/rol" method="post">
            <input type="hidden" name="accion" value="Registrar">
            <label>Nombre:</label>
            <input type="text" name="nombre_rol" required>
            <label>Descripción:</label>
            <input type="text" name="descripcion" required>
            <div class="form-actions">
                <button type="submit" class="btn primary">Guardar</button>
            </div>
        </form>
    </div>
</div>

<script>
    function openModal(id) {
        document.getElementById(id).style.display = 'block';
    }

    function closeModal(id) {
        document.getElementById(id).style.display = 'none';
    }

    function redirigirAsignacion(idRol) {
        window.location.href = '<%=request.getContextPath()%>/AsignacionModulosController?idRol=' + idRol;
    }
</script>

</body>
</html>
