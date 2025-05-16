package development.team.Utils;

import development.team.DAO.UsuarioDAO;
import development.team.Models.LoginResult;
import development.team.Models.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class Auth {
    private final UsuarioDAO userdao = new UsuarioDAO(); // Instanciamos directamente

    /**
     * Inicia sesión y almacena el usuario en la sesión HTTP.
     * @param userLogin Nombre de usuario.
     * @param contrasena Contraseña del usuario.
     * @param request Petición HTTP.
     * @return true si las credenciales son correctas, false si son incorrectas.
     */
    public LoginResult login(String userLogin, String contrasena, HttpServletRequest request) {
        Boolean validar = userdao.validarCredenciales(userLogin, contrasena);

        if (validar) {
            Usuario user = userdao.obtenerUsuarioSesion(userLogin);

            if (userdao.verificarEstadoActivo(user)) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", user);

                if (userdao.SettearUsuario(user.getIdUsuario())) {
                    return new LoginResult(true, "success", "Bienvenido", "Has iniciado sesión correctamente.");
                }
                return new LoginResult(false, "error", "Error interno", "Error al configurar usuario en la base de datos.");
            } else {
                return new LoginResult(false, "warning", "Usuario suspendido", "Comunicate con el administrador.");
            }
        }
        return new LoginResult(false, "error", "Credenciales incorrectas", "Usuario o contraseña incorrectos.");
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

