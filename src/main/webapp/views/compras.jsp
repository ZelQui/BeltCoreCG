<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Compras</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/compras.css" />
</head>
<div class="content-container">
    <h1>Gestión de Compras</h1>

    <button class="add-provider">Añadir compra</button>

    <div class="filter-container">
        <div class="search-container">
            <input type="text" class="search-provider" placeholder="Buscar compra...">
        </div>
        <select id="roleFilter">
            <option value="">Todos los proveedores</option>
            <option value="proveedor1">Proveedor 1</option>
            <option value="proveedor2">Proveedor 2</option>
        </select>
        <select id="statusFilter">
            <option value="">Todos los metodos de pago</option>
            <option value="Transferencia">Transferencia</option>
            <option value="Efectivo">Efectivo</option>
        </select>
        <button class="btn primary" id="applyFilters">Buscar</button>
    </div>

    <div class="table-container">
        <table class="provider-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Fecha Compra</th>
                <th>Proveedor</th>
                <th>N° Factura</th>
                <th>Total</th>
                <th>Método Pago</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <tr data-id="1">
                <td>1</td>
                <td>26-06-2022</td>
                <td>Zapatos de Cuero Kale S.A.</td>
                <td>F001-12345</td>
                <td>$1,250.00</td>
                <td>Transferencia</td>
                <td>
                    <button class="btn view" title="Ver"><i class="fas fa-eye"></i></button>
                    <button class="btn edit" title="Editar"><i class="fas fa-edit"></i></button>
                    <button class="btn deactivate" title="Eliminar"><i class="fas fa-user-slash"></i></button>
                </td>
            </tr>
            <tr data-id="2">
                <td>2</td>
                <td>26-06-2022</td>
                <td>Zapatos de Cuero Kale S.A.</td>
                <td>F001-12345</td>
                <td>$1,250.00</td>
                <td>Transferencia</td>
                <td>
                    <button class="btn view" title="Ver"><i class="fas fa-eye"></i></button>
                    <button class="btn edit" title="Editar"><i class="fas fa-edit"></i></button>
                    <button class="btn deactivate" title="Eliminar"><i class="fas fa-user-slash"></i></button>
                </td>
            </tr>
            <!-- Más filas aquí -->
            </tbody>
        </table>
    </div>

    <div class="pagination">
        <button class="btn"><i class="fas fa-chevron-left"></i></button>
        <button class="btn primary">1</button>
        <button class="btn">2</button>
        <button class="btn">3</button>
        <button class="btn"><i class="fas fa-chevron-right"></i></button>
    </div>
</div>

<!-- Modal para Agregar/Editar Compra -->
<div id="purchaseModal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <h2>Registrar Compra</h2>
            <span class="close">&times;</span>
        </div>
        <div class="modal-body">
            <form id="purchaseForm">
                <div class="form-group">
                    <label for="provider">Proveedor</label>
                    <select id="provider" name="provider" required>
                        <option value="">Seleccione proveedor</option>
                        <option>Zapatos de Cuero Kale S.A.</option>
                        <option>Distribuidora XYZ</option>
                        <option>Suministros ABC</option>
                        <option>Comercial DEF</option>
                        <option>Importadora GHI</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="invoiceType">Tipo Comprobante</label>
                    <select id="invoiceType" name="invoiceType" required>
                        <option value="">Seleccione tipo</option>
                        <option>FACTURA</option>
                        <option>BOLETA</option>
                        <option>RECIBO</option>
                        <option>NOTA DE CRÉDITO</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="invoiceNumber">Factura N°</label>
                    <input type="text" id="invoiceNumber" name="invoiceNumber" placeholder="Ingrese N° Factura" required>
                </div>
                <div class="form-group">
                    <label for="date">Fecha</label>
                    <input type="date" id="date" name="date" value="2022-06-26" required>
                </div>

                <div class="form-group">
                    <label for="invoiceNumber">Seleccione los productos</label>
                    <div class="table-container">
                        <table class="provider-table">
                            <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Precio</th>
                                <th>Descripción</th>
                                <th>Acciones</th>
                            </tr>
                            </thead>
                            <tbody id="productList">
                            <!-- Productos dinámicos -->
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="form-group" style="text-align: right;">
                    <label for="totalAmount">Total a Pagar</label>
                    <input type="text" id="totalAmount" name="totalAmount" value="0.00" readonly>
                </div>
            </form>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn cancel">Cancelar</button>
            <button type="button" class="btn primary">Registrar</button>
        </div>
    </div>
</div>

<!-- Modal para Buscar Productos -->
<div id="productsModal" class="modal">
    <div class="modal-content">
        <div class="modal-header">
            <h2>Buscar Productos</h2>
            <span class="close">&times;</span>
        </div>
        <div class="modal-body">
            <div class="search-container">
                <label>
                    <input type="text" class="search-provider" placeholder="Buscar por descripción o código...">
                </label>
                <button class="btn view" title="Buscar"><i class="fas fa-search"></i></button>
            </div>
            <div class="table-container">
                <table class="provider-table">
                    <thead>
                    <tr>
                        <th>Código</th>
                        <th>Descripción</th>
                        <th>Precio</th>
                        <th>Stock</th>
                        <th>Acción</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Filas de productos -->
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="<%= request.getContextPath() %>/assets/js/compras.js"></script>


