package development.team.Models;

public class DetalleCompra {
    private int idDetalleCompra;
    private Compra compra;
    private Insumo insumo;
    private int cantidad;
    private double precioUnitario;
    private double total;

    public DetalleCompra() {}

    public DetalleCompra(int idDetalleCompra, Compra compra, Insumo insumo, int cantidad, double precioUnitario, double total) {
        this.idDetalleCompra = idDetalleCompra;
        this.compra = compra;
        this.insumo = insumo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
    }

    public DetalleCompra(Compra compra, Insumo insumo, int cantidad) {
        this.compra = compra;
        this.insumo = insumo;
        this.cantidad = cantidad;
    }

    public int getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(int idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
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
        return "DetalleCompra{" +
                "idDetalleCompra=" + idDetalleCompra +
                ", compra=" + compra +
                ", insumo=" + insumo +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", total=" + total +
                '}';
    }
}
