// Datos simulados (en un caso real estos datos vendrían de una base de datos)
let producciones = [
  { id: 1, producto: "Correa negra", fecha: "2025-05-10 14:30:00", cantidad: 100, usuario: "Juan Pérez" },
  { id: 2, producto: "Correa marrón", fecha: "2025-05-11 09:45:00", cantidad: 50, usuario: "Ana López" },
  { id: 3, producto: "Correa negra", fecha: "2025-05-12 16:20:00", cantidad: 75, usuario: "Carlos Martínez" },
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
    fila.innerHTML = `
      <td>${p.id}</td>
      <td>${p.producto}</td>
      <td>${p.fecha}</td>
      <td>${p.cantidad}</td>
      <td>${p.usuario}</td>
      <td>
        <button class="btn view" onclick="verDetalle(${p.id})"><i class="fas fa-eye"></i></button>
        <button class="btn edit" onclick="editarProduccion(${p.id})"><i class="fas fa-edit"></i></button>
      </td>
    `;
    tablaProducciones.appendChild(fila);
  });
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
  limpiarCamposModal(); // Limpiar campos antes de mostrar el modal
  document.getElementById("modal-generar").classList.remove("oculto");
});

// Cerrar los modales
document.querySelectorAll(".cerrar-modal, .cancelar").forEach(btn => {
  btn.addEventListener("click", () => {
    document.getElementById("modal-generar").classList.add("oculto");
    document.getElementById("modal-editar").classList.add("oculto");
  });
});

// Manejar la creación de una nueva producción
document.getElementById("form-generar").addEventListener("submit", (e) => {
  e.preventDefault();
  
  const producto = document.getElementById("producto-select").value;
  const cantidad = document.getElementById("cantidad-producir").value;
  const usuario = "Juan Pérez"; // Simulación de usuario logueado

  const nuevoId = producciones.length ? producciones[producciones.length - 1].id + 1 : 1;
  const nuevaProduccion = {
    id: nuevoId,
    producto,
    fecha: new Date().toISOString().replace("T", " ").slice(0, 19),
    cantidad: parseInt(cantidad),
    usuario
  };

  producciones.push(nuevaProduccion);
  mostrarProducciones();
  limpiarCamposModal(); // Limpiar para evitar que aparezcan datos previos
  document.getElementById("modal-generar").classList.add("oculto");
});

// Abrir el modal para editar una producción
function editarProduccion(id) {
  const produccion = producciones.find(p => p.id === id);

  document.getElementById("editar-id").value = produccion.id;
  document.getElementById("editar-producto").value = produccion.producto;
  document.getElementById("editar-cantidad").value = produccion.cantidad;

  document.getElementById("modal-editar").classList.remove("oculto");
}

// Mostrar los detalles de la producción en una alerta
// Mostrar los detalles de la producción en un mensaje de SweetAlert
function verDetalle(id) {
  const produccion = producciones.find(p => p.id === id);
  if (produccion) {
    Swal.fire({
      title: 'Detalles de Producción',
      html: `
        <strong>Producto:</strong> ${produccion.producto}<br>
        <strong>Fecha de Producción:</strong> ${produccion.fecha}<br>
        <strong>Cantidad Producida:</strong> ${produccion.cantidad}<br>
        <strong>Usuario:</strong> ${produccion.usuario}
      `,
      icon: 'info',
      confirmButtonText: 'Cerrar'
    });
  }
}


// Guardar cambios en una producción
document.getElementById("form-editar").addEventListener("submit", (e) => {
  e.preventDefault();

  const id = parseInt(document.getElementById("editar-id").value);
  const producto = document.getElementById("editar-producto").value;
  const cantidad = parseInt(document.getElementById("editar-cantidad").value);

  const produccion = producciones.find(p => p.id === id);
  produccion.producto = producto;
  produccion.cantidad = cantidad;
  produccion.fecha = new Date().toISOString().replace("T", " ").slice(0, 19); // Actualiza fecha

  mostrarProducciones();
  document.getElementById("modal-editar").classList.add("oculto");
});

// Filtros en tiempo real
document.getElementById("filtro-nombre").addEventListener("input", mostrarProducciones);
document.getElementById("filtro-usuario").addEventListener("change", mostrarProducciones);

// Inicializar
window.onload = () => {
  cargarUsuarios();
  mostrarProducciones();
};
