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
import java.io.PrintWriter;

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
                break;
        }
    }

    // REGISTRAR PROVEEDOR
    private void registrarProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mensajeRegistro = "";
        String iconRegistro = "";

        HttpSession session = request.getSession();

        // Datos
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        int usuarioId = usuario.getIdUsuario();

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
        int idProveedor = ProveedorDAO.registrarProveedor(proveedor, usuarioId);


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
