package development.team.DAO;

import development.team.Models.DetalleCompra;
import development.team.Utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

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
    public static ArrayList<DetalleCompra> obtenerDetalleCompra(int idCompra) {
        String sql = "SELECT * FROM detalle_compra WHERE idCompra = ?";
        ArrayList<DetalleCompra> detalleCompras = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){
             ps.setInt(1, idCompra);
             ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DetalleCompra detalleCompra = new DetalleCompra();
                detalleCompra.setIdDetalleCompra(rs.getInt("id_detalle_compra"));
                detalleCompra.getCompra().setIdCompra(rs.getInt("idCompra"));
                detalleCompra.getInsumo().setIdInsumo(rs.getInt("idInsumo"));
                detalleCompra.setCantidad(rs.getInt("cantidad"));
                detalleCompra.setPrecioUnitario(rs.getDouble("precio_unitario"));
                detalleCompra.setTotal(rs.getDouble("total"));
                detalleCompras.add(detalleCompra);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return detalleCompras;
    }
}
