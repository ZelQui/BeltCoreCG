<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Compras</title>
    <!-- Este archivo CSS se agregará solo para esta página -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/compras.css">
</head>
<body class="compras-page">
    <div class="container">
        <div class="header-container">
            <h1>Gestión de Compras</h1>
            <button id="add-purchase-btn" class="btn btn-primary">+ Agregar Compra</button>
        </div>
        
        <div class="search-container">
            <input type="text" class="search-input" placeholder="Buscar compra...">
            <button class="btn btn-info">Buscar</button>
        </div>
        
        <table>
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
                <tr>
                    <td>1</td>
                    <td>26-06-2022</td>
                    <td>Zapatos de Cuero Kale S.A.</td>
                    <td>F001-12345</td>
                    <td>$1,250.00</td>
                    <td>Transferencia</td>
                    <td>
                        <div class="action-buttons">
                            <button class="btn btn-info btn-sm view-btn" data-id="1">Ver</button>
                            <button class="btn btn-primary btn-sm edit-btn" data-id="1">Editar</button>
                            <button class="btn btn-danger btn-sm delete-btn" data-id="1">Eliminar</button>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>25-06-2022</td>
                    <td>Distribuidora XYZ</td>
                    <td>F023-98765</td>
                    <td>$875.50</td>
                    <td>Efectivo</td>
                    <td>
                        <div class="action-buttons">
                            <button class="btn btn-info btn-sm view-btn" data-id="2">Ver</button>
                            <button class="btn btn-primary btn-sm edit-btn" data-id="2">Editar</button>
                            <button class="btn btn-danger btn-sm delete-btn" data-id="2">Eliminar</button>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>24-06-2022</td>
                    <td>Suministros ABC</td>
                    <td>F045-56789</td>
                    <td>$2,340.75</td>
                    <td>Tarjeta</td>
                    <td>
                        <div class="action-buttons">
                            <button class="btn btn-info btn-sm view-btn" data-id="3">Ver</button>
                            <button class="btn btn-primary btn-sm edit-btn" data-id="3">Editar</button>
                            <button class="btn btn-danger btn-sm delete-btn" data-id="3">Eliminar</button>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>23-06-2022</td>
                    <td>Comercial DEF</td>
                    <td>F103-45678</td>
                    <td>$950.25</td>
                    <td>Cheque</td>
                    <td>
                        <div class="action-buttons">
                            <button class="btn btn-info btn-sm view-btn" data-id="4">Ver</button>
                            <button class="btn btn-primary btn-sm edit-btn" data-id="4">Editar</button>
                            <button class="btn btn-danger btn-sm delete-btn" data-id="4">Eliminar</button>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td>5</td>
                    <td>22-06-2022</td>
                    <td>Importadora GHI</td>
                    <td>F067-34567</td>
                    <td>$1,875.30</td>
                    <td>Transferencia</td>
                    <td>
                        <div class="action-buttons">
                            <button class="btn btn-info btn-sm view-btn" data-id="5">Ver</button>
                            <button class="btn btn-primary btn-sm edit-btn" data-id="5">Editar</button>
                            <button class="btn btn-danger btn-sm delete-btn" data-id="5">Eliminar</button>
                        </div>
                    </td>
                </tr>
            </tbody>
        </table>
        
        <div class="pagination">
            <a href="#">&laquo;</a>
            <a href="#" class="active">1</a>
            <a href="#">2</a>
            <a href="#">3</a>
            <a href="#">4</a>
            <a href="#">5</a>
            <a href="#">&raquo;</a>
        </div>
    </div>
    
    <!-- Modal para Agregar/Editar Compra -->
    <div id="purchase-modal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2>Registrar Compra</h2>
                <button class="close">&times;</button>
            </div>
            
            <div class="modal-body">
                <form id="purchase-form">
                    <div class="form-row">
                        <div class="form-group">
                            <label class="form-label">Proveedor</label>
                            <select class="form-control" required>
                                <option value="">Seleccione proveedor</option>
                                <option>Zapatos de Cuero Kale S.A.</option>
                                <option>Distribuidora XYZ</option>
                                <option>Suministros ABC</option>
                                <option>Comercial DEF</option>
                                <option>Importadora GHI</option>
                            </select>
                        </div>
                        
                        <div class="form-group">
                            <label class="form-label">Tipo Comprobante</label>
                            <select class="form-control" required>
                                <option value="">Seleccione tipo</option>
                                <option>FACTURA</option>
                                <option>BOLETA</option>
                                <option>RECIBO</option>
                                <option>NOTA DE CRÉDITO</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label class="form-label">Factura N°</label>
                            <input type="text" class="form-control" placeholder="Ingrese N° Factura" required>
                        </div>
                        
                        <div class="form-group">
                            <label class="form-label">Fecha</label>
                            <input type="date" class="form-control" value="2022-06-26" required>
                        </div>
                    </div>
                    
                    <div style="margin: 20px 0;">
                        <button type="button" class="btn btn-info" id="search-product-btn">Buscar Producto</button>
                    </div>
                    
                    <div class="table-container">
                        <table>
                            <thead>
                                <tr>
                                    <th>N°</th>
                                    <th>DESCRIPCION</th>
                                    <th>CANTIDAD</th>
                                    <th>PRECIO</th>
                                    <th>ACCION</th>
                                </tr>
                            </thead>
                            <tbody id="product-list">
                                <!-- Los productos se añaden dinámicamente -->
                            </tbody>
                        </table>
                    </div>
                    
                    <div style="display: flex; justify-content: flex-end; margin-top: 15px;">
                        <div style="width: 200px;">
                            <label class="form-label">Total a Pagar</label>
                            <input type="text" class="form-control" id="total-amount" value="0.00" readonly>
                        </div>
                    </div>
                </form>
            </div>
            
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" id="cancel-btn">Cancelar</button>
                <button type="button" class="btn btn-primary" id="save-btn">Registrar</button>
            </div>
        </div>
    </div>
    
    <!-- Modal para Buscar Productos -->
    <div id="products-modal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h2>Buscar Productos</h2>
                <button class="close">&times;</button>
            </div>
            
            <div class="modal-body">
                <div class="search-container">
                    <input type="text" class="search-input" placeholder="Buscar por descripción o código...">
                    <button class="btn btn-info">Buscar</button>
                </div>
                
                <table>
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
                        <tr>
                            <td>P001</td>
                            <td>Zapatos de cuero negro talla 40</td>
                            <td>$45.00</td>
                            <td>15</td>
                            <td>
                                <button class="btn btn-primary select-product" data-id="P001" data-name="Zapatos de cuero negro talla 40" data-price="45.00">
                                    Seleccionar
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <td>P002</td>
                            <td>Zapatos de cuero marrón talla 42</td>
                            <td>$50.00</td>
                            <td>10</td>
                            <td>
                                <button class="btn btn-primary select-product" data-id="P002" data-name="Zapatos de cuero marrón talla 42" data-price="50.00">
                                    Seleccionar
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <td>P003</td>
                            <td>Sandalias de cuero negro talla 38</td>
                            <td>$35.00</td>
                            <td>20</td>
                            <td>
                                <button class="btn btn-primary select-product" data-id="P003" data-name="Sandalias de cuero negro talla 38" data-price="35.00">
                                    Seleccionar
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <td>P004</td>
                            <td>Botas de cuero negro talla 39</td>
                            <td>$65.00</td>
                            <td>8</td>
                            <td>
                                <button class="btn btn-primary select-product" data-id="P004" data-name="Botas de cuero negro talla 39" data-price="65.00">
                                    Seleccionar
                                </button>
                            </td>
                        </tr>
                        <tr>
                            <td>P005</td>
                            <td>Mocasines de cuero marrón talla 41</td>
                            <td>$55.00</td>
                            <td>12</td>
                            <td>
                                <button class="btn btn-primary select-product" data-id="P005" data-name="Mocasines de cuero marrón talla 41" data-price="55.00">
                                    Seleccionar
                                </button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    
    <!-- El script separado para mejorar la modularidad -->
    <script src="/assets/js/compras.js"></script>
</body>
</html>