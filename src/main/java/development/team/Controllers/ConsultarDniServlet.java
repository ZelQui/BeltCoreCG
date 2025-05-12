package development.team.Controllers;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@WebServlet("/consultarDni")
public class ConsultarDniServlet extends HttpServlet {
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String TOKEN = "apis-token-14917.dA68liFCZjzW0OEyeMQTZKPvMhWbqXtv"; // Reemplaza por tu token o usa una variable segura

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dni = request.getParameter("dni");
        if (dni == null || dni.length() != 8) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"DNI inválido\"}");
            return;
        }

        HttpRequest apiRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.apis.net.pe/v2/reniec/dni?numero=" + dni))
                .header("Authorization", "Bearer " + TOKEN)
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> apiResponse = null;
        try {
            apiResponse = client.send(apiRequest, HttpResponse.BodyHandlers.ofString());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (apiResponse.statusCode() == 200) {
            response.getWriter().write(apiResponse.body());
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\":\"No se encontraron datos\"}");
        }
    }
}

