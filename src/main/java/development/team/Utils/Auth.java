package development.team.Utils;

import development.team.DAO.UsuarioDAO;
import development.team.Models.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class Auth {
    private final UsuarioDAO userdao = new UsuarioDAO(); // Instanciamos directamente

    /**
     * Inicia sesión y almacena el usuario en la sesión HTTP.
     * @param username Nombre de usuario.
     * @param password Contraseña del usuario.
     * @param request Petición HTTP.
     * @return true si las credenciales son correctas, false si son incorrectas.
     */
    public boolean login(String username, String password, HttpServletRequest request) {
        if (userdao.validarCredenciales(username, password)) {
            Usuario user = userdao.obtenerUsuarioSesion(username);
            HttpSession session = request.getSession();
            session.setAttribute("usuario", user);
            System.out.println("Auth: Inicio de sesión exitoso");
            return true; // Inicio de sesión exitoso
        }
        System.out.println("Auth: Credenciales incorrectas");
        return false; // Credenciales incorrectas
    }

    /**
     * Cierra la sesión eliminando los datos de usuario.
     * @param request Petición HTTP.
     * @return true si la sesión existía y fue cerrada, false si no había sesión activa.
     */
    public boolean logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Obtener sesión sin crear una nueva
        if (session != null) {
            session.invalidate();
            return true; // Cierre de sesión exitoso
        }
        return false; // No había sesión activa
    }
}

