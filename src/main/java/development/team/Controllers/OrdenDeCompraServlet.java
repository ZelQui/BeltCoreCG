package development.team.Controllers;


import com.google.gson.Gson;
import development.team.DTO.OrdenInsumoDTO;
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
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;

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
            case "editar":
                editarSolicitudCompra(request, response);
                break;
            case "obtenerDetalles":
                obtenerDetallesPorCompra(request, response);
                break;
            case "eliminarInsumo":
                eliminarInsumo(request, response);
                break;
            case "anularCompra":
                anularCompra(request, response);
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

    private void editarSolicitudCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idCompra = Integer.parseInt(request.getParameter("idCompra"));
            String[] idInsumos = request.getParameterValues("idInsumo[]");
            String[] cantidades = request.getParameterValues("cantidad[]");

            // 1. Obtener los insumos ya registrados en la base de datos
            List<OrdenInsumoDTO> detallesExistentes = DetalleOrdenCompraDAO.obtenerDetalleCompra(idCompra);
            Set<Integer> insumosYaRegistrados = new HashSet<>();
            for (OrdenInsumoDTO detalle : detallesExistentes) {
                insumosYaRegistrados.add(detalle.getIdInsumo());
            }

            // 2. Agregar solo los nuevos insumos que no están ya en la BD
            if (idInsumos != null && cantidades != null && idInsumos.length == cantidades.length) {
                Compra compra = new Compra();
                compra.setIdCompra(idCompra);

                for (int i = 0; i < idInsumos.length; i++) {
                    int idInsumo = Integer.parseInt(idInsumos[i]);
                    int cantidad = Integer.parseInt(cantidades[i]);

                    Insumo insumo = new Insumo();
                    insumo.setIdInsumo(idInsumo);

                    DetalleCompra detalle = new DetalleCompra();
                    detalle.setCompra(compra);
                    detalle.setInsumo(insumo);
                    detalle.setCantidad(cantidad);

                    if (insumosYaRegistrados.contains(idInsumo)) {
                        // Actualizar cantidad si ya existe
                        DetalleOrdenCompraDAO.actualizarCantidad(detalle); // Este método debes implementarlo
                    } else {
                        // Registrar nuevo
                        DetalleOrdenCompraDAO.registrarDetalleCompra(detalle);
                    }
                }
            }

            response.sendRedirect(request.getContextPath() + "/app/solicitudDeCompra");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al editar la solicitud.");
        }
    }

    private void obtenerDetallesPorCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idCompra = Integer.parseInt(request.getParameter("idCompra"));

            // Obtener los detalles de la compra
            List<OrdenInsumoDTO> detalles = DetalleOrdenCompraDAO.obtenerDetalleCompra(idCompra);

            // Crear una lista de insumos para enviar como respuesta
            List<Map<String, Object>> insumosList = new ArrayList<>();
            for (OrdenInsumoDTO detalle : detalles) {
                Map<String, Object> insumoMap = new HashMap<>();
                insumoMap.put("idInsumo", detalle.getIdInsumo());
                insumoMap.put("nombre", detalle.getNombreInsumo());
                insumoMap.put("cantidad", detalle.getCantidad());

                insumosList.add(insumoMap);
            }

            // Convertir la lista a JSON y enviarla como respuesta
            try (PrintWriter out = response.getWriter()) {
                String jsonResponse = new Gson().toJson(Collections.singletonMap("insumos", insumosList));
                out.write(jsonResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener los detalles de la solicitud.");
        }

    }

    // Método para eliminar un insumo de una solicitud de compra específica
    private void eliminarInsumo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int idCompra = Integer.parseInt(request.getParameter("idCompra"));
            int idInsumo = Integer.parseInt(request.getParameter("idInsumo"));

            // Eliminar el detalle del insumo solo de la compra especificada
            boolean eliminado = DetalleOrdenCompraDAO.eliminarDetallePorCompraEInsumo(idCompra, idInsumo);

            // Enviar respuesta JSON
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print("{\"success\": " + eliminado + "}");
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al eliminar el insumo.");
        }
    }

    // Anular Compra
    private void anularCompra(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCompra = Integer.parseInt(request.getParameter("idCompra"));

        boolean actualizado = OrdenDeCompraDAO.anularCompra(idCompra);

        if (actualizado) {
            request.getSession().setAttribute("mensaje", "Compra anulada correctamente.");
        } else {
            request.getSession().setAttribute("mensaje", "Error al anular la compra.");
        }

        response.sendRedirect(request.getContextPath() + "/app/solicitudDeCompra"); // Cambia esto según tu JSP principal
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
