// Datos simulados
let inventario = [
  {
    id: 1,
    imagen: contextPath + "/assets/img/correa.png",
    nombre: "Correa Negra",
    descripcion: "Correa de cuero negro",
    precioventa: 25.00,
    stock: 10,
    categoria: "Cinturones"
  },
  {
    id: 2,
    imagen: contextPath + "/assets/img/correa.png",
    nombre: "Correa Marrón",
    descripcion: "Correa de cuero marrón",
    precioventa: 22.50,
    stock: 6,
    categoria: "Cinturones"
  },
  {
    id: 3,
    imagen: contextPath + "/assets/img/correa.png",
    nombre: "Cartera Negra",
    descripcion: "Cartera de cuero sintético",
    precioventa: 35.00,
    stock: 2,
    categoria: "Carteras"
  }
];

// Para evitar mostrar la alerta más de una vez
const productosAlertaMostrada = new Set();

// Función para mostrar alerta
function mostrarAlertaStockMinimo(item) {
  Swal.fire({
    icon: 'warning',
    title: 'Stock mínimo',
    text: `El producto "${item.nombre}" tiene un stock bajo (${item.stock} unidades).`,
    confirmButtonColor: '#d33'
  });
}

// Función para mostrar el inventario
function mostrarInventario() {
  const tabla = document.getElementById("tabla-inventario");
  tabla.innerHTML = "";

  const filtroNombre = document.getElementById("filtro-nombre").value.toLowerCase();
  const filtroCategoria = document.getElementById("filtro-categoria").value;

  const filtrado = inventario.filter(item => {
    const coincideNombre = item.nombre.toLowerCase().includes(filtroNombre);
    const coincideCategoria = filtroCategoria ? item.categoria === filtroCategoria : true;
    return coincideNombre && coincideCategoria;
  });

  filtrado.forEach(item => {
    const fila = document.createElement("tr");
    const esStockBajo = item.stock <= 5;

    // Alerta solo si es la primera carga y aún no se ha mostrado para ese ID
    if (esStockBajo && !productosAlertaMostrada.has(item.id) && window.alertasIniciales) {
      mostrarAlertaStockMinimo(item);
      productosAlertaMostrada.add(item.id);
    }

    if (esStockBajo) {
      fila.style.backgroundColor = "#f8d7da";
    }

    fila.innerHTML = `
      <td>${item.id}</td>
      <td><img src="${item.imagen}" alt="img" width="50"></td>
      <td>${item.nombre}</td>
      <td>${item.descripcion}</td>
      <td>S/. ${item.precioventa.toFixed(2)}</td>
      <td>
        ${item.stock}
        ${esStockBajo ? '<i class="fas fa-exclamation-triangle" style="color: red;" title="Stock mínimo alcanzado"></i>' : ''}
      </td>
      <td>${item.categoria}</td>
    `;

    // Alerta al hacer clic sobre la fila de un producto con stock bajo
    if (esStockBajo) {
      fila.addEventListener("click", () => {
        mostrarAlertaStockMinimo(item);
      });
    }

    tabla.appendChild(fila);
  });

  if (filtrado.length === 0) {
    const fila = document.createElement("tr");
    fila.innerHTML = `<td colspan="7" style="text-align: center;">No se encontraron productos con esos filtros.</td>`;
    tabla.appendChild(fila);
  }
}

// Filtro en tiempo real
document.getElementById("filtro-nombre").addEventListener("input", () => {
  // Al filtrar ya no se mostrarán alertas automáticamente
  window.alertasIniciales = false;
  mostrarInventario();
});

document.getElementById("filtro-categoria").addEventListener("change", () => {
  // Al filtrar ya no se mostrarán alertas automáticamente
  window.alertasIniciales = false;
  mostrarInventario();
});

// Inicializar
window.onload = () => {
  window.alertasIniciales = true; // Bandera para permitir alertas iniciales
  mostrarInventario();
};
