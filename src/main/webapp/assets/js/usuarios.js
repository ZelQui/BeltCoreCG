// Definir la función
function initializeUserFunctions() {
    // Aquí tus funciones y eventos relacionados a usuarios
    console.log('initializeUserFunctions ejecutado correctamente');
    
    // Elementos del DOM
    const addBtn = document.querySelector('.add-user');
    const userModal = document.getElementById('userModal');
    const closeButtons = document.querySelectorAll('.close');
    const cancelButtons = document.querySelectorAll('.cancel');
    const searchInput = document.querySelector('.search-user');
    const userTable = document.querySelector('.user-table');
    const userForm = document.getElementById('userForm');
    const applyFiltersBtn = document.getElementById('applyFilters');
    const submitBtn = userForm ? userForm.querySelector('button[type="submit"]') : null;
    
    console.log('Estado de elementos DOM:');
    console.log('- addBtn:', !!addBtn);
    console.log('- userModal:', !!userModal);
    console.log('- closeButtons:', closeButtons ? closeButtons.length : 0);
    console.log('- cancelButtons:', cancelButtons ? cancelButtons.length : 0);
    console.log('- userForm:', !!userForm);
    console.log('- submitBtn:', !!submitBtn);
    
    // Verificar si estamos en la página correcta
    if (!userTable) {
        console.log('No se encontró la tabla de usuarios. No estamos en la página correcta.');
        return;
    }
    
    console.log('Página de usuarios detectada, inicializando componentes...');
    
    // Si el modal existe, establecer su estilo display none por defecto
    if (userModal) {
        userModal.style.display = 'none';
    }
    
    // Configurar botón de añadir usuario
    if (addBtn) {
        console.log('Configurando botón añadir usuario');
        addBtn.addEventListener('click', function() {
            if (userForm) userForm.reset();
            if (userModal) {
                userModal.style.display = 'block';
                console.log('Modal abierto desde botón añadir');
            } else {
                console.error('No se encontró el modal de usuario');
            }
        });
    }
    
    // Configurar botones de cierre (X) en modal
    if (closeButtons && closeButtons.length > 0) {
        console.log('Configurando botones cerrar');
        closeButtons.forEach(btn => {
            btn.addEventListener('click', function() {
                if (userModal) {
                    userModal.style.display = 'none';
                    console.log('Modal cerrado con botón X');
                }
            });
        });
    }
    
    // Configurar botones cancelar en modal
    if (cancelButtons && cancelButtons.length > 0) {
        console.log('Configurando botones cancelar');
        cancelButtons.forEach(btn => {
            btn.addEventListener('click', function() {
                if (userModal) {
                    userModal.style.display = 'none';
                    console.log('Modal cerrado con botón cancelar');
                }
            });
        });
    }
    
    // Cerrar modal al hacer clic fuera
    window.addEventListener('click', function(event) {
        if (event.target === userModal) {
            userModal.style.display = 'none';
            console.log('Modal cerrado al hacer clic fuera');
        }
    });
    
    // Configurar búsqueda
    if (searchInput) {
        console.log('Configurando búsqueda');
        searchInput.addEventListener('keyup', function() {
            const searchTerm = this.value.toLowerCase();
            if (userTable) {
                const rows = userTable.querySelectorAll('tbody tr');
                rows.forEach(row => {
                    row.style.display = row.textContent.toLowerCase().includes(searchTerm) ? '' : 'none';
                });
            }
            console.log('Búsqueda realizada:', searchTerm);
        });
    }
    
    // Filtro de roles y estado
    if (applyFiltersBtn) {
        console.log('Configurando filtros');
        applyFiltersBtn.addEventListener('click', function() {
            const roleFilter = document.getElementById('roleFilter');
            const statusFilter = document.getElementById('statusFilter');
            
            if (userTable) {
                const rows = userTable.querySelectorAll('tbody tr');
                
                rows.forEach(row => {
                    let showRow = true;
                    
                    // Filtro de rol
                    if (roleFilter && roleFilter.value) {
                        const roleBadge = row.querySelector('.role-badge');
                        if (roleBadge) {
                            const role = roleBadge.textContent.toLowerCase();
                            if (!role.includes(roleFilter.value.toLowerCase())) {
                                showRow = false;
                            }
                        }
                    }
                    
                    // Filtro de estado
                    if (statusFilter && statusFilter.value && showRow) {
                        const statusCell = row.cells[4]; // La quinta celda es el estado
                        if (statusCell) {
                            const statusBadge = statusCell.querySelector('.role-badge');
                            if (statusBadge) {
                                const status = statusBadge.textContent.toLowerCase();
                                const isActive = status.includes('activo');
                                
                                if ((statusFilter.value === 'active' && !isActive) || 
                                    (statusFilter.value === 'inactive' && isActive)) {
                                    showRow = false;
                                }
                            }
                        }
                    }
                    
                    row.style.display = showRow ? '' : 'none';
                });
                
                console.log('Filtros aplicados - Rol:', roleFilter ? roleFilter.value : 'ninguno', 
                            'Estado:', statusFilter ? statusFilter.value : 'ninguno');
            }
        });
    }
    
    // Delegación de eventos para botones de acción en la tabla
    userTable.addEventListener('click', function(e) {
        const target = e.target.closest('button');
        if (!target) return;
        
        const row = target.closest('tr');
        if (!row) return;
        
        const userId = row.dataset.id;
        
        // Editar usuario
        if (target.classList.contains('edit') || target.querySelector('.fa-edit')) {
            if (userForm) {
                // Aquí podrías cargar los datos del usuario en el formulario
                // Por ahora solo abrimos el modal
                userForm.reset();
                
                // Opcionalmente, cambiar el título del modal para edición
                const modalTitle = userModal.querySelector('.modal-header h2');
                if (modalTitle) modalTitle.textContent = 'Editar Usuario';
            }
            
            if (userModal) {
                userModal.style.display = 'block';
                console.log('Editando usuario:', userId);
            }
        }
        
        // Ver detalles de usuario
        if (target.classList.contains('view') || target.querySelector('.fa-eye')) {
            alert('Ver detalles completos del usuario #' + userId);
            console.log('Viendo usuario:', userId);
        }
        
        // Resetear contraseña
        if (target.classList.contains('reset-password') || target.querySelector('.fa-key')) {
            alert('Funcionalidad de resetear contraseña no disponible en este ejemplo (Usuario #' + userId + ')');
            console.log('Reseteando contraseña para usuario:', userId);
        }
        
        // Desactivar usuario
        if (target.classList.contains('deactivate') || target.querySelector('.fa-user-slash')) {
            if (confirm('¿Está seguro de que desea desactivar el usuario #' + userId + '?')) {
                alert('Usuario #' + userId + ' desactivado');
            }
            console.log('Desactivando usuario:', userId);
        }
        
        // Activar usuario
        if (target.classList.contains('activate') || target.querySelector('.fa-user-check')) {
            if (confirm('¿Está seguro de que desea activar el usuario #' + userId + '?')) {
                alert('Usuario #' + userId + ' activado');
            }
            console.log('Activando usuario:', userId);
        }
    });
    
    // Envío de formulario de usuario
    if (userForm) {
        console.log('Configurando formulario de usuario');
        userForm.addEventListener('submit', function(e) {
            e.preventDefault();
            
            // Aquí podrías agregar validación del formulario
            
            alert('Usuario guardado correctamente');
            userModal.style.display = 'none';
            console.log('Formulario de usuario enviado');
        });
    }
    
    console.log('Funciones de usuarios inicializadas correctamente');
}

// Ejecutarla de inmediato si detecta que ya está cargado en DOM (para SPA o carga con fetch)
(function () {
    const observer = new MutationObserver(() => {
        const userTable = document.querySelector('.user-table');
        if (userTable) {
            console.log('Página de usuarios detectada por MutationObserver, inicializando...');
            initializeUserFunctions();
            observer.disconnect(); // Detener observación una vez encontrado
        }
    });

    observer.observe(document.getElementById('contenido'), {
        childList: true,
        subtree: true,
    });
})();

// Exponer globalmente por si necesitas llamarla manualmente también
if (typeof window !== 'undefined') {
    window.initializeUserFunctions = initializeUserFunctions;
}