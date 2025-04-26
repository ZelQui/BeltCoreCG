package development.team.Models;

public class DetalleVenta {
    private int idDetalle;
    private Venta venta;
    private Producto producto;
    private int cantidad;
    private double precioUnitario;
    private double total;

    public DetalleVenta() {}

    public DetalleVenta(int idDetalle, Venta venta, Producto producto, int cantidad, double precioUnitario, double total) {
        this.idDetalle = idDetalle;
        this.venta = venta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(int idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" +
                "idDetalle=" + idDetalle +
                ", venta=" + venta +
                ", producto=" + producto +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", total=" + total +
                '}';
    }
}
