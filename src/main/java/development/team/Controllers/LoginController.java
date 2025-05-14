package development.team.Controllers;

import development.team.DAO.ModuloDAO;
import development.team.Models.Modulo;
import development.team.Models.Usuario;
import development.team.Utils.Auth;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private Auth authService = new Auth(); // Instanciamos la clase Auth

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userLogin = request.getParameter("userLogin");
        String contrasena = request.getParameter("contrasena");

        if (authService.login(userLogin, contrasena, request)) {

            Usuario user = (Usuario) request.getSession().getAttribute("usuario");
            int idRol = user.getRol().getIdRol(); // Obtener del modelo de usuario autenticado
            ModuloDAO moduloDAO = new ModuloDAO();
            List<Modulo> modulosUsuario = moduloDAO.obtenerModulosPorRol(user.getRol().getIdRol());
            request.getSession().setAttribute("modulos_usuario", modulosUsuario);
            response.sendRedirect(request.getContextPath() + "/app/inicio");

        } else {
            System.out.println("Error: mensaje Credenciales incorrectas");
            // Error: mensaje Credenciales incorrectas
            response.sendRedirect(request.getContextPath() + "/");
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

