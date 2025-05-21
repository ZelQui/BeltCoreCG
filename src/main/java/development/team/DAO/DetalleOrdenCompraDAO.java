package development.team.DAO;

import development.team.DTO.OrdenInsumoDTO;
import development.team.Models.DetalleCompra;
import development.team.Utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleOrdenCompraDAO {

    private static final DataSource dataSource = DataBaseUtil.getDataSource();

    // CREATE
    public static int registrarDetalleCompra(DetalleCompra detalleCompra) {
        String sql = "INSERT INTO detalle_compra (id_compra, id_insumo, cantidad) VALUES (?, ?, ?)";
        int idDetalleCompra = -1;

        try (Connection con = dataSource.getConnection()) {

            // Ejecuta el insert normalmente
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, detalleCompra.getCompra().getIdCompra());
                ps.setInt(2, detalleCompra.getInsumo().getIdInsumo());
                ps.setInt(3, detalleCompra.getCantidad());

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            idDetalleCompra = rs.getInt(1);
                        }
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al registrar el Detalle de Compra: " + e.getMessage());
        }

        return idDetalleCompra;
    }

    // LISTAR DETALLER POR ORDEN
    public static List<OrdenInsumoDTO> obtenerDetalleCompra(int idCompra) {
        String sql = "SELECT d.id_detalle_compra, d.cantidad, i.id_insumo, i.nombre " +
                "FROM detalle_compra d " +
                "JOIN insumos i ON d.id_insumo = i.id_insumo " +
                "WHERE d.id_compra = ?";

        ArrayList<OrdenInsumoDTO> detalleCompras = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){
             ps.setInt(1, idCompra);
             ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                OrdenInsumoDTO detalleCompra = new OrdenInsumoDTO();
                detalleCompra.setIdDetalleCompra(rs.getInt("d.id_detalle_compra"));
                detalleCompra.setCantidad(rs.getInt("d.cantidad"));
                detalleCompra.setIdInsumo(rs.getInt("i.id_insumo"));
                detalleCompra.setNombreInsumo(rs.getString("i.nombre"));
                detalleCompras.add(detalleCompra);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return detalleCompras;
    }

    // ELIMINAR DETALLES POR COMPRA
    public static boolean eliminarDetallePorCompraEInsumo(int idCompra, int idInsumo) {
        String sql = "DELETE FROM detalle_compra WHERE id_compra = ? AND id_insumo = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idCompra);
            ps.setInt(2, idInsumo);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean actualizarCantidad(DetalleCompra detalle) {
        String sql = "UPDATE detalle_compra SET cantidad = ? WHERE id_compra = ? AND id_insumo = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, detalle.getCantidad());
            ps.setInt(2, detalle.getCompra().getIdCompra());
            ps.setInt(3, detalle.getInsumo().getIdInsumo());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ANULAR DETALLES SEGUN EL ORDEN DE COMPRA
    public static boolean anularDetallesCompra (int idCompra) {
        String sql = "UPDATE detalle_compra SET estado = ? WHERE id_compra = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, "ANULADA");
            ps.setInt(2, idCompra);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Compra " + idCompra + " anular correctamente.");
                return true;
            } else {
                System.err.println("Error al anular compra con ID: " + idCompra);
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al anular compra " + idCompra + ": " + e.getMessage());
            return false;
        }
    }


}
