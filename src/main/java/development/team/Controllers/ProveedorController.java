package development.team.Controllers;

import development.team.DAO.ProveedorDAO;
import development.team.Models.Proveedor;
import development.team.Models.TipoDocumento;
import development.team.Models.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ProveedorController", urlPatterns = {"/ProveedorController"})
public class ProveedorController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        System.out.println("Accion: " + accion);

        switch (accion) {
            case "registrar":
                registrarProveedor(request, response);
                break;
            case "editar":
                editarProveedor(request, response);
                break;
            case "cambiarEstado":
                estadoProveedor(request, response);
                break;
        }
    }

    // REGISTRAR PROVEEDOR
    private void registrarProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mensajeRegistro = "";
        String iconRegistro = "";

        HttpSession session = request.getSession();

        String nombre = request.getParameter("nombre");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        String direccion = request.getParameter("direccion");

        int ID_TIPO_DOCUMENTO = 4;
        TipoDocumento tipoDocumentoRuc = new TipoDocumento();
        tipoDocumentoRuc.setIdTipoDocumento(ID_TIPO_DOCUMENTO);

        String numeroRuc = request.getParameter("ruc");
        String cuentaInterbancaria = request.getParameter("cuentaInterbancaria");

        // Registro
        Proveedor proveedor = new Proveedor(nombre, telefono, correo, direccion, tipoDocumentoRuc, numeroRuc, cuentaInterbancaria);
        int idProveedor = ProveedorDAO.registrarProveedor(proveedor);


        // Verificar si el proveedor se registró con éxito
        if (idProveedor > 0) {
            mensajeRegistro = "Proveedor Crear Exitosamente";
            iconRegistro = "success";
        } else {
            mensajeRegistro = "Error al Crear Proveedor";
            iconRegistro = "error";
        }
        session.setAttribute("mensajeRegistro", mensajeRegistro);
        session.setAttribute("iconRegistro", iconRegistro);
        response.sendRedirect(request.getContextPath() + "/app/proveedores");
    }

    // EDITAR PROVEEDOR
    private void editarProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String mensaje = "";
        String icon = "";

        // Crear Session
        HttpSession session = request.getSession();

        // Datos
        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        String nombre = request.getParameter("nuevoNombre");
        String telefono = request.getParameter("nuevoTelefono");
        String correo = request.getParameter("nuevoCorreo");
        String direccion = request.getParameter("nuevaDireccion");

        // Buscar Usuario id para verificar los datos para editar
        Proveedor proveedorActual = ProveedorDAO.buscarProveedor(idProveedor);

        // Si no se edita nada, no se actualizara
        if (nombre.equals(proveedorActual.getNombre()) && telefono.equals(proveedorActual.getTelefono()) && correo.equals(proveedorActual.getCorreo()) && direccion.equals(proveedorActual.getDireccion())) {
            mensaje = "No se edito ningun campo correspondiente";
            icon = "warning";
            session.setAttribute("mensajeEditado", mensaje);
            session.setAttribute("icon", icon);
            response.sendRedirect(request.getContextPath() + "/app/proveedores");
            return;
        }

        // Si algun campo esta vacio se mantiene los datos del Usuario
        if (nombre.isEmpty()) {
            nombre = proveedorActual.getNombre();
        }
        if (telefono.isEmpty()) {
            telefono = proveedorActual.getTelefono();
        }
        if (correo.isEmpty()) {
            correo = proveedorActual.getCorreo();
        }
        if (direccion.isEmpty()) {
            direccion = proveedorActual.getDireccion();
        }

        // Usuario a editar
        Proveedor proveedorEditar = new Proveedor(idProveedor, nombre, telefono, correo, direccion);
        boolean editadoProveedor = ProveedorDAO.actualizarProveedor(proveedorEditar);

        if (editadoProveedor) {
            mensaje = "Editado Correctamente";
            icon = "success";
            session.setAttribute("mensajeEditado", mensaje);
            session.setAttribute("icon", icon);
        } else {
            mensaje = "Error al Editar el Proveedor";
            icon = "error";
            session.setAttribute("mensajeEditado", mensaje);
            session.setAttribute("icon", icon);
        }

        response.sendRedirect(request.getContextPath() + "/app/proveedores");
    }

    // ESTADOS PROVEEDOR
    private void estadoProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        int nuevoEstado = Integer.parseInt(request.getParameter("nuevoEstado"));

        ProveedorDAO.cambiarEstadoProveedor(idProveedor, nuevoEstado);
        response.sendRedirect(request.getContextPath() + "/app/proveedores");
    }


        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
