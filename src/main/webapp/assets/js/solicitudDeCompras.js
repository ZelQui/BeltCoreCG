// Abrir el modal
function abrirModalSolicitud() {
    const modal = new bootstrap.Modal(document.getElementById('modalSolicitud'));
    limpiarModal();
    modal.show();
}

// Limpiar campos e insumos al abrir
function limpiarModal() {
    document.getElementById("insumoInput").value = "";
    document.getElementById("cantidadInput").value = "";
    document.getElementById("tablaInsumos").innerHTML = "";
}

// Agrega insumo a la tabla y al formulario
function agregarInsumo() {
    const insumoSelect = document.getElementById("insumoInput");
    const cantidadInput = document.getElementById("cantidadInput");

    const insumoId = insumoSelect.value;
    const cantidad = cantidadInput.value;

    if (!insumoId || !cantidad || parseInt(cantidad) <= 0) {
        Swal.fire('Error', 'Selecciona un insumo y una cantidad válida.', 'error');
        return;
    }

    const insumoNombre = insumoSelect.options[insumoSelect.selectedIndex].text;

    const tableBody = document.getElementById("tablaInsumos");
    const row = document.createElement("tr");

    row.innerHTML = `
        <td>
            ${insumoNombre}
            <input type="hidden" name="idInsumo[]" value="${insumoId}">
        </td>
        <td>
            ${cantidad}
            <input type="hidden" name="cantidad[]" value="${cantidad}">
        </td>
        <td>
            <button type="button" class="btn btn-danger btn-sm" onclick="eliminarInsumo(this)">Eliminar</button>
        </td>
    `;

    tableBody.appendChild(row);
    insumoSelect.value = "";
    cantidadInput.value = "";
}

// Elimina un insumo
function eliminarInsumo(button) {
    button.closest("tr").remove();
}

// Validación antes de enviar
document.getElementById("formSolicitud").addEventListener("submit", function (e) {
    const insumos = document.querySelectorAll('input[name="idInsumo[]"]');
    const cantidades = document.querySelectorAll('input[name="cantidad[]"]');

    if (insumos.length === 0 || cantidades.length === 0) {
        e.preventDefault();
        Swal.fire('Error', 'Agrega al menos un insumo a la solicitud.', 'error');
    }
});
