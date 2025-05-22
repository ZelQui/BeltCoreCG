// Datos simulados (en un caso real estos datos vendrían de una base de datos)
const recetas = {
  "Correa negra": [
    { insumo: "Cuero negro", cantidad: 2, unidad: "m" },
    { insumo: "Hilo negro", cantidad: 0.5, unidad: "rollo" },
    { insumo: "Hebilla metálica", cantidad: 1, unidad: "pieza" },
  ],
  "Correa marrón": [
    { insumo: "Cuero marrón", cantidad: 2, unidad: "m" },
    { insumo: "Hilo marrón", cantidad: 0.5, unidad: "rollo" },
    { insumo: "Hebilla metálica", cantidad: 1, unidad: "pieza" },
  ]
};

const stockInsumos = {
  "Cuero negro": 150,         // metros
  "Hilo negro": 30,           // rollos
  "Hebilla metálica": 200,   // piezas
  "Cuero marrón": 90,
  "Hilo marrón": 20
};



let producciones = [
  { id: 1, producto: "Correa negra", fecha: "2025-05-10 14:30:00", cantidad: 100, usuario: "Juan Pérez", estado: "Finalizado" },
  { id: 2, producto: "Correa marrón", fecha: "2025-05-11 09:45:00", cantidad: 50, usuario: "Ana López", estado: "En proceso" },
  { id: 3, producto: "Correa negra", fecha: "2025-05-12 16:20:00", cantidad: 75, usuario: "Carlos Martínez", estado: "En proceso" },
];


// Datos de usuarios (en un caso real se obtendrían de la base de datos)
let usuarios = [
  "Juan Pérez",
  "Ana López",
  "Carlos Martínez",
  "Luisa Gómez",
  "Pedro Sánchez"
];

// Función para mostrar la lista de producciones en la tabla
function mostrarProducciones() {
  const tablaProducciones = document.getElementById("tabla-producciones");
  tablaProducciones.innerHTML = ""; // Limpiar la tabla

  // Filtros activos
  const filtroProducto = document.getElementById("filtro-nombre").value.toLowerCase();
  const filtroUsuario = document.getElementById("filtro-usuario").value;

  // Filtrar las producciones según los filtros aplicados
  const produccionesFiltradas = producciones.filter(p => {
    const coincideProducto = p.producto.toLowerCase().includes(filtroProducto);
    const coincideUsuario = filtroUsuario ? p.usuario === filtroUsuario : true;
    return coincideProducto && coincideUsuario;
  });

  // Mostrar las producciones filtradas
  produccionesFiltradas.forEach(p => {
    const fila = document.createElement("tr");
    // Determina la clase según el estado
    let badgeClass = "role-badge ";
    switch (p.estado) {
      case "Finalizado":
        badgeClass += "role-green";
        break;
      case "En proceso":
        badgeClass += "role-orange";
        break;
      case "Anulado":
        badgeClass += "role-red";
        break;
      default:
        badgeClass += "role-red"; // fallback
    }
    fila.innerHTML = `
      <td>${p.id}</td>
      <td>${p.producto}</td>
      <td>${p.fecha}</td>
      <td>${p.cantidad}</td>
      <td>${p.usuario}</td>
      <td><span class="${badgeClass}">${p.estado}</span></td>
      <td>
        <button class="btn view" onclick="verDetalle(${p.id})"><i class="fas fa-eye"></i></button>
        <button class="btn edit" onclick="editarProduccion(${p.id})" ${p.estado === "Anulado" ? "disabled" : ""}><i class="fas fa-edit"></i></button>
        <button class="btn delete" onclick="anularProduccion(${p.id})" ${p.estado === "Anulado" ? "disabled" : ""}><i class="fas fa-ban"></i></button>
        <button class="btn finalize" onclick="abrirModalFinalizar(${p.id})" ${p.estado !== "En proceso" ? "disabled" : ""}><i class="fas fa-check"></i></button>
      </td>
    `;
    tablaProducciones.appendChild(fila);
    applyFilters();
  });
}

