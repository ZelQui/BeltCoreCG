package development.team.Models;

import java.time.LocalDateTime;

public class Compra {
    private int idCompra;
    private LocalDateTime fechaCompra;
    private Proveedor proveedor;
    private Usuario usuario;
    private double totalCompra;
    private MetodoPago metodoPago;

    public Compra() {
    }

    public Compra(int idCompra, LocalDateTime fechaCompra, Proveedor proveedor, Usuario usuario, double totalCompra, MetodoPago metodoPago) {
        this.idCompra = idCompra;
        this.fechaCompra = fechaCompra;
        this.proveedor = proveedor;
        this.usuario = usuario;
        this.totalCompra = totalCompra;
        this.metodoPago = metodoPago;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "idCompra=" + idCompra +
                ", fechaCompra=" + fechaCompra +
                ", proveedor=" + proveedor +
                ", usuario=" + usuario +
                ", totalCompra=" + totalCompra +
                ", metodoPago=" + metodoPago +
                '}';
    }
}
