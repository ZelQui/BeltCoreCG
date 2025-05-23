package development.team.DAO;

import development.team.DTO.InsumoCantidadUnidad;
import development.team.Models.*;
import development.team.Utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.*;
import development.team.Utils.DataBaseUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecetaDAO {
    private static final DataSource dataSource = DataBaseUtil.getDataSource();

    // Obtener producto por ID (solo datos de Producto)
    // Obtener receta por idProducto
    public static Receta obtenerRecetaPorIdProducto(int idProducto) {
        String sql = "SELECT id_receta, id_producto, id_insumo, cantidad_requerida FROM recetas WHERE id_producto = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idProducto);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Receta receta = new Receta();
                    receta.setIdReceta(rs.getInt("id_receta"));
                    // Crear objeto Producto con solo el ID (puedes cargar más datos si quieres)
                    Producto producto = new Producto();
                    producto.setIdProducto(rs.getInt("id_producto"));
                    receta.setProducto(producto);
                    // Crear objeto Insumo con solo el ID
                    Insumo insumo = new Insumo();
                    insumo.setIdInsumo(rs.getInt("id_insumo"));
                    receta.setInsumo(insumo);
                    // Obtener cantidad requerida como BigDecimal
                    receta.setCantidadRequerida(rs.getBigDecimal("cantidad_requerida"));

                    return receta;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al obtener receta por ID de producto: " + e.getMessage());
        }
        return null; // si no encuentra receta
    }

    public static Map<Integer, List<InsumoCantidadUnidad>> obtenerRecetasMapeadas() {
        Map<Integer, List<InsumoCantidadUnidad>> recetasMap = new HashMap<>();

        String sql = "SELECT r.id_producto, i.nombre AS insumo, r.cantidad_requerida AS cantidad, " +
                " i.unidad_medida AS unidad " +
                "FROM recetas r JOIN insumos i ON r.id_insumo = i.id_insumo";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int idProducto = rs.getInt("id_producto");
                String nombreInsumo = rs.getString("insumo");
                double cantidad = rs.getDouble("cantidad");
                String unidad = rs.getString("unidad");

                InsumoCantidadUnidad insumoDTO = new InsumoCantidadUnidad(nombreInsumo, cantidad, unidad);
                recetasMap.computeIfAbsent(idProducto, k -> new ArrayList<>()).add(insumoDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recetasMap;
    }

}
