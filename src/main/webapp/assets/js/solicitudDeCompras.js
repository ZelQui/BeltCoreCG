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

    // ✅ Validar si el insumo ya fue agregado
    const filas = document.querySelectorAll('#tablaInsumos tr');
    for (let fila of filas) {
        const idExistente = fila.querySelector('input[name="idInsumo[]"]').value;
        if (idExistente === insumoId) {
            Swal.fire('Advertencia', 'Este insumo ya fue agregado.', 'warning');
            return;
        }
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


//-------------------------------------------------------------------------------------------------------
// Función para abrir el modal de editar solicitud y cargar los datos
function editarSolicitud(idCompra) {
    fetch(BASE_URL + "/OrdenDeCompraServlet?accion=obtenerDetalles&idCompra=" + idCompra)
        .then(response => response.json())
        .then(data => {
            const tbody = document.querySelector("#tablaEditarInsumos");
            tbody.innerHTML = "";

            const originalData = [];

            data.insumos.forEach(insumo => {
                const row = document.createElement("tr");
                row.dataset.idinsumo = insumo.idInsumo;
                row.dataset.db = "true"; // MARCA COMO DE BASE DE DATOS
                row.innerHTML = `
                    <td>
                        ${insumo.nombre}
                        <input type="hidden" name="idInsumo[]" value="${insumo.idInsumo}" />
                    </td>
                    <td>
                        <input type="number" name="cantidad[]" value="${insumo.cantidad}" class="form-control" required />
                    </td>
                    <td>
                        <button type="button" class="btn btn-danger btn-sm" onclick="eliminarFila(this)">Eliminar</button>
                    </td>
                `;
                tbody.appendChild(row);

                // Guardar estado original
                originalData.push({
                    idInsumo: String(insumo.idInsumo),
                    cantidad: String(insumo.cantidad).trim()
                });
            });

            // Establecer el ID de la compra a editar
            document.querySelector("#formEditarSolicitud input[name='idCompra']").value = idCompra;

            // Guardar datos originales como atributo en el formulario
            document.getElementById("formEditarSolicitud").dataset.original = JSON.stringify(originalData);

            // Mostrar el modal
            const modal = new bootstrap.Modal(document.getElementById("modalEditarSolicitud"));
            modal.show();
        })
        .catch(error => {
            console.error("Error al obtener detalles de la solicitud:", error);
            Swal.fire("Error", "No se pudieron cargar los datos.", "error");
        });
}

//-------------------------------------------------------------------------------------------------------
// Función para agregar un insumo desde el formulario al modal de edición
function agregarInsumoEditar() {
    const selectInsumo = document.getElementById('insumoEditarInput');
    const inputCantidad = document.getElementById('cantidadEditarInput');
    const tabla = document.getElementById('tablaEditarInsumos');

    const idInsumo = selectInsumo.value;
    const nombreInsumo = selectInsumo.options[selectInsumo.selectedIndex]?.text;
    const cantidad = inputCantidad.value;

    if (!idInsumo || !cantidad || cantidad <= 0) {
        Swal.fire("Advertencia", "Seleccione un insumo y una cantidad válida.", "warning");
        return;
    }

    // Verifica si el insumo ya está en la tabla
    const filas = tabla.querySelectorAll('tr');
    for (let fila of filas) {
        const idExistente = fila.dataset.idinsumo;
        if (idExistente === idInsumo) {
            Swal.fire("Advertencia", "Este insumo ya ha sido agregado.", "warning");
            return;
        }
    }

    // Crea una nueva fila
    const nuevaFila = document.createElement('tr');
    nuevaFila.dataset.idinsumo = idInsumo;
    nuevaFila.dataset.nuevo = "true"; // MARCA COMO NUEVO
    nuevaFila.innerHTML = `
        <td>
            ${nombreInsumo}
            <input type="hidden" name="idInsumo[]" value="${idInsumo}" />
        </td>
        <td>
            ${cantidad}
            <input type="hidden" name="cantidad[]" value="${cantidad}" />
        </td>
        <td>
            <button type="button" class="btn btn-danger btn-sm" onclick="eliminarFila(this)">Eliminar</button>
        </td>
    `;

    tabla.appendChild(nuevaFila);

    // Limpia los campos
    selectInsumo.value = "";
    inputCantidad.value = "";
}


//-------------------------------------------------------------------------------------------------------
// Función auxiliar para normalizar listas (convierte a string y elimina espacios)
function normalizarLista(lista) {
    return lista.map(item => ({
        idInsumo: String(item.idInsumo).trim(),
        cantidad: String(item.cantidad).trim()
    }));
}

//-------------------------------------------------------------------------------------------------------
// Validar cambios antes de enviar el formulario
document.getElementById("formEditarSolicitud").addEventListener("submit", function (e) {
    const form = e.target;
    const original = normalizarLista(JSON.parse(form.dataset.original || "[]"));

    const filas = form.querySelectorAll("#tablaEditarInsumos tr");
    const actuales = normalizarLista(Array.from(filas).map(fila => {
        const inputCantidad = fila.querySelector("input[name='cantidad[]']");
        const idInsumo = fila.dataset.idinsumo;
        return {
            idInsumo: idInsumo,
            cantidad: inputCantidad ? inputCantidad.value : ""
        };
    }));

    const sonIguales = (a, b) => {
        if (a.length !== b.length) return false;
        const ordenadosA = [...a].sort((x, y) => x.idInsumo - y.idInsumo);
        const ordenadosB = [...b].sort((x, y) => x.idInsumo - y.idInsumo);
        return ordenadosA.every((val, i) =>
            val.idInsumo === ordenadosB[i].idInsumo &&
            val.cantidad === ordenadosB[i].cantidad
        );
    };

    if (sonIguales(original, actuales)) {
        e.preventDefault();
        Swal.fire("Advertencia", "No se realizaron cambios en la solicitud.", "warning");
    }
});

//-------------------------------------------------------------------------------------------------------
// Función para eliminar una fila del modal y en BD si ya existe
function eliminarFila(btn) {
    const row = btn.closest('tr');
    const idInsumo = row.dataset.idinsumo;
    const esNuevo = row.dataset.nuevo === "true";
    const idCompra = document.querySelector("#formEditarSolicitud input[name='idCompra']").value;

    Swal.fire({
        title: "¿Estás seguro?",
        text: "Este insumo será eliminado de la solicitud.",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: "Sí, eliminar",
        cancelButtonText: "Cancelar"
    }).then(result => {
        if (!result.isConfirmed) return;

        if (esNuevo) {
            row.remove();
            Swal.fire("Eliminado", "El insumo nuevo fue eliminado del formulario.", "success");
        } else {
            // Eliminar en la BD
            fetch(`${BASE_URL}/OrdenDeCompraServlet?accion=eliminarInsumo&idCompra=${idCompra}&idInsumo=${idInsumo}`, {
                method: "POST"
            })
                .then(response => response.json())
                .then(data => {
                    if (data.success) {
                        row.remove();
                        Swal.fire("Eliminado", "El insumo fue eliminado correctamente.", "success");
                    } else {
                        Swal.fire("Error", "No se pudo eliminar el insumo.", "error");
                    }
                })
                .catch(error => {
                    console.error("Error al eliminar el insumo:", error);
                    Swal.fire("Error", "Ocurrió un problema al conectar con el servidor.", "error");
                });
        }
    });
}


// ---------------------------------------------------------------------------------------------------------------
// Funcion anular compra
function anularSolicitud(idCompra) {
    Swal.fire({
        title: '¿Estás seguro?',
        text: 'La solicitud será anulada y no podrá editarse.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, anular',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            // Redireccionar a un servlet con el ID
            window.location.href = BASE_URL + "/OrdenDeCompraServlet?accion=anularCompra&idCompra=" + idCompra;
        }
    });
}

