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

    <!-- Agregar SweetAlert2 -->
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</head>

<%
    List<Proveedor> proveedores = ProveedorDAO.obtenerProveedores();
%>


<!-- Registrar Proveedor: Mensaje Registro -->
<%
    String mensajeRegistro = (String) session.getAttribute("mensajeRegistro");
    String iconRegistro = (String) session.getAttribute("iconRegistro");
    if (mensajeRegistro != null || iconRegistro != null) {

        // Eliminar mensaje de la sesion para que no aparesca al recargar la pagina
        session.removeAttribute("mensajeRegistro");
        session.removeAttribute("iconRegistro");
%>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        Swal.fire({
            title: "<%= mensajeRegistro%>",
            icon: "<%= iconRegistro%>",
            timer: 2000,
            showConfirmButton: false
        });
    });
</script>
<%  } %>

<!-- Editar Proveedor: Mensaje Editado -->
<%
    String mensajeEditado = (String) session.getAttribute("mensajeEditado");
    String icon = (String) session.getAttribute("icon");
    if (mensajeEditado != null || icon != null) {

        // Eliminar mensaje de la sesion para que no aparesca al recargar la pagina
        session.removeAttribute("mensajeEditado");
        session.removeAttribute("icon");
%>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        Swal.fire({
            title: "<%= mensajeEditado%>",
            icon: "<%= icon%>",
            timer: 2000,
            showConfirmButton: false
        });
    });
</script>
<%  }%>

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
                <th>#</th>
                <th>Nombre</th>
                <th>Teléfono</th>
                <th>Correo</th>
                <th>Ruc</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <%
                int i = 1;
                for (Proveedor proveedorList : proveedores) {
            %>
            <tr data-id="<%= proveedorList.getIdProveedor() %>">
                <td><%= i %></td>
                <td><%= proveedorList.getNombre() %></td>
                <td><%= proveedorList.getTelefono() %></td>
                <td><%= proveedorList.getCorreo() %></td>
                <td><%= proveedorList.getNumeroRuc() %></td>

                <!-- Campos ocultos -->
                <input type="hidden" class="direccion-hidden" value="<%= proveedorList.getDireccion() %>">
                <input type="hidden" class="cci-hidden" value="<%= proveedorList.getCuentaInterbancaria() %>">

                <td>
                    <button class="btn view" title="Ver"><i class="fas fa-eye fa-sm"></i></button>
                    <button class="btn edit" title="Editar"><i class="fas fa-edit fa-sm"></i></button>
                    <form method="post" action="${pageContext.request.contextPath}/ProveedorController">
                        <input type="hidden" name="accion" value="cambiarEstado">
                        <input type="hidden" name="idProveedor" value="<%= proveedorList.getIdProveedor() %>">
                        <input type="hidden" name="nuevoEstado" value="<%= proveedorList.getEstado() == 1 ? 0 : 1 %>">
                        <button type="submit" class="btn toggle <%= proveedorList.getEstado() == 1 ? "btn deactivate" : "btn activate" %>" title="Cambiar estado">
                            <i class="fas <%= proveedorList.getEstado() == 1 ? "fa-user-slash text-danger" : "fa-user-check text-success" %> fa-sm"></i>
                            <span class="<%= proveedorList.getEstado() == 1 ? "text-success" : "text-danger" %> fw-bold">
                            <%= proveedorList.getEstado() == 1 ? "Desactivar" : "Activar" %>
                        </span>
                        </button>
                    </form>
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

<!-- Modal exclusivo para añadir proveedor -->
<div id="addProviderModal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <h2>Nuevo Proveedor</h2>
            <span class="close" onclick="closeAddProviderModal()">&times;</span>
        </div>
        <div class="modal-body">
            <form id="addProviderForm" method="post" action="<%=request.getContextPath()%>/ProveedorController">
                <input type="hidden" name="accion" value="registrar">

                <div class="form-group">
                    <label for="nuevoNombre">Nombre</label>
                    <input type="text" id="nuevoNombre" name="nombre" required>
                </div>
                <div class="form-group">
                    <label for="nuevoTelefono">Teléfono</label>
                    <input type="tel" id="nuevoTelefono" name="telefono" required>
                </div>
                <div class="form-group">
                    <label for="nuevoCorreo">Correo</label>
                    <input type="email" id="nuevoCorreo" name="correo" required>
                </div>
                <div class="form-group">
                    <label for="nuevaDireccion">Dirección</label>
                    <input type="text" id="nuevaDireccion" name="direccion">
                </div>
                <div class="form-group">
                    <label for="nuevoRuc">RUC</label>
                    <input type="text" id="nuevoRuc" name="ruc" required>
                </div>
                <div class="form-group">
                    <label for="nuevaCuenta">Cuenta Interbancaria</label>
                    <input type="text" id="nuevaCuenta" name="cuentaInterbancaria">
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn cancel" onclick="closeAddProviderModal()">Cancelar</button>
                    <button type="submit" class="btn primary">Guardar</button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Editar proveedor -->
<div id="providerModal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <h2>Proveedor</h2>
            <span class="close">&times;</span>
        </div>
        <div class="modal-body">
            <form id="providerForm" method="post" action="<%=request.getContextPath()%>/ProveedorController">
                <input type="hidden" name="accion" value="editar">
                <input type="hidden" id="providerId" name="idProveedor">

                <div class="form-group">
                    <label for="providerName">Nombre</label>
                    <input type="text" id="providerName" name="nuevoNombre" required>
                </div>

                <div class="form-group">
                    <label for="email">Correo</label>
                    <input type="email" id="email" name="nuevoCorreo" required>
                </div>
                <div class="form-group">
                    <label for="phone">Teléfono</label>
                    <input type="tel" id="phone" name="nuevoTelefono" required>
                </div>
                <div class="form-group">
                    <label for="address">Dirección</label>
                    <textarea id="address" name="nuevaDireccion" rows="3"></textarea>
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