// ----- Filtrar Producciones -----
function applyFilters() {
  const filtroProducto   = document.getElementById("filtro-nombre").value.toLowerCase();
  const filtroUsuario    = document.getElementById("filtro-usuario").value;
  const filtroEstado     = document.getElementById("statusFilter").value.toLowerCase();

  document.querySelectorAll("#tabla-producciones tr").forEach(row => {
    const producto = row.cells[1].textContent.toLowerCase();
    const usuario  = row.cells[4].textContent;
    const estado   = row.querySelector(".role-badge").textContent.toLowerCase();

    const okProducto = !filtroProducto || producto.includes(filtroProducto);
    const okUsuario  = !filtroUsuario  || usuario === filtroUsuario;
    const okEstado   = !filtroEstado   || estado === filtroEstado;

    row.style.display = (okProducto && okUsuario && okEstado) ? "" : "none";
  });
}

// Eventos para disparar filtrado
document.getElementById("filtro-nombre").addEventListener("input", applyFilters);
document.getElementById("filtro-usuario").addEventListener("change", applyFilters);
document.getElementById("statusFilter").addEventListener("change", applyFilters);

/* mostrar la tabla al seleccionar un producto */

// Funcion para actualziar tablas de insumos
function actualizarTablaInsumos() {
  const producto = document.getElementById("producto-select").value;
  const cantidad = parseFloat(document.getElementById("cantidad-producir").value);
  const contenedorTabla = document.getElementById("tabla-insumos-container");
  const cuerpoTabla = document.getElementById("tabla-insumos-body");
  const mensajeInsumos = document.getElementById("mensaje-insumos");
  const modalContenido = document.querySelector("#modal-generar .modal-contenido");

  mensajeInsumos.innerHTML = "";

  if (!producto || isNaN(cantidad) || cantidad <= 0 || !recetas[producto])  {
    // Oculta la columna derecha y contrae modal
    contenedorTabla.classList.add("oculto");
    cuerpoTabla.innerHTML = "";
    modalContenido.classList.remove("expandido");
    return;
  }

  // Aquí puedes llenar la tabla con la lógica que tengas
  // Por ejemplo:
  cuerpoTabla.innerHTML = ""; // Limpiar tabla

  let hayInsuficiente = false;
  let mensajes = [];

  recetas[producto].forEach(insumo => {
    const cantidadTotal = insumo.cantidad * cantidad;
    const stockDisponible = stockInsumos[insumo.insumo] || 0;
    const insuficiente = cantidadTotal > stockDisponible;
    if (insuficiente) {
      hayInsuficiente = true;
      mensajes.push(`<span class="badge badge-info">⚠ Insumo insuficiente: ${insumo.insumo}</span>`);
    }

    const fila = document.createElement("tr");
    fila.innerHTML = `<td>${insumo.insumo}</td><td>${cantidadTotal.toFixed(2)}</td><td>${insumo.unidad}</td>`;
    cuerpoTabla.appendChild(fila);
  });

  if (hayInsuficiente) {
    mensajeInsumos.innerHTML = mensajes.join("<br>");
  }

  // Mostrar columna derecha y expandir modal
  contenedorTabla.classList.remove("oculto");
  modalContenido.classList.add("expandido");
}




// Función para limpiar los campos del modal de nueva producción
function limpiarCamposModal() {
  document.getElementById("producto-select").value = "";
  document.getElementById("cantidad-producir").value = "";
}

// Cargar los usuarios en el filtro
function cargarUsuarios() {
  const filtroUsuario = document.getElementById("filtro-usuario");
  usuarios.forEach(usuario => {
    const opcion = document.createElement("option");
    opcion.value = usuario;
    opcion.textContent = usuario;
    filtroUsuario.appendChild(opcion);
  });
}

