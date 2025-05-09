<%@ page import="development.team.DAO.UsuarioDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="development.team.DTO.UsuarioRolDTO" %>
<%@ page import="development.team.Models.Rol" %>
<%@ page import="development.team.DAO.RolDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Usuarios</title>
    <link rel="stylesheet" href="../assets/css/usuarios.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <!-- SweetAlert2 -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<%
    List<UsuarioRolDTO> usuarios = UsuarioDAO.obtenerTodosUsuarios();
    List<Rol> roles = RolDAO.obtenerRols();
%>

<div class="content-container">
    <h1>Gestión de Usuarios</h1>

    <button id="openAddUserBtn" class="add-provider">Agregar Usuario</button>

    <div class="filter-container">
        <select id="roleFilter">
            <option value="">Todos los roles</option>
            <%
                for (Rol rolList : roles) {
            %>
            <option value="<%= rolList.getNombreRol() %>"><%= rolList.getNombreRol() %></option>
            <%
                }
            %>
        </select>
        <select id="statusFilter">
            <option value="">Todos los estados</option>
            <option value="Activo">Activo</option>
            <option value="Inactivo">Inactivo</option>
        </select>
        <div class="search-container">
            <input type="text" class="search-provider" placeholder="Buscar usuario por nombre...">
        </div>
        <button class="btn primary" id="applyFilters">Buscar</button>
    </div>

    <div class="table-container">
        <table class="user-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nombre completo</th>
                <th>Email</th>
                <th>Rol</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <%
                int i = 1;
                for (UsuarioRolDTO usuarioList : usuarios) { %>
            <tr data-id="1">
                <td><%= i %></td>
                <td><%= usuarioList.getNombreUsuario()%> </td>
                <td><%= usuarioList.getCorreo() %></td>
                <td><span class="role-badge role-blue"><%= usuarioList.getNombreRol() %></span></td>
                <td><span class="role-badge role-green"><%= usuarioList.getEstado() == 1 ? "Activo" : "Inactivo" %></span></td>
                <td>
                    <button class="btn view" title="Ver"><i class="fas fa-eye"></i></button>
                    <button class="btn edit" id="editBtn_<%= usuarioList.getIdUsuario() %>" title="Editar"><i class="fas fa-edit"></i></button>
                    <button class="btn reset-password" title="Resetear Contraseña"><i class="fas fa-key"></i></button>
                    <button class="btn deactivate" title="Desactivar"><i class="fas fa-user-slash"></i></button>
                </td>
            </tr>
            <%
                    i++;
                }
            %>
            </tbody>
        </table>
    </div>
</div>

<!-- MODAL AÑADIR USUARIO -->
<div id="addUserModal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <h2>Nuevo Usuario</h2>
            <span class="close" id="closeAdd">&times;</span>
        </div>
        <div class="modal-body">
            <form id="addUserForm" action="<%=request.getContextPath()%>/user" method="post">
                <input type="hidden" name="accion" value="Registrar">
                <!-- Contenido de campos -->
                <div class="form-row">
                    <div class="form-group">
                        <label for="fullName">Nombre Completo</label>
                        <input type="text" id="fullName" name="fullName" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="role">Rol</label>
                        <select id="role" name="role" required>
                            <option value="">Seleccione un rol</option>
                            <option value="1">Administrador</option>
                            <option value="2">Vendedor</option>
                        </select>
                    </div>
                </div>

                <!-- Botones del formulario -->
                <div class="form-actions">
                    <button type="button" class="btn cancel" id="cancelAdd">Cancelar</button>
                    <button type="submit" class="btn primary">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- MODAL EDITAR USUARIO -->
<div id="editUserModal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <h2>Editar Usuario</h2>
            <span class="close" id="closeEdit">&times;</span>
        </div>
        <div class="modal-body">
            <form id="editUserForm">
                <input type="hidden" name="accion" value="update">
                <div class="form-row">
                    <div class="form-group">
                        <label for="fullNameEdit">Nombre Completo</label>
                        <input type="text" id="fullNameEdit" name="fullNameEdit" required>
                    </div>
                </div>

                <div class="form-group">
                    <label for="emailEdit">Email</label>
                    <input type="email" id="emailEdit" name="emailEdit" required readonly>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="role">Rol</label>
                        <select id="roleEdit" name="role" required>
                            <option value="">Seleccione un rol</option>
                            <option value="admin">Administrador</option>
                            <option value="vendedor">Vendedor</option>
                        </select>
                    </div>
                </div>

                <!-- Botones del formulario -->
                <div class="form-actions">
                    <button type="button" class="btn cancel" id="cancelEdit">Cancelar</button>
                    <button type="submit" class="btn primary">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>


<!-- Modal para añadir/editar usuario -->
<%--<div id="userModal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <h2>Usuario</h2>
            <span class="close">&times;</span>
        </div>
        <div class="modal-body">
            <form id="userForm">

            </form>
        </div>
    </div>
</div>--%>

<script src="../assets/js/usuarios.js"></script>
