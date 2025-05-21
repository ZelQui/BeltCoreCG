package development.team.Controllers;


import development.team.Models.Compra;
import development.team.Models.DetalleCompra;
import development.team.Models.Insumo;
import development.team.DAO.DetalleOrdenCompraDAO;
import development.team.DAO.OrdenDeCompraDAO;
import development.team.Models.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "OrdenDeCompraServlet", urlPatterns = {"/OrdenDeCompraServlet"})
public class OrdenDeCompraServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        System.out.println("Accion: " + accion);

        switch (accion) {
            case "registrar":
                registrarSolicitudCompra(request, response);
                break;
        }
    }

    private void registrarSolicitudCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario");

            // Registrar la compra
            Compra compra = new Compra();
            compra.setUsuario(usuario);
            compra.setFechaSolicitud(LocalDateTime.now());
            compra.setEstado("PENDIENTE");

            int idCompra = OrdenDeCompraDAO.registrarCompra(compra);

            String[] idInsumos = request.getParameterValues("idInsumo[]");
            String[] cantidades = request.getParameterValues("cantidad[]");

            if (idInsumos != null && cantidades != null && idInsumos.length == cantidades.length) {
                for (int i = 0; i < idInsumos.length; i++) {
                    int idInsumo = Integer.parseInt(idInsumos[i]);
                    int cantidad = Integer.parseInt(cantidades[i]);

                    Insumo insumo = new Insumo();
                    insumo.setIdInsumo(idInsumo);

                    DetalleCompra detalle = new DetalleCompra();
                    compra.setIdCompra(idCompra);
                    detalle.setCompra(compra);

                    detalle.setInsumo(insumo);
                    detalle.setCantidad(cantidad);

                    DetalleOrdenCompraDAO.registrarDetalleCompra(detalle);
                }
            }


            response.sendRedirect(request.getContextPath() + "/app/solicitudDeCompra");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al registrar la solicitud.");
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
