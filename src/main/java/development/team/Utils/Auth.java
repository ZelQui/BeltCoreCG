package development.team.Utils;

import development.team.DAO.UsuarioDAO;
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
    public boolean login(String userLogin, String contrasena, HttpServletRequest request) {
        // Validar credenciales
        Boolean validar = userdao.validarCredenciales (userLogin, contrasena);
        if (validar) {
            Usuario user = userdao.obtenerUsuarioSesion(userLogin);

            if (userdao.verificarEstadoActivo(user)) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", user);

                // SETTEAR USUARIO EN BASE DE DATOS, PARA QUE PUEDA USAR SQL
                if (userdao.SettearUsuario(user.getIdUsuario())) {
                    return true; // Inicio de sesión exitoso
                }
                return false; // Falló al settear usuario
            } else {
                System.out.println("Auth: Usuario inactivado");
                return false;
            }
        }
        System.out.println("Auth: Credenciales incorrectas");
        return false;
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