// Abrir el modal para generar una nueva producción
document.getElementById("btnNuevaProduccion").addEventListener("click", () => {
  limpiarModalGenerar(); // Limpiar campos antes de mostrar el modal
  document.getElementById("modal-generar").classList.remove("oculto");
});

// Cerrar los modales
document.querySelectorAll(".cerrar-modal, .cancelar").forEach(btn => {
  btn.addEventListener("click", () => {
    document.getElementById("modal-generar").classList.add("oculto");
    document.getElementById("modal-editar").classList.add("oculto");
    limpiarModalGenerar(); // Limpia campos y oculta la tabla
  });
});

// Manejar la creación de una nueva producción
document.getElementById("form-generar").addEventListener("submit", (e) => {
  e.preventDefault();

  const producto = document.getElementById("producto-select").value;
  const cantidad = document.getElementById("cantidad-producir").value;
  const usuario = "Juan Pérez"; // Simulación de usuario logueado

  const nuevoId = producciones.length ? producciones[producciones.length - 1].id + 1 : 1;

  // ⛔️ Aquí es donde insertamos la validación del stock
  if (!hayStockSuficiente(producto, cantidad)) {
    Swal.fire({
      icon: 'error',
      title: 'Stock insuficiente',
      text: 'No hay suficientes insumos para esta producción.'
    });
    return; // Salir de la función si no hay stock suficiente
  }

const nuevaProduccion = {
  id: nuevoId,
  producto,
  fecha: new Date().toISOString().replace("T", " ").slice(0, 19),
  cantidad: parseInt(cantidad),
  usuario,
  estado: "En proceso" // o "En proceso" según tu lógica
};

  producciones.push(nuevaProduccion);
  descontarInsumos(producto, cantidad);
  mostrarProducciones();
  limpiarModalGenerar();
  document.getElementById("modal-generar").classList.add("oculto");
});


/* Funcion para descontar insumos */
function descontarInsumos(producto, cantidad) {
  recetas[producto].forEach(insumo => {
    const requerido = insumo.cantidad * cantidad;
    if (stockInsumos[insumo.insumo] !== undefined) {
      stockInsumos[insumo.insumo] -= requerido;
    }
  });
}

function hayStockSuficiente(producto, cantidad) {
  return recetas[producto].every(insumo => {
    const requerido = insumo.cantidad * cantidad;
    return stockInsumos[insumo.insumo] >= requerido;
  });
}

// Abrir el modal para editar una producción
function editarProduccion(id) {
  const produccion = producciones.find(p => p.id === id);
  if (!produccion) return;

  // Llenar el formulario con los datos de la producción seleccionada
  document.getElementById("editar-id").value = produccion.id;
  document.getElementById("editar-producto").value = produccion.producto;
  document.getElementById("editar-cantidad").value = produccion.cantidad;

  // Mostrar el modal
  document.getElementById("modal-editar").classList.remove("oculto");
}


// Mostrar los detalles de la producción en una alerta
// Mostrar los detalles de la producción en un mensaje de SweetAlert
function verDetalle(id) {
  const modal = document.getElementById("modal-ver");
  const p = producciones.find(item => item.id === id);
  if (!p) return;

  // Producto
  document.getElementById("verProducto").value = p.producto;

  // Producción
  document.getElementById("verPlaneadas").value   = p.cantidad;
  document.getElementById("verProducidas").value  = p.cantidadReal || 0;
  document.getElementById("verDefectuosas").value = p.defectuosos   || 0;

  // Eficiencia
  const eficiencia = p.cantidad
    ? (( (p.cantidadReal||0) - (p.defectuosos||0) ) / p.cantidad) * 100
    : 0;
  document.getElementById("verEficiencia").value = eficiencia.toFixed(2) + "%";

  // Insumos
  const tbody = document.querySelector("#verTablaInsumos tbody");
  tbody.innerHTML = "";
  let totalPrev = 0, totalReal = 0;
  (recetas[p.producto]||[]).forEach(ins => {
    const prev = ins.cantidad * p.cantidad;
    const real = ins.cantidad * (p.cantidadReal||0);
    totalPrev += prev;
    totalReal += real;
    const tr = document.createElement("tr");
    tr.innerHTML = `
      <td>${ins.insumo}</td>
      <td>${prev.toFixed(2)}</td>
      <td>${real.toFixed(2)}</td>
      <td>${(prev - real).toFixed(2)}</td>
    `;
    tbody.appendChild(tr);
  });
  document.getElementById("verTotalPrevistos").value = totalPrev.toFixed(2);
  document.getElementById("verTotalReales").value   = totalReal.toFixed(2);
  document.getElementById("verMermaTotal").value    = (totalPrev - totalReal).toFixed(2);

  // Mostrar modal
  modal.classList.remove("oculto");
  document.getElementById("cerrarVer").onclick = () => modal.classList.add("oculto");
  document.getElementById("btnCerrarVer").onclick = () => modal.classList.add("oculto");
}



