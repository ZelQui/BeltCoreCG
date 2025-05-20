package development.team.Models;

import java.time.LocalDateTime;

public class Compra {
    private int idCompra;
    private LocalDateTime fechaSolicitud;
    private LocalDateTime fechaCompra;
    private Proveedor proveedor;
    private Usuario usuario;
    private double totalCompra;
    private MetodoPago metodoPago;
    private String estado;

    public Compra() {
    }

    public Compra(int idCompra, LocalDateTime fechaSolicitud, LocalDateTime fechaCompra, Proveedor proveedor, Usuario usuario, double totalCompra, MetodoPago metodoPago, String estado) {
        this.idCompra = idCompra;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaCompra = fechaCompra;
        this.proveedor = proveedor;
        this.usuario = usuario;
        this.totalCompra = totalCompra;
        this.metodoPago = metodoPago;
        this.estado = estado;
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

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "idCompra=" + idCompra +
                ", fechaSolicitud=" + fechaSolicitud +
                ", fechaCompra=" + fechaCompra +
                ", proveedor=" + proveedor +
                ", usuario=" + usuario +
                ", totalCompra=" + totalCompra +
                ", metodoPago=" + metodoPago +
                ", estado='" + estado + '\'' +
                '}';
    }
}
