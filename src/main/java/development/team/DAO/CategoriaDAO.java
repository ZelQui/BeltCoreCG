package development.team.DAO;

import development.team.Models.Categoria;
import development.team.Utils.DataBaseUtil;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    private static final DataSource dataSource = DataBaseUtil.getDataSource();

    // CREATE
    public static int registrarCategoria(Categoria categoria) {
        String sql = "INSERT INTO categorias (nombre) VALUES (?)";
        int categoriaID = -1;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { // Importante para obtener ID generado

            ps.setString(1, categoria.getNombre());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        categoriaID = rs.getInt(1); // Obtener el ID generado
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al registrar categoria: " + e.getMessage());
        }
        return categoriaID; //Retorna el ID que ha insertado
    }

    // UPDATE
    private static boolean actualizarCategoria(Categoria nuevaCategoria) {
        String sql = "UPDATE categorias SET nombre = ? WHERE id_categoria = ?";
        boolean result = false;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevaCategoria.getNombre());
            ps.setInt(2, nuevaCategoria.getIdCategoria());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Categoria " + nuevaCategoria.getIdCategoria() + " actualizado correctamente.");
                result = true;
            } else {
                System.err.println("Error al actualizar categoria con ID: " + nuevaCategoria.getIdCategoria());
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al actualizar modulos " + nuevaCategoria.getIdCategoria() + ": " + e.getMessage());
        }

        return result;
    }

    // LISTAR
    public static List<Categoria> obtenerCategorias() {
        String sql = "SELECT * FROM categorias";
        List<Categoria> categoriasList = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idCategoria = rs.getInt("id_categoria");
                String nombre = rs.getString("nombre");

                categoriasList.add(new Categoria(idCategoria, nombre));
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al obtener categorias: " + e.getMessage());
        }

        return categoriasList;
    }
}