// Guardar cambios en una producción
// Manejar el guardado de cambios de la producción editada
document.getElementById("form-editar").addEventListener("submit", (e) => {
  e.preventDefault();

  const id = parseInt(document.getElementById("editar-id").value);
  const producto = document.getElementById("editar-producto").value;
  const cantidad = parseInt(document.getElementById("editar-cantidad").value);

  const index = producciones.findIndex(p => p.id === id);
  if (index !== -1) {
    producciones[index].producto = producto;
    producciones[index].cantidad = cantidad;
    // Mantiene la misma fecha y usuario
  }

  mostrarProducciones();
  document.getElementById("modal-editar").classList.add("oculto");
});

//Anular produccion
function anularProduccion(id) {
  const produccion = producciones.find(p => p.id === id);
  if (!produccion || produccion.estado !== "En proceso") {
    Swal.fire("No permitido", "Solo se pueden anular producciones en proceso.", "info");
    return;
  }

  Swal.fire({
    title: "¿Estás seguro?",
    text: `¿Deseas anular la producción "${produccion.producto}"?`,
    icon: "warning",
    showCancelButton: true,
    confirmButtonText: "Sí, anular",
    cancelButtonText: "Cancelar"
  }).then((result) => {
    if (result.isConfirmed) {
      produccion.estado = "Anulado";
      mostrarProducciones();
      Swal.fire("¡Anulado!", "La producción ha sido anulada.", "success");
    }
  });
}



// Filtros en tiempo real
document.getElementById("filtro-nombre").addEventListener("input", mostrarProducciones);
document.getElementById("filtro-usuario").addEventListener("change", mostrarProducciones);

// Inicializar
window.onload = () => {
  cargarUsuarios();
  mostrarProducciones();
  document.getElementById("producto-select").addEventListener("change", actualizarTablaInsumos);
  document.getElementById("cantidad-producir").addEventListener("input", actualizarTablaInsumos);

};


function limpiarModalGenerar() {
  document.getElementById("producto-select").value = "";
  document.getElementById("cantidad-producir").value = "";
  document.getElementById("tabla-insumos-container").classList.add("oculto");
  document.getElementById("tabla-insumos-body").innerHTML = "";
  document.getElementById("mensaje-insumos").innerHTML = "";
  document.querySelector("#modal-generar .modal-contenido").classList.remove("expandido"); // <- esta línea es la clave
}

