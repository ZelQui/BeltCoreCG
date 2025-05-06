<%@ page import="development.team.DAO.UsuarioDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="development.team.DTO.UsuarioRolDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Usuarios</title>
    <link rel="stylesheet" href="../assets/css/contenidos.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<%
    List<UsuarioRolDTO> usuarios = UsuarioDAO.obtenerTodosUsuarios();
%>
<body>
    <div class="content-container">
        <h1>Gestión de Usuarios</h1>

        <div class="header-controls">
            <div class="search-container">
                <label>
                    <input type="text" class="input-control" placeholder="Buscar usuario...">
                </label>
                <button class="btn view" title="Buscar"><i class="fas fa-search"></i></button>
            </div>
            <button class="add-provider">Añadir Usuario</button>
        </div>

        <div class="filter-bar">
            <select class="input-control" id="roleFilter">
                <option value="">Todos los roles</option>
                <option value="admin">Administrador</option>
                <option value="vended">Vendedor</option>
            </select>
            <select class="input-control" id="statusFilter">
                <option value="">Todos los estados</option>
                <option value="active">Activo</option>
                <option value="inactive">Inactivo</option>
            </select>
            <button class="btn btn-primary" id="applyFilters">Aplicar Filtros</button>
        </div>

        <div class="table-container">
            <table class="provider-table">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Nombre completo</th>
                    <th>Email</th>
                    <th>Rol</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                <% int contador = 1; %>
                <% for (UsuarioRolDTO urdto : usuarios) { %>
                <tr data-id="<%=contador%>">
                    <td><%=contador%></td>
                    <td><%=urdto.getNombreUsuario()%></td>
                    <td><%=urdto.getCorreo()%></td>
                    <td><span class="badge badge-info"><%=urdto.getNombreRol()%></span></td>
                    <td>
                        <% if (urdto.getEstado() == 1) { %>
                        <span class="badge badge-success">Activo</span>
                        <% } else { %>
                        <span class="badge badge-danger">Inactivo</span>
                        <% } %>
                    </td>
                    <td>
                        <button class="btn view" title="Ver"><i class="fas fa-eye"></i></button>
                        <button class="btn edit" title="Editar"><i class="fas fa-edit"></i></button>
                        <button class="btn reset" title="Resetear Contraseña"><i class="fas fa-key"></i></button>
                        <button class="btn deactivate" title="Desactivar"><i class="fas fa-user-slash"></i></button>
                    </td>
                </tr>
                <% contador++; } %>
                </tbody>
            </table>
        </div>
    </div>

    <!-- Modal para añadir/editar usuario -->
    <div id="userModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2>Usuario</h2>
                <span class="close">&times;</span>
            </div>
            <div class="modal-body">
                <form id="userForm">
                    <div class="form-group">
                        <label for="firstName">Nombre</label>
                        <input type="text" id="firstName" name="firstName" required>
                    </div>
                    <div class="form-group">
                        <label for="lastName">Apellidos</label>
                        <input type="text" id="lastName" name="lastName" required>
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="email" id="email" name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="username">Nombre de usuario</label>
                        <input type="text" id="username" name="username" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Contraseña</label>
                        <input type="password" id="password" name="password">
                        <small class="form-hint">Dejar en blanco para mantener la contraseña actual</small>
                    </div>
                    <div class="form-group">
                        <label for="role">Rol</label>
                        <select id="role" name="role" required>
                            <option value="">Seleccione un rol</option>
                            <option value="admin">Administrador</option>
                            <option value="vended">Vendedor</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="status">Estado</label>
                        <select id="status" name="status" required>
                            <option value="active">Activo</option>
                            <option value="inactive">Inactivo</option>
                        </select>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn cancel">Cancelar</button>
                        <button type="submit" class="btn primary">Guardar</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script>
        /* usuarios.js */
        // Abrir y cerrar modal de Usuarios
        document.addEventListener('DOMContentLoaded', () => {
            const btnAdd = document.getElementById('btnAddUser');
            const modal = document.getElementById('userModal');
            const closeBtn = modal.querySelector('.modal-close');
            const cancelBtn = document.getElementById('btnCancel');

            const openModal = () => modal.classList.add('open');
            const closeModal = () => modal.classList.remove('open');

            btnAdd.addEventListener('click', openModal);
            closeBtn.addEventListener('click', closeModal);
            cancelBtn.addEventListener('click', closeModal);

            // También permitir cerrar clicando fuera del diálogo
            modal.addEventListener('click', e => {
                if (e.target === modal) closeModal();
            });
        });
    </script>
</body>
</html>
