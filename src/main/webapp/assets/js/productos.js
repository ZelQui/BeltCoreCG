// Función para inicializar eventos de la página de productos
function inicializarEventosProductos() {
    const btnProducto = document.getElementById('btnProducto');
    const btnCategoria = document.getElementById('btnCategoria');
  
    const modalProducto = document.getElementById('modal-producto');
    const modalCategoria = document.getElementById('modal-categoria');
    
    const formProducto = document.getElementById('form-producto');
    const formCategoria = document.getElementById('form-categoria');
    
    // Verificar si los elementos existen antes de agregar event listeners
    if (!btnProducto || !btnCategoria || !modalProducto || !modalCategoria || !formProducto || !formCategoria) {
        console.error('No se encontraron todos los elementos necesarios para inicializar eventos de productos');
        return;
    }
    
    // Botones cerrar modal (X)
    const cerrarBotones = document.querySelectorAll('.cerrar-modal');
    
    // Función para ocultar todos los modales
    const ocultarModales = () => {
      modalProducto.classList.add('oculto');
      modalCategoria.classList.add('oculto');
    };
  
    // Mostrar modales
    btnProducto.addEventListener('click', () => {
      ocultarModales(); // Primero oculta todos
      modalProducto.classList.remove('oculto'); // Luego muestra el modal de producto
    });
  
    btnCategoria.addEventListener('click', () => {
      ocultarModales(); // Primero oculta todos
      modalCategoria.classList.remove('oculto'); // Luego muestra el modal de categoría
    });
  
    // Botones X para cerrar modales
    cerrarBotones.forEach(boton => {
      boton.addEventListener('click', ocultarModales);
    });
  
    // Botones cancelar
    const cancelarProducto = formProducto.querySelector('.cancelar');
    const cancelarCategoria = formCategoria.querySelector('.cancelar');
  
    if (cancelarProducto) {
        cancelarProducto.addEventListener('click', ocultarModales);
    }
    
    if (cancelarCategoria) {
        cancelarCategoria.addEventListener('click', ocultarModales);
    }
  
    // Cerrar modal al hacer clic fuera del contenido
    window.addEventListener('click', (e) => {
      if (e.target === modalProducto) {
        modalProducto.classList.add('oculto');
      }
      if (e.target === modalCategoria) {
        modalCategoria.classList.add('oculto');
      }
    });
  
    // Opcional: ocultar modales al enviar formularios
    formProducto.addEventListener('submit', (e) => {
      e.preventDefault();
      alert('Producto agregado');
      ocultarModales();
    });
  
    formCategoria.addEventListener('submit', (e) => {
      e.preventDefault();
      alert('Categoría agregada');
      ocultarModales();
    });
    
    console.log('Eventos de productos inicializados correctamente');
}

// Al cargar la página directamente, inicializar eventos
document.addEventListener('DOMContentLoaded', inicializarEventosProductos);

// Exponer la función para que pueda ser llamada desde script.js
if (typeof window !== 'undefined') {
    window.inicializarEventosProductos = inicializarEventosProductos;
}