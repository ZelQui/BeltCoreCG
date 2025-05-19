package development.team.DAO;

import development.team.Models.Rol;
import development.team.Utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolDAO {
    private static final DataSource dataSource = DataBaseUtil.getDataSource();

    //CRUD
    //CREATE
    public static boolean insertarRol(Rol rol) {
        String sql = "INSERT INTO roles (nombre_rol, descripcion) VALUES (?, ?)";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, rol.getNombreRol());
            ps.setString(2, rol.getDescripcion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar rol: " + e.getMessage());
            return false;
        }
    }

    //UPDATE
    public static boolean actualizarRol(Rol rol) {
        String sql = "UPDATE roles SET nombre_rol = ?, descripcion = ? WHERE id_rol = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, rol.getNombreRol());
            ps.setString(2, rol.getDescripcion());
            ps.setInt(3, rol.getIdRol());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar rol: " + e.getMessage());
            return false;
        }
    }

    //READ
    public static Rol obtenerRolPorId(int idRol) {
        Rol rol = null;
        String sql = "SELECT id_rol, nombre_rol, descripcion FROM roles WHERE id_rol = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, idRol);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    rol = new Rol(
                            rs.getInt("id_rol"),
                            rs.getString("nombre_rol"),
                            rs.getString("descripcion")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener rol por ID: " + e.getMessage());
        }

        return rol;
    }

    public static List<Integer> obtenerModulosAsignados(int idRol) {
        List<Integer> modulosAsignados = new ArrayList<>();
        String sql = "SELECT m.id_modulo FROM modulos AS m " +
                        "JOIN rol_modulo AS rm ON m.id_modulo = rm.id_modulo " +
                        "WHERE rm.id_rol = ?";
        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idRol);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    modulosAsignados.add(rs.getInt("id_modulo"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return modulosAsignados;
    }

    /**
     * Obtiene un rol usando una conexión ya proporcionada.
     * No crea una nueva conexión.
     */
    public Rol obtenerRolPorId(int idRol, Connection con) throws SQLException {
        Rol rol = null;
        String sql = "SELECT id_rol, nombre_rol, descripcion FROM roles WHERE id_rol = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idRol);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    rol = new Rol(
                            rs.getInt("id_rol"),
                            rs.getString("nombre_rol"),
                            rs.getString("descripcion")
                    );
                }
            }
        }
        return rol;
    }

    public Rol obtenerIdRolPorNombre(String nombreRol) {
        Rol rol = null;
        String sql = "SELECT id_rol, nombre_rol, descripcion FROM roles WHERE nombre_rol = ?";

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombreRol);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    rol = new Rol(
                            rs.getInt("id_rol"),
                            rs.getString("nombre_rol"),
                            rs.getString("descripcion")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener rol por nombre: " + e.getMessage());
        }

        return rol;
    }

    // LISTAR ROLES
    public static List<Rol> obtenerRols() {
        String sql = "SELECT * FROM roles";
        List<Rol> rolesList = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idRol = rs.getInt("id_rol");
                String nombreRol = rs.getString("nombre_rol");
                String descripcion = rs.getString("descripcion");

                rolesList.add(new Rol(idRol, nombreRol, descripcion));
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al obtener roles: " + e.getMessage());
        }

        return rolesList;
    }

    public void actualizarAsignacionModulos(int idRol, List<Integer> modulosSeleccionados) {
        String eliminarSQL = "DELETE FROM rol_modulo WHERE id_rol = ?";
        String insertarSQL = "INSERT INTO rol_modulo (id_rol, id_modulo) VALUES (?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement eliminarStmt = conn.prepareStatement(eliminarSQL);
             PreparedStatement insertarStmt = conn.prepareStatement(insertarSQL)) {

            conn.setAutoCommit(false); // Transacción

            // 1. Eliminar asignaciones anteriores
            eliminarStmt.setInt(1, idRol);
            eliminarStmt.executeUpdate();

            // 2. Insertar nuevas asignaciones
            for (Integer idModulo : modulosSeleccionados) {
                if (idModulo == 23) continue; // Seguridad: excluye módulo 23 si llega
                insertarStmt.setInt(1, idRol);
                insertarStmt.setInt(2, idModulo);
                insertarStmt.addBatch();
            }

            insertarStmt.executeBatch(); // Ejecutar en lote
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            // Manejar rollback si ocurre error
        }
    }
}
