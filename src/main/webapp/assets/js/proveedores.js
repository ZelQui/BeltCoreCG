document.addEventListener('DOMContentLoaded', function() {
    initializeProviderFunctions();
});

// Variables globales para edición
let currentProviderId = null;
let isEditing = false;

// Función principal que inicializa todos los eventos
function initializeProviderFunctions() {
    console.log('Inicializando funciones de proveedores...');
    
    // Elementos del DOM
    const addBtn = document.querySelector('.add-provider');
    const modal = document.getElementById('providerModal');
    const closeBtn = document.querySelector('.close');
    const cancelBtn = document.querySelector('.cancel');
    const searchInput = document.querySelector('.search-provider');
    const providerTable = document.querySelector('.provider-table');
    const providerForm = document.getElementById('providerForm');
    const modalTitle = document.querySelector('.modal-header h2');
    
    // Configurar botón de añadir
    if (addBtn) {
        addBtn.addEventListener('click', () => {
            if (providerForm) providerForm.reset();
            if (modal) modal.style.display = 'block';
            // Resetear modo edición
            isEditing = false;
            currentProviderId = null;
            if (modalTitle) modalTitle.textContent = 'Añadir Proveedor';
            console.log('Botón añadir clickeado');
        });
    }
    
    // Configurar cierre de modal
    if (closeBtn) {
        closeBtn.addEventListener('click', () => {
            if (modal) modal.style.display = 'none';
            console.log('Modal cerrado');
        });
    }
    
    // Configurar botón cancelar
    if (cancelBtn) {
        cancelBtn.addEventListener('click', () => {
            if (modal) modal.style.display = 'none';
            console.log('Acción cancelada');
        });
    }
    
    // Cierre de modal al hacer clic fuera
    window.addEventListener('click', event => {
        if (modal && event.target === modal) {
            modal.style.display = 'none';
        }
    });
    
    // Configurar búsqueda
    if (searchInput) {
        searchInput.addEventListener('keyup', function() {
            const searchTerm = this.value.toLowerCase();
            if (providerTable) {
                const rows = providerTable.querySelectorAll('tbody tr');
                rows.forEach(row => {
                    row.style.display = row.textContent.toLowerCase().includes(searchTerm) ? '' : 'none';
                });
            }
            console.log('Búsqueda realizada:', searchTerm);
        });
    }
    
    // Configurar eventos para botones existentes y futuros usando delegación de eventos
    document.addEventListener('click', function(e) {
        // Botones de editar
        if (e.target && e.target.classList.contains('edit')) {
            const row = e.target.closest('tr');
            if (row && modal) {
                // Establecer modo edición
                isEditing = true;
                currentProviderId = row.dataset.id;
                
                // Cambiar título del modal
                if (modalTitle) modalTitle.textContent = 'Editar Proveedor';
                
                // Llenar el formulario con los datos existentes
                if (providerForm) {
                    // Obtener los datos de la fila
                    const cells = row.cells;
                    const nameInput = document.getElementById('providerName');
                    const emailInput = document.getElementById('email');
                    const phoneInput = document.getElementById('phone');
                    const addressInput = document.getElementById('address');
                    
                    if (nameInput) nameInput.value = cells[1].textContent.trim();
                    if (phoneInput) phoneInput.value = cells[2].textContent.trim();
                    if (emailInput) emailInput.value = cells[3].textContent.trim();
                    if (addressInput) addressInput.value = cells[4].textContent.trim();
                }
                
                modal.style.display = 'block';
                console.log('Editando proveedor:', currentProviderId);
            }
        }
        
        // Botones de ver
        if (e.target && e.target.classList.contains('view')) {
            alert('Ver detalles completos del proveedor');
            console.log('Viendo proveedor:', e.target.closest('tr')?.dataset.id);
        }
        
        // Botones de desactivar
        if (e.target && e.target.classList.contains('deactivate')) {
            if (confirm('¿Está seguro que desea eliminar este proveedor?')) {
                const row = e.target.closest('tr');
                if (row) {
                    row.remove();
                    alert('Proveedor eliminado correctamente');
                }
            }
            console.log('Eliminando proveedor:', e.target.closest('tr')?.dataset.id);
        }
    });
    
    // Configurar envío del formulario
    if (providerForm) {
        providerForm.addEventListener('submit', function(e) {
            e.preventDefault();
            
            // Obtener valores del formulario
            const name = document.getElementById('providerName')?.value || '';
            const email = document.getElementById('email')?.value || '';
            const phone = document.getElementById('phone')?.value || '';
            const address = document.getElementById('address')?.value || '';
            
            if (isEditing && currentProviderId) {
                // Modo edición: Actualizar fila existente
                const row = document.querySelector(`tr[data-id="${currentProviderId}"]`);
                if (row) {
                    row.cells[1].textContent = name;
                    row.cells[2].textContent = phone;
                    row.cells[3].textContent = email;
                    row.cells[4].textContent = address;
                    
                    alert('Proveedor actualizado correctamente');
                }
            } else {
                // Modo añadir: Crear nueva fila
                const tbody = document.querySelector('.provider-table tbody');
                if (tbody) {
                    // Crear nueva fila con ID único
                    const newId = Date.now();
                    const newRow = document.createElement('tr');
                    newRow.setAttribute('data-id', newId);
                    
                    newRow.innerHTML = `
                        <td>${newId}</td>
                        <td>${name}</td>
                        <td>${phone}</td>
                        <td>${email}</td>
                        <td>${address}</td>
                        <td>
                            <button class="btn view" title="Ver"><i class="fas fa-eye"></i></button>
                            <button class="btn edit" title="Editar"><i class="fas fa-edit"></i></button>
                            <button class="btn deactivate" title="Eliminar"><i class="fas fa-user-slash"></i></button>
                        </td>
                    `;
                    
                    tbody.appendChild(newRow);
                    alert('Proveedor añadido correctamente');
                }
            }
            
            // Cerrar modal y resetear formulario
            if (modal) modal.style.display = 'none';
            if (providerForm) providerForm.reset();
            
            // Resetear modo edición
            isEditing = false;
            currentProviderId = null;
            
            console.log('Formulario enviado');
        });
    }
    
    console.log('Funciones de proveedores inicializadas correctamente');
}

// Para reinicializar cuando se carga desde el sidebar
// Opción 1: Evento personalizado
document.addEventListener('sidebarContentLoaded', initializeProviderFunctions);

// Opción 2: Verificar periódicamente si los elementos están disponibles
function checkAndInitialize() {
    if (document.querySelector('.provider-table') || document.querySelector('.add-provider')) {
        initializeProviderFunctions();
        clearInterval(checkInterval);
    }
}

const checkInterval = setInterval(checkAndInitialize, 500);
setTimeout(() => clearInterval(checkInterval), 10000);

// Opción 3: Si usas un framework o biblioteca específica
// Si usas jQuery
if (typeof $ !== 'undefined') {
    $(document).on('ajaxComplete', function() {
        initializeProviderFunctions();
    });
}

// Función que puedes llamar manualmente después de cargar contenido dinámicamente
function onContentLoaded() {
    initializeProviderFunctions();
}