package development.team.DAO;

import development.team.DTO.UsuarioRolDTO;
import development.team.Models.Proveedor;
import development.team.Utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
    private static final DataSource dataSource = DataBaseUtil.getDataSource();

    // CREATE
    public static int registrarProveedor(Proveedor proveedor) {
        String sql = "INSERT INTO proveedores (nombre, telefono, correo, direccion) VALUES (?, ?, ?, ?)";
        int proveedorId = -1;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { // Importante para obtener ID generado

            ps.setString(1, proveedor.getNombre());
            ps.setString(2, proveedor.getTelefono());
            ps.setString(3, proveedor.getCorreo());
            ps.setString(4, proveedor.getDireccion());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        proveedorId = rs.getInt(1); // Obtener el ID generado
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al registrar usuario: " + e.getMessage());
        }
        return proveedorId; //Retorna el ID que ha insertado
    }

    // UPDATE
    private static boolean actualizarProveedor(Proveedor nuevoProveedor) {
        String sql = "UPDATE proveedores SET nombre = ?, telefono = ?, correo = ?, direccion = ?";
        boolean result = false;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevoProveedor.getNombre());
            ps.setString(2, nuevoProveedor.getTelefono());
            ps.setString(3, nuevoProveedor.getCorreo());
            ps.setString(4, nuevoProveedor.getDireccion());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Proveedor " + nuevoProveedor.getIdProveedor() + " actualizado correctamente.");
                result = true;
            } else {
                System.err.println("Error al actualizar proveedor con ID: " + nuevoProveedor.getIdProveedor());
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al actualizar proveedor " + nuevoProveedor.getIdProveedor() + ": " + e.getMessage());
        }

        return result;
    }

    // Activar Proveedor
    public static void activarProveedor(int proveedorId) {
        String sql = "UPDATE proveedores SET estado = 1 WHERE id_proveedor = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, proveedorId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Proveedor " + proveedorId + " actualizado correctamente.");
            } else {
                System.err.println("Error al activar proveedor con ID: " + proveedorId);
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al activar proveedor " + proveedorId + ": " + e.getMessage());
        }
    }

    // Bloquear Proveedor
    public static void bloquearProveedor(int proveedorId) {
        String sql = "UPDATE proveedores SET estado = 0 WHERE id_proveedor = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, proveedorId);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Proveedor " + proveedorId + " actualizado correctamente.");
            } else {
                System.err.println("Error al bloquear proveedor con ID: " + proveedorId);
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al bloquear proveedor " + proveedorId + ": " + e.getMessage());
        }
    }

    // Listar Proveedores
    public static List<Proveedor> obtenerProveedores() {
        String sql = "SELECT * FROM proveedores";
        List<Proveedor> proveedoresList = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idProveedor = rs.getInt("id_proveedor");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                String correo = rs.getString("correo");
                String direccion = rs.getString("direccion");
                int estado = rs.getInt("estado");

                proveedoresList.add(new Proveedor(idProveedor, nombre, telefono, correo, direccion, estado));
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al obtener proveedores: " + e.getMessage());
        }

        return proveedoresList;
    }

}
