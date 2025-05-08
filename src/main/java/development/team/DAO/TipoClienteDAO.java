package development.team.DAO;

import development.team.Models.TipoCliente;
import development.team.Utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoClienteDAO {

    private static final DataSource dataSource = DataBaseUtil.getDataSource();

    // LISTAR
    public static List<TipoCliente> obtenerTiposClientes() {
        String sql = "SELECT * FROM tiposcliente";
        List<TipoCliente> tipoClienteList = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idTipoCliente = rs.getInt("id_tipo_cliente");
                String nombre = rs.getString("nombre");

                tipoClienteList.add(new TipoCliente(idTipoCliente, nombre));
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al obtener tiposClientes: " + e.getMessage());
        }

        return tipoClienteList;
    }
}
