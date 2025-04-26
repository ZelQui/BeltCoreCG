package development.team.Controllers;

import development.team.Utils.Auth;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private Auth authService = new Auth(); // Instanciamos la clase Auth

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (authService.login(username, password, req)) {
            System.out.println("Éxito: redirigir al dashboard");
            resp.sendRedirect("views/menu.jsp"); // Éxito: redirigir al dashboard
        } else {
            System.out.println("Error: mensaje Credenciales incorrectas");
            resp.sendRedirect("views/index.jsp"); // Error: mensaje Credenciales incorrectas
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // Redirigir GET a POST para mayor seguridad
    }
}

