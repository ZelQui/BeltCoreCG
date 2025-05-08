package development.team.DAO;

import development.team.Models.TipoDocumento;
import development.team.Models.TipoCliente;
import development.team.Utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoDocumentoDAO {

    private static final DataSource dataSource = DataBaseUtil.getDataSource();


    // LISTAR
    public static List<TipoDocumento> obtenerTipoDocumento() {
        String sql = "SELECT * FROM tiposdocumento";
        List<TipoDocumento> tipoDocumentosList = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idTipoDocumeto = rs.getInt("id_tipo_documento");
                String nombreTipoDocumento = rs.getString("nombre");
                String siglas = rs.getString("siglas");
                int idTipoCliente = rs.getInt("id_tipo_cliente");

                TipoCliente tipoCliente = new TipoCliente();
                tipoCliente.setIdTipoCliente(idTipoCliente);

                tipoDocumentosList.add(new TipoDocumento(idTipoDocumeto, nombreTipoDocumento, siglas, tipoCliente));
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al obtener tipoDocumentos: " + e.getMessage());
        }

        return tipoDocumentosList;
    }
}
