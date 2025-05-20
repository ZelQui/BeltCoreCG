<%@ page import="development.team.DAO.InsumoDAO" %>
<%@ page import="development.team.Models.Insumo" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <meta charset="UTF-8">
    <title>Solicitud de Compra</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/solicitudDeCompra.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>

<div class="content-container">
    <h3>Órdenes de Compra Pendientes</h3>
    <button class="btn btn-primary mb-3" onclick="abrirModalSolicitud()">Nueva Solicitud</button>

    <table class="table table-bordered table-sm">
        <thead class="table-light">
        <tr>
            <th>ID</th>
            <th>Fecha Solicitud</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody id="tablaOrdenes"></tbody>
    </table>

    <!-- Modal Solicitud -->
    <div class="modal fade" id="modalSolicitud" tabindex="-1">
        <div class="modal-dialog modal-lg modal-lg-custom">
            <div class="modal-content">
                <form id="formSolicitud" method="post" action="<%=request.getContextPath()%>/OrdenDeCompraServlet?accion=registrar">
                    <div class="modal-header">
                        <h5 class="modal-title">Nueva Solicitud</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <div class="row g-3 mb-3">
                            <div class="col-md-6">
                                <label>Insumo<span class="required-star">*</span></label>
                                <select id="insumoInput" class="form-select">
                                    <option value="">Seleccione un insumo</option>
                                    <%
                                        InsumoDAO insumoDao = new InsumoDAO();
                                        List<Insumo> insumos = insumoDao.listarInsumos();
                                        if (insumos != null) {
                                            for (Insumo ins : insumos) {
                                    %>
                                    <option value="<%= ins.getIdInsumo() %>">
                                        <%= ins.getNombre() %>
                                    </option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="col-md-4">
                                <input type="number" id="cantidadInput" class="form-control" placeholder="Cantidad" />
                            </div>
                            <div class="col-md-2">
                                <button class="btn btn-success w-100" type="button" onclick="agregarInsumo()">Agregar</button>
                            </div>
                        </div>

                        <table class="table table-bordered table-sm">
                            <thead class="table-light">
                            <tr>
                                <th>Insumo</th>
                                <th>Cantidad</th>
                                <th>Acción</th>
                            </tr>
                            </thead>
                            <tbody id="tablaInsumos"></tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button class="btn btn-primary" type="submit">Finalizar Solicitud</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="<%= request.getContextPath() %>/assets/js/solicitudDeCompras.js"></script>
