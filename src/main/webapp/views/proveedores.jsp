<%@ page import="development.team.Models.Proveedor" %>
<%@ page import="java.util.List" %>
<%@ page import="development.team.DAO.ProveedorDAO" %>
<%@ page import="development.team.Models.CuentaBancaria" %>
<%@ page import="development.team.DAO.CuentaBancariaDAO" %>
<%@ page import="development.team.DTO.ProveedorCuentasBancarias" %>
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
    List<ProveedorCuentasBancarias> proveedores = ProveedorDAO.obtenerProveedores();
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

<!-- Ruc Proveedor: Mensaje Ruc -->
<%
    String mensajeRuc = (String) session.getAttribute("mensajeRuc");
    String iconRuc = (String) session.getAttribute("iconRuc");
    if (mensajeRuc != null || iconRuc != null) {

        // Eliminar mensaje de la sesion para que no aparesca al recargar la pagina
        session.removeAttribute("mensajeRuc");
        session.removeAttribute("iconRuc");
%>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        Swal.fire({
            title: "<%= mensajeRuc%>",
            icon: "<%= iconRuc%>",
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
                <input type="text" class="search-provider" placeholder="Buscar por (RUC o Nombre) ...">
            </label>
        </div>

        <select id="statusFilter">
            <option value="">Todos los estados</option>
            <option value="Activo">Activo</option>
            <option value="Inactivo">Inactivo</option>
        </select>

    </div>

    <div class="table-container">
        <table class="provider-table">
            <thead>
            <tr>
                <th>#</th>
                <th>Ruc</th>
                <th>Nombre o Razon Social</th>
                <th>Estado Contribuyente</th>
                <th>Domicilio Fiscal</th>
                <th>Telefono</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <%
                int i = 1;
                for (ProveedorCuentasBancarias proveedorList : proveedores) {
                    boolean esActivo = proveedorList.getEstado() == 1;
                    String claseEstado = esActivo ? "role-green" : "role-red";
                    String textoEstado = esActivo ? "Activo" : "Inactivo";
                    String botonEstado = esActivo ? "btn deactivate" : "btn activate";
                    String tituloEstado = esActivo ? "Desactivar" : "Activar";
            %>
            <tr data-id="<%= proveedorList.getIdProveedor() %>" data-activo="<%= esActivo %>">
                <td><%= i %></td>
                <td><%= proveedorList.getNumeroRuc() %></td>
                <td><%= proveedorList.getNombreRazonSocial() %></td>
                <td><%= proveedorList.getEstadoContribuyente() %></td>
                <td><%= proveedorList.getDomicilioFiscal() %></td>
                <td><%= proveedorList.getTelefono() %></td>
                <td>
                    <span class="proveedor-estado role-badge <%= claseEstado %>"><%= textoEstado %></span>
                </td>

                <!-- Campos ocultos -->
                <input type="hidden" class="direccionAlterna-hidden" value="<%= proveedorList.getDomicilioAlterna() != null ? proveedorList.getDomicilioAlterna() : "" %>">
                <input type="hidden" class="tipo-hidden" value="<%= proveedorList.getTipoCuentaBancaria() %>">
                <input type="hidden" class="cuenta-hidden" value="<%= proveedorList.getNumeroCuenta() %>">

                <td class="acciones">
                    <button class="btn view" title="Ver"><i class="fas fa-eye"></i></button>
                    <button class="btn edit" title="Editar"><i class="fas fa-edit"></i></button>
                    <button class="btn-toggle-status <%= botonEstado %>" title="<%= tituloEstado %>">
                        <i class="fas fa-user-<%= esActivo ? "slash" : "check" %>"></i>
                    </button>
                </td>
            </tr>
            <%
                    i++;
                }
            %>
            </tbody>
        </table>
        <div id="pagination" class="pagination-controls"></div>
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

                <div class="form-row" style="margin-bottom: 1.5rem;">
                    <div class="ruc-group">
                        <label for="nuevoRuc">RUC</label>
                        <input type="text" id="nuevoRuc" name="ruc" required maxlength="11">
                        <button class="btn buscar" type="button" onclick="buscarProveedorSUNAT()"><i class="fas fa-search"></i></button>
                    </div>
                </div>

                <div class="form-group">
                    <label for="nuevoNombre">Nombre <span class="required-star">*</span></label>
                    <input type="text" id="nuevoNombre" name="nombreRazonSocial"  readonly required>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="estadoRuc">Estado SUNAT <span class="required-star">*</span></label>
                        <input type="text" id="estadoRuc" name="estadoContribuyente" readonly required>
                    </div>

                    <div class="form-group">
                        <label for="nuevaDireccion">Domicilio Fiscal <span class="required-star">*</span></label>
                        <input type="text" id="nuevaDireccion" name="domicilioFiscal" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="nuevaDireccionAlterna">Domicilio Alterna (No Obligatorio)</label>
                        <input type="text" id="nuevaDireccionAlterna" name="domicilioAlterna">
                    </div>

                    <div class="form-group">
                        <label for="nuevoTelefono">Teléfono <span class="required-star">*</span> (Ej: 954135647)</label>
                        <input type="tel" id="nuevoTelefono" name="telefono"  maxlength="9" required>
                        <span id="telefonoError" style="color:red; font-size: 0.9em;"></span>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="tipoCuenta">Tipo de Metodo de Pago <span class="required-star">*</span></label>
                        <select id="tipoCuenta" name="idCuentaBancaria"  onchange="habilitarYValidarCuenta()">
                            <option value="">Seleccione un metodo de pago</option>
                            <%
                                List<CuentaBancaria> cuentasBancarias = CuentaBancariaDAO.obtenerCuentasBancarias();
                                if (cuentasBancarias != null) {
                                    for (CuentaBancaria cuenta : cuentasBancarias) {
                            %>
                            <option value="<%= cuenta.getIdCuentaBancaria() %>">
                                <%= cuenta.getTipoCuentaBancaria() %>
                            </option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="nuevaCuenta" id="labelCuenta">Cuenta</label>
                        <input type="text" id="nuevaCuenta" name="numeroCuenta" required disabled>
                    </div>
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

                <div class="form-row">
                    <div class="form-group">
                        <label for="editRuc">RUC</label>
                        <input type="text" id="editRuc" name="ruc" disabled>
                    </div>
                    <div class="form-group">
                        <label for="editEstado">Estado SUNAT</label>
                        <input type="text" id="editEstado" name="estadoContribuyente" disabled>
                    </div>
                </div>

                <div class="form-group">
                    <label for="editName">Nombre o Razón Social</label>
                    <input type="text" id="editName" name="nombreRazonSocial" disabled>
                </div>

                <div class="form-group">
                    <label for="editDireccion">Dirección Fiscal</label>
                    <input type="text" id="editDireccion" name="domicilioFiscal" disabled>
                </div>

                <div class="form-group">
                    <label for="editDireccionAlterna">Dirección Alterna</label>
                    <input type="text" id="editDireccionAlterna" name="domicilioAlterna">
                </div>

                <div class="form-group">
                    <label for="editTelefono">Teléfono</label>
                    <input type="tel" id="editTelefono" name="telefono" maxlength="9">
                </div>

                <div class="form-row">
                    <div class="form-group">
                        <label for="editTipoCuenta">Tipo de Metodo de Pagoa</label>
                        <input type="text" id="editTipoCuenta" name="tipoCuenta" disabled>
                    </div>
                    <div class="form-group">
                        <label for="editNumeroCuenta" id="editLabelCuenta">Número de Cuenta</label>
                        <input type="text" id="editNumeroCuenta" name="numeroCuenta" disabled>
                    </div>
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
    const BASE_URL = "<%=request.getContextPath()%>";
</script>

<script src="<%= request.getContextPath() %>/assets/js/proveedores.js"></script>

