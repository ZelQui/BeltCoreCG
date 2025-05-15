package development.team.Controllers;

import development.team.DAO.ProveedorCuentaDAO;
import development.team.DAO.ProveedorDAO;
import development.team.Models.Proveedor;
import development.team.Models.ProveedorCuenta;
import development.team.Models.TipoDocumento;
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
            case "inactivate":
                bloquearProveedor(request, response);
                break;
            case "activate":
                activarProveedor(request, response);
                break;
        }
    }

    // REGISTRAR PROVEEDOR
    private void registrarProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mensajeRegistro = "";
        String iconRegistro = "";

        HttpSession session = request.getSession();

        String numeroRuc = request.getParameter("ruc");
        String nombre = request.getParameter("nombreRazonSocial");
        String telefono = request.getParameter("telefono");
        String domicilioFiscal = request.getParameter("domicilioFiscal");

        // Verificamos si el campo "Domicilio Alterna" está vacío y asignamos null si es así
        String domicilioAlterna = request.getParameter("domicilioAlterna");
        if (domicilioAlterna == null || domicilioAlterna.trim().isEmpty()) {
            domicilioAlterna = null;  // Si está vacío o es null, lo dejamos como null
        }

        int idCuentaBancaria = Integer.parseInt(request.getParameter("idCuentaBancaria"));
        String estadoContribuyente = request.getParameter("estadoContribuyente");
        String numeroCuenta = request.getParameter("numeroCuenta");

        int ID_TIPO_DOCUMENTO = 4;
        TipoDocumento tipoDocumentoRuc = new TipoDocumento();
        tipoDocumentoRuc.setIdTipoDocumento(ID_TIPO_DOCUMENTO);

        // Registro
        Proveedor proveedor = new Proveedor(nombre, numeroRuc, telefono, domicilioFiscal, domicilioAlterna, estadoContribuyente, tipoDocumentoRuc, numeroCuenta);
        int idProveedor = ProveedorDAO.registrarProveedor(proveedor);


        // Verificar si el proveedor se registró con éxito
        if (idProveedor > 0) {

            // Registro Proveedor Cuenta Bancaria
            ProveedorCuenta proveedorCuenta = new ProveedorCuenta(idProveedor, idCuentaBancaria);
            boolean proveedorCuentaBancaria = ProveedorCuentaDAO.registrarProveedorCuenta(proveedorCuenta);

            if (proveedorCuentaBancaria) {
                mensajeRegistro = "Proveedor Creado Exitosamente";
                iconRegistro = "success";
            }
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
        String telefono = request.getParameter("telefono");
        String domicilioAlterna = request.getParameter("domicilioAlterna");

        // Buscar Usuario id para verificar los datos para editar
        Proveedor proveedorActual = ProveedorDAO.buscarProveedor(idProveedor);
        String actualTelefono = proveedorActual.getTelefono() != null ? proveedorActual.getTelefono() : "";
        String actualDomicilioAlterna = proveedorActual.getDomicilioAlterna() != null ? proveedorActual.getDomicilioAlterna() : "";

        // Si no se edita nada, no se actualizara
        if (telefono.equals(actualTelefono) && domicilioAlterna.equals(actualDomicilioAlterna)) {
            mensaje = "No se edito ningun campo correspondiente";
            icon = "warning";
            session.setAttribute("mensajeEditado", mensaje);
            session.setAttribute("icon", icon);
            response.sendRedirect(request.getContextPath() + "/app/proveedores");
            return;
        }

        // Si algun campo esta vacio se mantiene los datos del Usuario
        if (domicilioAlterna.isEmpty()) {
            domicilioAlterna = proveedorActual.getDomicilioAlterna();
        }
        if (telefono.isEmpty()) {
            telefono = proveedorActual.getTelefono();
        }

        // Usuario a editar
        Proveedor proveedorEditar = new Proveedor(idProveedor, telefono, domicilioAlterna);
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

    // BLOQUEAR PROVEEDOR
    private void bloquearProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        boolean inactivado = ProveedorDAO.bloquearProveedor(idProveedor);

        if (inactivado) {
            System.out.println("Proveedor inactivado: ID: " + idProveedor);
            response.sendRedirect(request.getContextPath() + "/app/proveedores");
        } else {
            System.err.println("No se pudo bloquear/inactivar el proveedor: ID: " + idProveedor);
        }
    }

    // ACTIVAR PROVEEDOR
    private void activarProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idProveedor = Integer.parseInt(request.getParameter("idProveedor"));
        boolean activado = ProveedorDAO.activarProveedor(idProveedor);

        if (activado) {
            System.out.println("Proveedor activado: ID: " + idProveedor);
            response.sendRedirect(request.getContextPath() + "/app/proveedores");
        } else {
            System.err.println("No se pudo activar el proveedor: ID: " + idProveedor);
        }
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