// -----------------------------------------------------------------------------------------------------------------
// Filtro por estados
function filtrarPorEstado() {
    const filtro = document.getElementById("filtroEstado").value.toUpperCase();
    const filas = document.querySelectorAll("#tablaCompras tbody tr");

    filas.forEach(fila => {
        const estado = fila.querySelector("td:nth-child(3)").textContent.toUpperCase();
        if (filtro === "" || estado === filtro) {
            fila.style.display = "";
        } else {
            fila.style.display = "none";
        }
    });
}

// ----------------------------------------------------------------------------------------------------------------
function verSolicitud(idCompra) {
    fetch(BASE_URL + "/OrdenDeCompraServlet?accion=obtenerDetalles&idCompra=" + idCompra)
        .then(response => response.json())
        .then(data => {
            const tbody = document.getElementById("tablaVerInsumos");
            tbody.innerHTML = "";

            if (!data.insumos || data.insumos.length === 0) {
                const row = document.createElement("tr");
                row.innerHTML = `<td colspan="2" class="text-center">No hay insumos registrados.</td>`;
                tbody.appendChild(row);
            } else {
                data.insumos.forEach(insumo => {
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td>${insumo.nombre}</td>
                        <td>${insumo.cantidad}</td>
                    `;
                    tbody.appendChild(row);
                });
            }

            const modal = new bootstrap.Modal(document.getElementById("modalVerSolicitud"));
            modal.show();
        })
        .catch(error => {
            console.error("Error al obtener detalles de la solicitud:", error);
            Swal.fire("Error", "No se pudieron cargar los detalles de la solicitud.", "error");
        });
}



