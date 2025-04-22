package development.team.Models;

public class DetalleCompra {
    private int idDetalleCompra;
    private int idCompra;
    private int idInsumo;
    private int cantidad;
    private double precioUnitario;
    private double total;

    public DetalleCompra() {}

    public DetalleCompra(int idDetalleCompra, int idCompra, int idInsumo, int cantidad, double precioUnitario, double total) {
        this.idDetalleCompra = idDetalleCompra;
        this.idCompra = idCompra;
        this.idInsumo = idInsumo;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.total = total;
    }

    public int getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(int idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
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
                ", idCompra=" + idCompra +
                ", idConsumo=" + idInsumo +
                ", cantidad=" + cantidad +
                ", precioUnitario=" + precioUnitario +
                ", total=" + total +
                '}';
    }
}
