package development.team.Models;

import java.math.BigDecimal;

public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private BigDecimal precioVenta;
    private int cantidadStock;
    private int stockMinimo;
    private Categoria categoria;
    private EstadoProducto estadoProducto;

    public Producto() {
    }

    public Producto(int idProducto, String nombre, String descripcion, BigDecimal precioVenta, int cantidadStock, int stockMinimo, Categoria categoria, EstadoProducto estadoProducto) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.cantidadStock = cantidadStock;
        this.stockMinimo = stockMinimo;
        this.categoria = categoria;
        this.estadoProducto = estadoProducto;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(BigDecimal precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public EstadoProducto getEstadoProducto() {
        return estadoProducto;
    }

    public void setEstadoProducto(EstadoProducto estadoProducto) {
        this.estadoProducto = estadoProducto;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioVenta=" + precioVenta +
                ", cantidadStock=" + cantidadStock +
                ", stockMinimo=" + stockMinimo +
                ", categoria=" + categoria +
                ", estadoProducto=" + estadoProducto +
                '}';
    }
}
