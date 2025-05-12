package development.team.DAO;

import development.team.Utils.DataBaseUtil;
import development.team.Models.Insumo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            ps.setDouble(4, insumo.getPrecioUnitario());
            ps.setString(5, insumo.getUnidadMedida());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
            ps.setDouble(4, insumo.getPrecioUnitario());
            ps.setString(5, insumo.getUnidadMedida());
            ps.setInt(6, insumo.getIdInsumo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarInsumo(int id) {
        String sql = "DELETE FROM insumos WHERE id_insumo = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Insumo mapearInsumo(ResultSet rs) throws SQLException {
        Insumo insumo = new Insumo();
        insumo.setIdInsumo(rs.getInt("id_insumo"));
        insumo.setNombre(rs.getString("nombre"));
        insumo.setDescripcion(rs.getString("descripcion"));
        insumo.setCantidadStock(rs.getInt("cantidad_stock"));
        insumo.setPrecioUnitario(rs.getDouble("precio_unitario"));
        insumo.setUnidadMedida(rs.getString("unidad_medida"));
        return insumo;
    }
}
