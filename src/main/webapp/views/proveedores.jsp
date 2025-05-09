<%@ page import="development.team.Models.Proveedor" %>
<%@ page import="java.util.List" %>
<%@ page import="development.team.DAO.ProveedorDAO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Proveedores</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/proveedores.css" />
</head>

<%
    List<Proveedor> proveedores = ProveedorDAO.obtenerProveedores();
%>

<div class="content-container">
    <h1>Gestión de Proveedores</h1>
    <button class="add-provider">Añadir Proveedor</button>
    <div class="header-controls">
        <div class="search-container">
            <label>
                <input type="text" class="search-provider" placeholder="Buscar proveedor...">
            </label>
        </div>
        <button class="btn filter" id="applyFilters">Buscar</button>
    </div>

    <div class="table-container">
        <table class="provider-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Teléfono</th>
                <th>Correo</th>
                <th>Dirección</th>
                <th>Ruc</th>
                <th>Cuenta Interbancaria</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <%
                int i = 1;
                for (Proveedor proveedorList : proveedores) {%>
                <tr data-id="<%= i %>">
                    <td><%= i %></td>
                    <td><%= proveedorList.getNombre() %></td>
                    <td><%= proveedorList.getTelefono() %></td>
                    <td><%= proveedorList.getCorreo() %></td>
                    <td><%= proveedorList.getDireccion() %></td>
                    <td><%= proveedorList.getNumeroRuc() %></td>
                    <td><%= proveedorList.getCuentaInterbancaria() %></td>
                    <td>
                        <button class="btn view" title="Ver"><i class="fas fa-eye"></i></button>
                        <button class="btn edit" title="Editar"><i class="fas fa-edit"></i></button>
                        <button class="btn deactivate" title="Eliminar"><i class="fas fa-user-slash"></i></button>
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

<!-- Modal para añadir/editar proveedor -->
<div id="providerModal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <h2>Proveedor</h2>
            <span class="close">&times;</span>
        </div>
        <div class="modal-body">
            <form id="providerForm" method="post" action="proveedor">
                <div class="form-group">
                    <label for="providerName">Nombre</label>
                    <input type="text" id="providerName" name="providerName" required>
                </div>
                <div class="form-group">
                    <label for="email">Correo</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="phone">Teléfono</label>
                    <input type="tel" id="phone" name="phone" required>
                </div>
                <div class="form-group">
                    <label for="address">Dirección</label>
                    <textarea id="address" name="address" rows="3"></textarea>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn cancel">Cancelar</button>
                    <button type="submit" class="btn primary">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script src="<%= request.getContextPath() %>/assets/js/proveedores.js"></script>

