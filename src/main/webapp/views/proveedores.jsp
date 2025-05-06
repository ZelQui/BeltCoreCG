<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Proveedores</title>
    <link rel="stylesheet" href="../assets/css/proveedores.css">
</head>
<body>
    <div class="content-container">
        <h1>Gestión de Proveedores</h1>
        
        <div class="header-controls">
            <div class="search-container">
                <label>
                    <input type="text" class="search-provider" placeholder="Buscar proveedor...">
                </label>
            </div>
            <button class="add-provider">Añadir Proveedor</button>
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
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <tr data-id="1">
                        <td>1</td>
                        <td>Proveedor Ejemplo S.A.</td>
                        <td>555-123-4567</td>
                        <td>juan@ejemplo.com</td>
                        <td>Av. Siempre Viva 123</td>
                        <td>
                            <button class="btn view" title="Ver"><i class="fas fa-eye"></i></button>
                            <button class="btn edit" title="Editar"><i class="fas fa-edit"></i></button>
                            <button class="btn deactivate" title="Eliminar"><i class="fas fa-user-slash"></i></button>
                        </td>
                    </tr>
                    <tr data-id="2">
                        <td>2</td>
                        <td>Distribuidora XYZ</td>
                        <td>555-987-6543</td>
                        <td>maria@xyz.com</td>
                        <td>Jr. Comercio 456</td>
                        <td>
                            <button class="btn view" title="Ver"><i class="fas fa-eye"></i></button>
                            <button class="btn edit" title="Editar"><i class="fas fa-edit"></i></button>
                            <button class="btn deactivate" title="Eliminar"><i class="fas fa-user-slash"></i></button>
                        </td>
                    </tr>
                    <tr data-id="3">
                        <td>3</td>
                        <td>Suministros ABC</td>
                        <td>555-456-7890</td>
                        <td>carlos@abc.com</td>
                        <td>Calle Progreso 789</td>
                        <td>
                            <button class="btn view" title="Ver"><i class="fas fa-eye"></i></button>
                            <button class="btn edit" title="Editar"><i class="fas fa-edit"></i></button>
                            <button class="btn deactivate" title="Eliminar"><i class="fas fa-user-slash"></i></button>
                        </td>
                    </tr>
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
                <form id="providerForm">
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
    <script src="<%=request.getContextPath()%>/assets/js/proveedores.js"></script>
</body>
</html>
