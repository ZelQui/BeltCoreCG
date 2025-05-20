package development.team.DAO;

import development.team.Models.Compra;
import development.team.Utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.*;
import java.util.ArrayList;


public class OrdenDeCompraDAO {

    private static final DataSource dataSource = DataBaseUtil.getDataSource();

    // CREATE
    public static int registrarCompra(Compra compra) {
        String sql = "INSERT INTO compras (id_usuario, estado, fecha_solicitud) VALUES (?, ?, ?)";
        int idCompra = -1;

        try (Connection con = dataSource.getConnection()) {

            // Ejecuta el insert normalmente
            try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, compra.getUsuario().getIdUsuario());
                compra.setEstado("PENDIENTE");
                ps.setString(2, compra.getEstado());
                ps.setTimestamp(3, Timestamp.valueOf(compra.getFechaSolicitud()));

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            idCompra = rs.getInt(1);
                        }
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al registrar la Compra: " + e.getMessage());
        }

        return idCompra;
    }

    // LISTAR ORDENES
    public static ArrayList<Compra> obtenerCompras() {
        String sql = "SELECT * FROM compras";
        ArrayList<Compra> compras = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Compra compra = new Compra();
                compra.setIdCompra(rs.getInt("id_compra"));
                compra.setFechaCompra(rs.getTimestamp("fecha_solicitud").toLocalDateTime());
                compra.getProveedor().setIdProveedor(rs.getInt("id_proveedor"));
                compra.getUsuario().setIdUsuario(rs.getInt("id_usuario"));
                compra.setTotalCompra(rs.getInt("total_compra"));
                compra.getMetodoPago().setIdMetodoPago(rs.getInt("id_metodo_pago"));
                compra.setEstado(rs.getString("estado"));
                compras.add(compra);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return compras;
    }

    // ANULAR COMPRA
    public static boolean anularCompra (int idCompra) {
        String sql = "UPDATE compras SET estado = ? WHERE idCompra = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){
             ps.setString(1, "PENDIENTE");
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
