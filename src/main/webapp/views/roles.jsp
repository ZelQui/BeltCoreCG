<%@ page import="development.team.DAO.ModuloDAO" %>
<%@ page import="development.team.DAO.RolDAO" %>
<%@ page import="development.team.Models.Modulo" %>
<%@ page import="development.team.Models.Rol" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<Rol> roles = RolDAO.obtenerRols();
    List<Modulo> modulos = ModuloDAO.obtenerModulos();
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Roles y Módulos</title>
    <link rel="stylesheet" href="../assets/css/roles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>

<div class="tabs">
    <button class="tab-button active" onclick="showTab('roles')">CRUD Roles</button>
    <button class="tab-button" onclick="showTab('modulos')">CRUD Módulos</button>
    <button class="tab-button" onclick="showTab('asignacion')">Asignar Módulos a Rol</button>
</div>

<!-- CRUD ROLES -->
<div id="roles" class="tab-content active">
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
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

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

<!-- CRUD MODULOS -->
<div id="modulos" class="tab-content">
    <h2>Gestión de Módulos</h2>
    <button class="btn primary" onclick="openModal('addModuloModal')">Agregar Módulo</button>
    <input type="text" class="search-input" placeholder="Buscar módulo...">

    <table class="styled-table">
        <thead>
        <tr><th>#</th><th>Nombre</th><th>Ruta</th><th>Icono</th><th>Padre</th><th>Acciones</th></tr>
        </thead>
        <tbody>
        <% i = 1; for (Modulo modulo : modulos) { %>
        <tr>
            <td><%= i++ %></td>
            <td><%= modulo.getNombre() %></td>
            <td><%= modulo.getRuta() %></td>
            <td><%= modulo.getIcono() %></td>
            <td><%= modulo.getModuloPadreId() %></td>
            <td>
                <button class="btn edit" onclick="editModulo(<%= modulo.getIdModulo() %>)"><i class="fas fa-edit"></i></button>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>

<!-- MODAL AGREGAR MODULO -->
<div id="addModuloModal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <h2>Nuevo Módulo</h2>
            <span class="close" onclick="closeModal('addModuloModal')">&times;</span>
        </div>
        <form action="<%=request.getContextPath()%>/modulo" method="post">
            <input type="hidden" name="accion" value="Registrar">
            <label>Nombre:</label>
            <input type="text" name="nombre_modulo" required>
            <label>Ruta:</label>
            <input type="text" name="ruta" required>
            <label>Icono:</label>
            <input type="text" name="icono">
            <label>Módulo Padre:</label>
            <select name="modulo_padre_id">
                <option value="">Ninguno</option>
                <% for (Modulo m : modulos) { %>
                <option value="<%= m.getIdModulo() %>"><%= m.getNombre() %></option>
                <% } %>
            </select>
            <div class="form-actions">
                <button type="submit" class="btn primary">Guardar</button>
            </div>
        </form>
    </div>
</div>

<!-- ASIGNACIÓN DE MÓDULOS A ROLES -->
<div id="asignacion" class="tab-content">
    <h2>Asignar Módulos a Rol</h2>
    <div class="form-row">
        <label for="selectRol">Selecciona un rol:</label>
        <select id="selectRol">
            <% for (Rol rol : roles) { %>
            <option value="<%= rol.getIdRol() %>"><%= rol.getNombreRol() %></option>
            <% } %>
        </select>
    </div>
    <div class="checkboxes-container">
        <% for (Modulo modulo : modulos) { %>
        <div class="checkbox-item">
            <input type="checkbox" id="modulo_<%= modulo.getIdModulo() %>" name="modulos" value="<%= modulo.getIdModulo() %>">
            <label for="modulo_<%= modulo.getIdModulo() %>"><%= modulo.getNombre() %></label>
        </div>
        <% } %>
    </div>
    <div class="form-actions">
        <button class="btn primary">Guardar Asignación</button>
    </div>
</div>

<script src="../assets/js/gestionRolesModulos.js"></script>
<script>
    function showTab(id) {
        document.querySelectorAll('.tab-content').forEach(t => t.classList.remove('active'));
        document.querySelectorAll('.tab-button').forEach(b => b.classList.remove('active'));
        document.getElementById(id).classList.add('active');
        event.target.classList.add('active');
    }

    function openModal(id) {
        document.getElementById(id).style.display = 'block';
    }

    function closeModal(id) {
        document.getElementById(id).style.display = 'none';
    }
</script>

</body>
</html>