package development.team.DTO;

import java.math.BigDecimal;

public class ProductoCategoriaEstadoDTO {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private BigDecimal precioVenta;
    private int cantidadStock;
    private int stockMinimo;
    private String nombreCategoria;
    private String nombreEstado;

    public ProductoCategoriaEstadoDTO(int idProducto, String nombre, String descripcion, BigDecimal precioVenta, int cantidadStock, int stockMinimo, String nombreCategoria, String nombreEstado) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.cantidadStock = cantidadStock;
        this.stockMinimo = stockMinimo;
        this.nombreCategoria = nombreCategoria;
        this.nombreEstado = nombreEstado;
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

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    @Override
    public String toString() {
        return "ProductoCategoriaEstadoDTO{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioVenta=" + precioVenta +
                ", cantidadStock=" + cantidadStock +
                ", stockMinimo=" + stockMinimo +
                ", nombreCategoria='" + nombreCategoria + '\'' +
                ", nombreEstado='" + nombreEstado + '\'' +
                '}';
    }
}
