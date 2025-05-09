<%@ page import="development.team.DAO.UsuarioDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="development.team.DTO.UsuarioRolDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Usuarios</title>
    <link rel="stylesheet" href="../assets/css/usuarios.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<%
    List<UsuarioRolDTO> usuarios = UsuarioDAO.obtenerTodosUsuarios();
%>

<div class="content-container">
    <h1>Gestión de Usuarios</h1>

    <button class="add-provider">Añadir Usuario</button>

    <div class="filter-container">
        <div class="search-container">
            <input type="text" class="search-provider" placeholder="Buscar usuario...">
        </div>
        <select id="roleFilter">
            <option value="">Todos los roles</option>
            <option value="Administrador">Administrador</option>
            <option value="Vendedor">Vendedor</option>
        </select>
        <select id="statusFilter">
            <option value="">Todos los estados</option>
            <option value="Activo">Activo</option>
            <option value="Inactivo">Inactivo</option>
        </select>

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
            <tr data-id="1">
                <td>1</td>
                <td>Ana Martínez</td>
                <td>ana.martinez@ejemplo.com</td>
                <td><span class="role-badge role-blue">Administrador</span></td>
                <td><span class="role-badge role-green">Activo</span></td>
                <td>
                    <button class="btn view" title="Ver"><i class="fas fa-eye"></i></button>
                    <button class="btn edit" title="Editar"><i class="fas fa-edit"></i></button>
                    <button class="btn reset-password" title="Resetear Contraseña"><i class="fas fa-key"></i></button>
                    <button class="btn deactivate" title="Desactivar"><i class="fas fa-user-slash"></i></button>
                </td>
            </tr>
            <tr data-id="2">
                <td>2</td>
                <td>Luis Rodríguez</td>
                <td>luis.rodriguez@ejemplo.com</td>
                <td><span class="role-badge role-gray">Vendedor</span></td>
                <td><span class="role-badge role-green">Activo</span></td>
                <td>
                    <button class="btn view" title="Ver"><i class="fas fa-eye"></i></button>
                    <button class="btn edit" title="Editar"><i class="fas fa-edit"></i></button>
                    <button class="btn reset-password" title="Resetear Contraseña"><i class="fas fa-key"></i></button>
                    <button class="btn deactivate" title="Desactivar"><i class="fas fa-user-slash"></i></button>
                </td>
            </tr>
            <tr data-id="3">
                <td>3</td>
                <td>Carmen García</td>
                <td>carmen.garcia@ejemplo.com</td>
                <td><span class="role-badge role-gray">Vendedor</span></td>
                <td><span class="role-badge role-red">Inactivo</span></td>
                <td>
                    <button class="btn view" title="Ver"><i class="fas fa-eye"></i></button>
                    <button class="btn edit" title="Editar"><i class="fas fa-edit"></i></button>
                    <button class="btn reset-password" title="Resetear Contraseña"><i class="fas fa-key"></i></button>
                    <button class="btn activate" title="Activar"><i class="fas fa-user-check"></i></button>
                </td>
            </tr>
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
                <div class="form-row">
                    <div class="form-group">
                        <label for="firstName">Nombre</label>
                        <input type="text" id="firstName" name="firstName" required>
                    </div>
                    <div class="form-group">
                        <label for="lastName">Apellidos</label>
                        <input type="text" id="lastName" name="lastName" required>
                    </div>
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" required>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="username">Nombre de usuario</label>
                        <input type="text" id="username" name="username" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Contraseña</label>
                        <input type="password" id="password" name="password">
                        <small>Dejar en blanco para mantener la contraseña actual (en caso de edición)</small>
                    </div>
                </div>

                <div class="form-row">
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
                </div>

                <!-- Botones del formulario -->
                <div class="form-actions">
                    <button type="button" class="btn cancel">Cancelar</button>
                    <button type="submit" class="btn primary">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="../assets/js/usuarios.js"></script>
