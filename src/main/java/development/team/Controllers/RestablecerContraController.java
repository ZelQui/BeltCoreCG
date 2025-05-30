package development.team.Controllers;

import development.team.DAO.UsuarioDAO;
import java.io.IOException;

import development.team.Models.Usuario;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "RestablecerContraController", urlPatterns = {"/RestablecerContraController"})
public class RestablecerContraController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String accion = request.getParameter("accion");
        response.setContentType("text/plain");

        if ("verificarUsuario".equals(accion)) {
            String correo = request.getParameter("correo");
            String dni = request.getParameter("dni");

            UsuarioDAO dao = new UsuarioDAO();
            boolean existe = dao.validarCorreoYDni(correo, dni);

            if (existe) {
                // Generar código de 6 dígitos
                int codigo = (int) (Math.random() * 900000) + 100000;

                // Guardar código en sesión
                HttpSession session = request.getSession();
                session.setAttribute("dni", dni);
                session.setAttribute("codigo_verificacion", codigo);
                System.out.println("codigo generado: " + codigo);

                // Enviar correo
                String asunto = "Código de Verificación - MarketPlus";
                String mensaje = "Hola, tu código de verificación es: " + codigo;

                try {
                    //enviarCorreo(correo, asunto, mensaje);
                    response.getWriter().write("OK");  // código enviado correctamente
                } catch (Exception e) {
                    e.printStackTrace();
                    response.getWriter().write("ERROR_ENVIO");
                }
            } else {
                response.getWriter().write("NO_ENCONTRADO");
            }

        } else if ("verificarCodigo".equals(accion)) {
            String codigoUsuario = request.getParameter("codigo");
            System.out.println("Codigo recibido: " + codigoUsuario);
            HttpSession session = request.getSession();
            Object codSesion = session.getAttribute("codigo_verificacion");

            if (codSesion != null && codigoUsuario.equals(codSesion.toString())) {
                response.getWriter().write("CODIGO_CORRECTO");
            } else {
                response.getWriter().write("CODIGO_INCORRECTO");
            }
        } else if ("actualizarPassword".equals(accion)) {
            String nuevaContra = request.getParameter("nuevaContra");
            HttpSession session = request.getSession();
            String dni = (String) session.getAttribute("dni");

            response.setContentType("text/plain");

            if (dni != null && nuevaContra != null && !nuevaContra.trim().isEmpty()) {
                UsuarioDAO usuDao = new UsuarioDAO();
                Usuario userRestablecer = usuDao.obtenerUsuarioPorDni(dni);
                System.out.println(userRestablecer);

                if (userRestablecer != null) {
                    boolean actualizado = usuDao.actualizarContrasena(userRestablecer, nuevaContra); // Asegúrate de que este método exista y funcione correctamente

                    response.getWriter().write(actualizado ? "actualizado" : "error_al_actualizar");
                } else {
                    response.getWriter().write("usuario_no_encontrado");
                }
            } else {
                response.getWriter().write("datos_invalidos");
            }
        }
    }

    private void enviarCorreo(String destino, String asunto, String contenido) throws Exception {
        final String remitente = "marketplussystem@gmail.com"; // cámbialo
        final String clave = "womwequcittsljnl";   // contraseña de aplicación (no la normal)

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(remitente));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destino));
        message.setSubject(asunto);
        message.setText(contenido);

        Transport.send(message);
    }
}


