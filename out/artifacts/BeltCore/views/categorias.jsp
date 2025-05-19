<%--
  Created by IntelliJ IDEA.
  User: santa
  Date: 08/05/2025
  Time: 12:29 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/categorias.css" />
</head>

<div class="productos-container">
    <h1>Gestión de categorias</h1>

    <div class="acciones">
        <button id="btnProducto"><i class="fas fa-plus"></i> Agregar categoria</button>
    </div>

    <div class="filter-container">
        <div class="search-container">
            <input type="text" class="search-provider" placeholder="Buscar categoria...">
            <button class="btn primary" id="applyFilters">Buscar</button>
        </div>
    </div>

    <!-- Tabla de categorias -->
    <div class="table-container">
        <table class="user-table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>Correa 1</td>
                <td>
                    <button class="btn view" title="Ver"><i class="fas fa-eye"></i></button>
                    <button class="btn edit" title="Editar"><i class="fas fa-edit"></i></button>
                    <button class="btn delete" title="Eliminar"><i class="fas fa-trash-alt"></i></button>
                </td>
            </tr>
            <tr>
                <td>2</td>
                <td>Correa 2</td>
                <td>
                    <button class="btn view" title="Ver"><i class="fas fa-eye"></i></button>
                    <button class="btn edit" title="Editar"><i class="fas fa-edit"></i></button>
                    <button class="btn delete" title="Eliminar"><i class="fas fa-trash-alt"></i></button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>

<!-- Modal Producto -->
<div id="modal-producto" class="modal oculto">
    <div class="modal-contenido">
        <div class="modal-header">
            <h2>Nueva categoria</h2>
            <span class="cerrar-modal">&times;</span>
        </div>
        <form id="form-producto" class="formulario">

            <label>Nombre de categoria:</label>
            <input type="text" placeholder="Ingrese nombre" />

            <div class="botones">
                <button type="button" class="cancelar">Cancelar</button>
                <button type="submit">Agregar</button>
            </div>
        </form>
    </div>
</div>

<script src="<%= request.getContextPath() %>/assets/js/categorias.js"></script>
