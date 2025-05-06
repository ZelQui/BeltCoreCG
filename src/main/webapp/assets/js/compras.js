// Script para la página de gestión de compras
document.addEventListener('DOMContentLoaded', function() {
    // Variables globales
    let productCounter = 0;
    let selectedProducts = [];
    
    // Referencias a elementos del DOM
    const purchaseModal = document.getElementById('purchase-modal');
    const productsModal = document.getElementById('products-modal');
    const addPurchaseBtn = document.getElementById('add-purchase-btn');
    const searchProductBtn = document.getElementById('search-product-btn');
    const cancelBtn = document.getElementById('cancel-btn');
    const saveBtn = document.getElementById('save-btn');
    const productList = document.getElementById('product-list');
    const totalAmount = document.getElementById('total-amount');
    const closeButtons = document.querySelectorAll('.close');
    
    // Comprobar si los elementos existen antes de asignar eventos
    if (addPurchaseBtn) {
        // Abrir modal de compra
        addPurchaseBtn.addEventListener('click', function() {
            purchaseModal.style.display = 'block';
            document.querySelector('.modal-header h2').textContent = 'Registrar Compra';
            resetForm();
        });
    }
    
    // Cerrar modales con botón X
    closeButtons.forEach(button => {
        button.addEventListener('click', function() {
            purchaseModal.style.display = 'none';
            productsModal.style.display = 'none';
        });
    });
    
    // Cerrar modales haciendo clic fuera
    window.addEventListener('click', function(event) {
        if (event.target === purchaseModal) {
            purchaseModal.style.display = 'none';
        }
        if (event.target === productsModal) {
            productsModal.style.display = 'none';
        }
    });
    
    // Asignar eventos solo si los elementos existen
    if (searchProductBtn) {
        // Abrir modal de búsqueda de productos
        searchProductBtn.addEventListener('click', function() {
            productsModal.style.display = 'block';
        });
    }
    
    if (cancelBtn) {
        // Cancelar registro de compra
        cancelBtn.addEventListener('click', function() {
            purchaseModal.style.display = 'none';
        });
    }
    
    if (saveBtn) {
        // Guardar compra
        saveBtn.addEventListener('click', function() {
            const form = document.getElementById('purchase-form');
            // Aquí iría la validación del formulario y lógica para guardar
            alert('Compra registrada con éxito');
            purchaseModal.style.display = 'none';
        });
    }
    
    // Editar compra existente
    document.querySelectorAll('.edit-btn').forEach(button => {
        button.addEventListener('click', function() {
            const purchaseId = this.getAttribute('data-id');
            document.querySelector('.modal-header h2').textContent = 'Editar Compra #' + purchaseId;
            purchaseModal.style.display = 'block';
            
            // Aquí cargarías los datos de la compra para editar
            loadDummyPurchaseData(purchaseId);
        });
    });
    
    // Ver detalles de una compra
    document.querySelectorAll('.view-btn').forEach(button => {
        button.addEventListener('click', function() {
            const purchaseId = this.getAttribute('data-id');
            alert('Ver detalles de compra #' + purchaseId);
            // Aquí podrías abrir un modal con los detalles en modo lectura
        });
    });
    
    // Eliminar compra
    document.querySelectorAll('.delete-btn').forEach(button => {
        button.addEventListener('click', function() {
            const purchaseId = this.getAttribute('data-id');
            if (confirm('¿Está seguro de eliminar la compra #' + purchaseId + '?')) {
                // Aquí iría la lógica para eliminar la compra
                alert('Compra #' + purchaseId + ' eliminada correctamente');
                // this.closest('tr').remove();
            }
        });
    });
    
    // Seleccionar producto - asignamos eventos de forma dinámica
    const attachProductSelectors = () => {
        document.querySelectorAll('.select-product').forEach(button => {
            button.addEventListener('click', function() {
                const productId = this.getAttribute('data-id');
                const productName = this.getAttribute('data-name');
                const productPrice = this.getAttribute('data-price');
                
                addProductToList(productId, productName, productPrice);
                productsModal.style.display = 'none';
            });
        });
    };
    
    // Ejecutamos la función para adjuntar los eventos
    attachProductSelectors();
    
    // Agregar producto a la lista
    function addProductToList(id, name, price) {
        productCounter++;
        
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${productCounter}</td>
            <td>${name}</td>
            <td>
                <input type="number" min="1" value="1" class="form-control quantity-input" 
                       style="width: 70px;" data-price="${price}">
            </td>
            <td>$${price}</td>
            <td>
                <button class="btn btn-danger btn-sm remove-product">
                    Eliminar
                </button>
            </td>
        `;
        
        if (productList) {
            productList.appendChild(row);
            
            // Agregar event listener para eliminar producto
            row.querySelector('.remove-product').addEventListener('click', function() {
                row.remove();
                updateTotal();
            });
            
            // Agregar event listener para actualizar cantidad
            row.querySelector('.quantity-input').addEventListener('change', function() {
                updateTotal();
            });
            
            updateTotal();
        }
    }
    
    // Actualizar total
    function updateTotal() {
        if (!totalAmount) return;
        
        let total = 0;
        const quantityInputs = document.querySelectorAll('.quantity-input');
        
        quantityInputs.forEach(input => {
            const quantity = parseInt(input.value);
            const price = parseFloat(input.getAttribute('data-price'));
            total += quantity * price;
        });
        
        totalAmount.value = total.toFixed(2);
    }
    
    // Resetear formulario
    function resetForm() {
        const form = document.getElementById('purchase-form');
        if (!form) return;
        
        form.reset();
        if (productList) productList.innerHTML = '';
        productCounter = 0;
        if (totalAmount) totalAmount.value = '0.00';
    }
    
    // Cargar datos de ejemplo (para la función de editar)
    function loadDummyPurchaseData(id) {
        resetForm();
        
        // Datos de ejemplo según el ID
        if (id === '1') {
            addProductToList('P001', 'Zapatos de cuero negro talla 40', '45.00');
            addProductToList('P003', 'Sandalias de cuero negro talla 38', '35.00');
        } else if (id === '2') {
            addProductToList('P002', 'Zapatos de cuero marrón talla 42', '50.00');
        } else {
            addProductToList('P005', 'Mocasines de cuero marrón talla 41', '55.00');
        }
    }
});