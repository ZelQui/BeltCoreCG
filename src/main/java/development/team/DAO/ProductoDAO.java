package development.team.DAO;

import development.team.DTO.ProductoCategoriaEstadoDTO;
import development.team.Models.Producto;
import development.team.Models.Usuario;
import development.team.Utils.DataBaseUtil;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private static final DataSource dataSource = DataBaseUtil.getDataSource();

    // CREATE
    public static int registrarProducto(Producto producto) {
        String sql = "INSERT INTO productos (nombre, descripcion, precio_venta, cantidad_stock, stock_minimo, id_categoria, id_estado_producto) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int productoId = -1;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) { // Importante para obtener ID generado

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setBigDecimal(3, producto.getPrecioVenta());
            ps.setInt(4, producto.getCantidadStock());
            ps.setInt(5, producto.getStockMinimo());
            ps.setInt(6, producto.getCategoria().getIdCategoria());
            ps.setInt(7, producto.getEstadoProducto().getIdEstadoProducto());


            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        productoId = rs.getInt(1); // Obtener el ID generado
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al registrar producto: " + e.getMessage());
        }
        return productoId; //Retorna el ID que ha insertado
    }

    // READ
    public static List<ProductoCategoriaEstadoDTO> obtenerProductos() {

        String sql = "SELECT p.id_producto, p.nombre, p.descripcion, p.precio_venta, p.cantidad_stock, p.stock_minimo, c.nombre, e.nombre " +
                "FROM productos as p " +
                "JOIN categorias AS c ON p.id_categoria = c.id_categoria" +
                "JOIN estadosproducto AS e ON p.estado_producto = e.id_estado_producto";
        List<ProductoCategoriaEstadoDTO> productosList = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int idproducto = rs.getInt("p.id_producto");
                String nombre = rs.getString("p.nombre");
                String descripcion = rs.getString("p.descripcion");
                BigDecimal precioVenta = rs.getBigDecimal("p.precio_venta");
                int cantidadStock = rs.getInt("p.cantidad_stock");
                int stockMinimo = rs.getInt("p.stock_minimo");
                String nombreCategoria = rs.getString("c.nombre");
                String nombreEstado = rs.getString("e.nombre");

                productosList.add(new ProductoCategoriaEstadoDTO(idproducto, nombre, descripcion, precioVenta, cantidadStock, stockMinimo, nombreCategoria, nombreEstado));
            }

        } catch (SQLException e) {
            System.err.println("Error SQLException al obtener usuarios: " + e.getMessage());
        }

        return productosList;
    }

    // UPDATE
    public static boolean actualizarProducto(Producto nuevoProducto) {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ? , precio_venta = ?, cantidad_stock = ?, stock_minimo = ?, id_categoria = ?, id_estado_producto = ? WHERE id_producto = ?";
        boolean result = false;

        try (Connection con = dataSource.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nuevoProducto.getNombre());
            ps.setString(2, nuevoProducto.getDescripcion());
            ps.setBigDecimal(3, nuevoProducto.getPrecioVenta());
            ps.setInt(4, nuevoProducto.getCantidadStock());
            ps.setInt(5, nuevoProducto.getStockMinimo());
            ps.setInt(6, nuevoProducto.getCategoria().getIdCategoria());
            ps.setInt(7, nuevoProducto.getEstadoProducto().getIdEstadoProducto());
            ps.setInt(8, nuevoProducto.getIdProducto());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Producto " + nuevoProducto.getIdProducto() + " actualizado correctamente.");
                result = true;
            } else {
                System.err.println("Error al actualizar producto con ID: " + nuevoProducto.getIdProducto());
            }
        } catch (SQLException e) {
            System.err.println("Error SQLException al actualizar producto " + nuevoProducto.getIdProducto() + ": " + e.getMessage());
        }

        return result;
    }
}
