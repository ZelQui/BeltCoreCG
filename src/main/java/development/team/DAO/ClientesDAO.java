package development.team.DAO;

import development.team.Models.Cliente;
import development.team.Utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.*;

public class ClientesDAO {

    private static final DataSource dataSource = DataBaseUtil.getDataSource();

    // CREATE
    public static int registrarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (nombres, apellidos, numero_documento, id_tipo_documento, direccion, telefono, correo, id_tipo_cliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int clienteID = -1;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { // Importante para obtener ID generado

            ps.setString(1, cliente.getNombres());
            ps.setString(2, cliente.getApellidos());
            ps.setString(3, cliente.getNumeroDocumento());
            ps.setInt(4, cliente.getTipoDocumento().getIdTipoDocumento());
            ps.setString(5, cliente.getDireccion());
            ps.setString(6, cliente.getTelefono());
            ps.setString(7, cliente.getCorreo());
            ps.setInt(8, cliente.getTipoCliente().getIdTipoCliente());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        clienteID = rs.getInt(1); // Obtener el ID generado
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al registrar cliente: " + e.getMessage());
        }
        return clienteID; //Retorna el ID que ha insertado
    }

}
