// Verifica si hay suficiente stock de insumos para el producto seleccionado
function hayStockSuficiente(productoId, cantidad) {
  return recetas[productoId].every(insumo => {
    const requerido = insumo.cantidad * cantidad;
    return stockInsumos[insumo.insumo] >= requerido;
  });
}

// Limpia campos del modal de generación
function limpiarCamposModal() {
  document.getElementById("producto-select").value = "";
  document.getElementById("cantidad-producir").value = "";
}

// Limpia y oculta el modal de generación
function limpiarModalGenerar() {
  document.getElementById("producto-select").value = "";
  document.getElementById("cantidad-producir").value = "";
  document.getElementById("tabla-insumos-container").classList.add("oculto");
  document.getElementById("tabla-insumos-body").innerHTML = "";
  document.getElementById("mensaje-insumos").innerHTML = "";
  document.querySelector("#modal-generar .modal-contenido").classList.remove("expandido");
}

// Actualiza la tabla de insumos según producto seleccionado y cantidad
function actualizarTablaInsumos() {
  const productoId = document.getElementById("producto-select").value;
  const cantidad = parseFloat(document.getElementById("cantidad-producir").value);
  const contenedorTabla = document.getElementById("tabla-insumos-container");
  const cuerpoTabla = document.getElementById("tabla-insumos-body");
  const mensajeInsumos = document.getElementById("mensaje-insumos");
  const modalContenido = document.querySelector("#modal-generar .modal-contenido");

  mensajeInsumos.innerHTML = "";

  if (!productoId || isNaN(cantidad) || cantidad <= 0 || !recetas[productoId]) {
    contenedorTabla.classList.add("oculto");
    cuerpoTabla.innerHTML = "";
    modalContenido.classList.remove("expandido");
    return;
  }

  cuerpoTabla.innerHTML = "";
  let hayInsuficiente = false;
  let mensajes = [];

  recetas[productoId].forEach(insumo => {
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

  contenedorTabla.classList.remove("oculto");
  modalContenido.classList.add("expandido");

  evaluarEstadoBotonGenerar();
}

function evaluarEstadoBotonGenerar() {
  const productoId = document.getElementById("producto-select").value;
  const cantidad = parseFloat(document.getElementById("cantidad-producir").value);
  const hayInsumoInsuficiente = document.querySelector(".badge-info") !== null;
  const boton = document.getElementById("btn-Generar");

  const cantidadValida = !isNaN(cantidad) && cantidad >= 1;
  const productoSeleccionado = productoId !== "";

  const habilitar = productoSeleccionado && cantidadValida && !hayInsumoInsuficiente;
  boton.disabled = !habilitar;
}

// Inicializa eventos al cargar la página
window.onload = () => {
  // Validación para "cantidad-producir"
  const inputCantidad = document.getElementById("cantidad-producir");

  // Evitar caracteres inválidos al escribir
  inputCantidad.addEventListener("keydown", (e) => {
    const tecla = e.key;

    // Permitir: control, navegación, borrar
    const teclasPermitidas = ["Backspace", "ArrowLeft", "ArrowRight", "Tab", "Delete"];
    if (teclasPermitidas.includes(tecla) || e.ctrlKey || e.metaKey) return;

    // Bloquear si la tecla no es un número
    if (!/^[0-9]$/.test(tecla)) {
      e.preventDefault();
      return;
    }

    // Si es el primer dígito y es cero, bloquear
    if (inputCantidad.value.length === 0 && tecla === "0") {
      e.preventDefault();
    }
  });

  // Evitar pegar valores inválidos
  inputCantidad.addEventListener("paste", (e) => {
    const pasted = e.clipboardData.getData("text");
    if (!/^[1-9][0-9]*$/.test(pasted)) {
      e.preventDefault();
    }
  });

  // Validar el valor final cuando se modifica (por seguridad)
  inputCantidad.addEventListener("input", () => {
    const valor = inputCantidad.value;

    // Si está vacío o es menor a 1, limpiar
    if (!/^[1-9][0-9]*$/.test(valor)) {
      inputCantidad.value = "";
    }
  });

  // Otros listeners que ya tienes
  document.getElementById("producto-select").addEventListener("change", actualizarTablaInsumos);
  inputCantidad.addEventListener("input", actualizarTablaInsumos);
  document.getElementById("btnNuevaProduccion").addEventListener("click", () => {
    limpiarModalGenerar();
    document.getElementById("modal-generar").classList.remove("oculto");
  });

  document.querySelectorAll(".cerrar-modal, .cancelar").forEach(btn => {
    btn.addEventListener("click", () => {
      document.getElementById("modal-generar").classList.add("oculto");
      limpiarModalGenerar();
    });
  });

  inputCantidad.addEventListener("input", evaluarEstadoBotonGenerar);
  document.getElementById("producto-select").addEventListener("change", evaluarEstadoBotonGenerar);

};