/* a partir de aqui finalizar */
// Abrir modal Finalizar Producción y cargar datos según el ID de producción
function abrirModalFinalizar(idProduccion) {
  const modal = document.getElementById("modalFinalizar");
  // Guarda el id en un data-attribute
  modal.dataset.idProduccion = idProduccion;
  const produccion = producciones.find(p => p.id === idProduccion);
  if (!produccion) return;

  // Llenar campos de solo lectura: Producto y Cantidad Estimada
  document.getElementById("finalizarProducto").value = produccion.producto;
  document.getElementById("cantidadEstimado").value = produccion.cantidad;

  // Limpiar tabla de insumos
  const cuerpoTabla = document.getElementById("tablaInsumosBody");
  cuerpoTabla.innerHTML = "";

  // Cargar insumos según la receta del producto de esta producción
  const receta = recetas[produccion.producto];
  if (receta) {
    receta.forEach(insumo => {
      const cantidadPrevista = insumo.cantidad * produccion.cantidad;
      const fila = document.createElement("tr");
      fila.innerHTML = `
        <td>${insumo.insumo}</td>
        <td>${cantidadPrevista.toFixed(2)}</td>
        <td><input type="number" min="0" value="${cantidadPrevista.toFixed(2)}" class="cantidad-real" /></td>
        <td><span class="merma">0.00</span></td>
      `;
      cuerpoTabla.appendChild(fila);
    });
  }
  document.getElementById("tablaInsumosBody").addEventListener("input", (e) => {
    if (e.target.classList.contains("cantidad-real")) {
      const fila = e.target.closest("tr");
      const cantidadPrevista = parseFloat(fila.children[1].textContent);
      const cantidadReal = parseFloat(e.target.value) || 0;

      const mermaSpan = fila.querySelector(".merma");
      const mermaCalculada = cantidadPrevista - cantidadReal;
      mermaSpan.textContent = mermaCalculada >= 0 ? mermaCalculada.toFixed(2) : "0.00";
    }
});

  // Mostrar modal
  modal.classList.remove("oculto");
}

// Cerrar modal Finalizar Producción
document.getElementById("cerrarFinalizar").addEventListener("click", () => {
  document.getElementById("modalFinalizar").classList.add("oculto");
});

// Cancelar finalización (botón cancelar)
document.getElementById("cancelarFinalizar").addEventListener("click", () => {
  document.getElementById("modalFinalizar").classList.add("oculto");
});

// Mostrar u ocultar detalles de defectos según opción seleccionada
document.querySelectorAll('input[name="productosDefectuosos"]').forEach(radio => {
  radio.addEventListener("change", () => {
    const detalles = document.getElementById("defectosDetalles");
    if (document.querySelector('input[name="productosDefectuosos"]:checked').value === "si") {
      detalles.style.display = "block";
    } else {
      detalles.style.display = "none";
    }
  });
});

// Guardar finalización producción (botón guardar)
document.getElementById("btnGuardarFinalizacion").addEventListener("click", (e) => {
  e.preventDefault();

  const modal = document.getElementById("modalFinalizar");
  const idProduccion = parseInt(modal.dataset.idProduccion, 10);
  const produccion = producciones.find(p => p.id === idProduccion);
  if (!produccion) return;

  // Lee los valores del modal
  const cantidadRealProducida = parseFloat(document.getElementById("cantidadRealProducida").value) || 0;
  const huboDefectuosos      = document.querySelector('input[name="productosDefectuosos"]:checked').value === "si";
  const cantidadDefectuosos  = huboDefectuosos ? parseInt(document.getElementById("cantidadDefectuosos").value) : 0;
  const motivoDefecto        = huboDefectuosos ? document.getElementById("motivoDefecto").value : null;

  // **Guarda en el objeto** para luego mostrar en "ver"
  produccion.cantidadReal   = cantidadRealProducida;
  produccion.defectuosos    = cantidadDefectuosos;
  produccion.motivoDefecto  = motivoDefecto;
  produccion.estado         = "Finalizado";

  // Refresca y cierra
  mostrarProducciones();
  modal.classList.add("oculto");

  Swal.fire({
    icon: 'success',
    title: 'Producción finalizada',
    html: `
      <strong>Producto:</strong> ${produccion.producto}<br>
      <strong>Estimado:</strong> ${produccion.cantidad}<br>
      <strong>Real:</strong> ${cantidadRealProducida}
    `
  });
});







