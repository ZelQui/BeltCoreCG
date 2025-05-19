// Abre el modal de edición y carga los datos del rol
function openEditModal(id, nombre, descripcion) {
    // Asignar los valores a los campos del formulario
    document.getElementById('roleId').value = id;
    document.getElementById('roleName').value = nombre;
    document.getElementById('roleDescription').value = descripcion;

    // Mostrar el modal
    document.getElementById('roleModal').style.display = 'block';
}

// Cierra el modal
function cerrarModal() {
    document.getElementById('roleModal').style.display = 'none';
}

// Cierra el modal si se hace clic fuera del contenido del modal
window.onclick = function(event) {
    var modal = document.getElementById('roleModal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }
}
