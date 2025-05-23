package development.team.DAO;

import development.team.Utils.DataBaseUtil;
import development.team.Models.Insumo;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class InsumoDAO {
    private static final DataSource dataSource = DataBaseUtil.getDataSource();

    public boolean agregarInsumo(Insumo insumo) {
        String sql = "INSERT INTO insumos (nombre, descripcion, cantidad_stock, precio_unitario, unidad_medida) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, insumo.getNombre());
            ps.setString(2, insumo.getDescripcion());
            ps.setInt(3, insumo.getCantidadStock());
            ps.setBigDecimal(4, insumo.getPrecioUnitario());
            ps.setString(5, insumo.getUnidadMedida());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean existeInsumo(int IdInsumo) {
        String sql = "SELECT COUNT(*) FROM insumos WHERE id_insumo = ?";
        boolean existe = false;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, IdInsumo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    existe = rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al verificar si existe el insumo ID: " + IdInsumo + " - " + e.getMessage());
        }

        return existe;
    }

    public Insumo obtenerInsumoPorId(int id) {
        String sql = "SELECT * FROM insumos WHERE id_insumo = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapearInsumo(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Insumo> listarInsumos() {
        List<Insumo> lista = new ArrayList<>();
        String sql = "SELECT * FROM insumos";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearInsumo(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean actualizarInsumo(Insumo insumo) {
        String sql = "UPDATE insumos SET nombre = ?, descripcion = ?, cantidad_stock = ?, precio_unitario = ?, unidad_medida = ? WHERE id_insumo = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, insumo.getNombre());
            ps.setString(2, insumo.getDescripcion());
            ps.setInt(3, insumo.getCantidadStock());
            ps.setBigDecimal(4, insumo.getPrecioUnitario());
            ps.setString(5, insumo.getUnidadMedida());
            ps.setInt(6, insumo.getIdInsumo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Map<String, Double> obtenerStockInsumos() {
        Map<String, Double> stockMap = new HashMap<>();

        String sql = "SELECT nombre, cantidad_stock FROM insumos";
        try (Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nombre = rs.getString("nombre");
                double cantidad = rs.getDouble("cantidad_stock");
                stockMap.put(nombre, cantidad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stockMap;
    }

    private Insumo mapearInsumo(ResultSet rs) throws SQLException {
        Insumo insumo = new Insumo();
        insumo.setIdInsumo(rs.getInt("id_insumo"));
        insumo.setNombre(rs.getString("nombre"));
        insumo.setDescripcion(rs.getString("descripcion"));
        insumo.setCantidadStock(rs.getInt("cantidad_stock"));
        insumo.setPrecioUnitario(rs.getBigDecimal("precio_unitario"));
        insumo.setUnidadMedida(rs.getString("unidad_medida"));
        return insumo;
    }
}
