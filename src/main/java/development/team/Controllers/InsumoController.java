package development.team.Controllers;

import com.google.gson.Gson;
import development.team.DAO.InsumoDAO;
import development.team.Models.Insumo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "insumo", urlPatterns = {"/insumo"})
public class InsumoController extends HttpServlet {

    InsumoDAO insumoDAO = new InsumoDAO();
    Insumo insumo = new Insumo();
    int IdInsumo = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        if ("get".equals(accion)) {
            IdInsumo = Integer.parseInt(req.getParameter("idInsumo"));
            insumo = insumoDAO.obtenerInsumoPorId(IdInsumo);
            System.out.println(insumo.toString());

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            PrintWriter out = resp.getWriter();
            out.print(new Gson().toJson(insumo));
            out.flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accion = req.getParameter("accion");

        switch (accion) {
            case "Listar":
                List<Insumo> lista = insumoDAO.listarInsumos();
                req.setAttribute("insumos", lista);
                break;
            case "Registrar":
                IdInsumo = Integer.parseInt(req.getParameter("idInsumo"));
                if (insumoDAO.existeInsumo(IdInsumo)) {
                    System.out.println("El insumo ya está registrado.");
                } else {
                    //AGREGAR Insumo PARAMETROS
                    insumo.setIdInsumo(IdInsumo);
                    insumo.setNombre(req.getParameter("nombre"));
                    insumo.setDescripcion(req.getParameter("descripcion"));
                    insumo.setCantidadStock(0);
                    insumo.setPrecioUnitario(new BigDecimal(req.getParameter("precioUnitario")));
                    insumo.setUnidadMedida(req.getParameter("unidadMedida"));
                    System.out.println(insumo);
                    System.out.printf("Se ha registrado el Insumo con ID: " + IdInsumo);
                }
                resp.sendRedirect(req.getContextPath() + "/app/insumos");
                return;
            case "Actualizar":
                IdInsumo = Integer.parseInt(req.getParameter("idInsumo"));
                //MAS PARAMETROS
                insumo.setIdInsumo(IdInsumo);
                insumo.setNombre(req.getParameter("nombreEdit"));
                insumo.setDescripcion(req.getParameter("descripcionEdit"));
                insumo.setPrecioUnitario(new BigDecimal(req.getParameter("precioUnitarioEdit")));
                insumo.setUnidadMedida(req.getParameter("unidadMedidaEdit"));
                System.out.println(insumo);
                //ACTUALIZAR INSUMO
                if (insumoDAO.actualizarInsumo(insumo)){
                    System.out.printf("Se ha actualizado correctamente el Insumo con ID: " + IdInsumo);
                } else {
                    System.out.printf("Ocurrió un error al actualizar el Insumo con ID: " + IdInsumo);
                }
                resp.sendRedirect(req.getContextPath() + "/app/insumos");
                return;
            default:
                break;
        }
    }
}