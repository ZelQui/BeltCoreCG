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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>


<body>
<div class="content-container">
    <h1>Gestión de Roles</h1>

    <button class="add-provider" onclick="openModal('addRolModal')">Agregar Rol</button>

    <div class="filter-container">
        <div class="search-container">
            <input type="text" class="search-provider" id="buscarRolInput" placeholder="Buscar rol por nombre...">
            <button class="btn primary" id="aplicarFiltroRol">Buscar</button>
        </div>
    </div>

    <div class="table-container">
        <table class="user-table">
            <thead>
            <tr>
                <th>#</th>
                <th>Nombre</th>
                <th>Descripción</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody id="tablaRoles">
            <% int i = 1; for (Rol rol : roles) { %>
            <tr data-nombre="<%= rol.getNombreRol().toLowerCase() %>">
                <td><%= i++ %></td>
                <td><%= rol.getNombreRol() %></td>
                <td><%= rol.getDescripcion() %></td>
                <td>
                    <button class="btn edit" onclick="openEditModal(1, 'Administrador', 'Rol con todos los permisos')">
                        <i class="fas fa-edit"></i>
                    </button>

                    <button class="btn assign" onclick="redirigirAsignacion(<%= rol.getIdRol() %>)" title="Asignar permisos">
                        <i class="fas fa-tasks"></i>
                    </button>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<!-- MODAL AGREGAR ROL -->
<div id="addRolModal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <h2>Nuevo Rol</h2>
            <span class="close" onclick="closeModal('addRolModal')">&times;</span>
        </div>
        <div class="modal-body">
            <form action="<%=request.getContextPath()%>/rol" method="post">
                <input type="hidden" name="accion" value="Registrar">
                <div class="form-row">
                    <div class="form-group">
                        <label for="nombreRol">Nombre</label>
                        <input type="text" id="nombreRol" name="nombre_rol" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group">
                        <label for="descripcionRol">Descripción</label>
                        <input type="text" id="descripcionRol" name="descripcion" required>
                    </div>
                </div>
                <div class="form-actions">
                    <button type="button" class="btn cancel" onclick="closeModal('addRolModal')">Cancelar</button>
                    <button type="submit" class="btn primary">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Modal para editar rol -->
<div id="roleModal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <h2>Editar Rol</h2>
            <span class="close" onclick="cerrarModal()">&times;</span>
        </div>
        <div class="modal-body">
            <form id="roleForm">
                <input type="hidden" id="roleId" name="roleId">

                <div class="form-group">
                    <label for="roleName">Nombre del Rol</label>
                    <input type="text" id="roleName" name="roleName" required>
                </div>

                <div class="form-group">
                    <label for="roleDescription">Descripción</label>
                    <textarea id="roleDescription" name="roleDescription" rows="3" required></textarea>
                </div>

                <!-- Botones -->
                <div class="form-actions">
                    <button type="button" class="btn cancel" onclick="cerrarModal()">Cancelar</button>
                    <button type="submit" class="btn primary">Guardar</button>
                </div>
            </form>
        </div>
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

<script src="../assets/js/roles.js"></script>

</body>
</html>
