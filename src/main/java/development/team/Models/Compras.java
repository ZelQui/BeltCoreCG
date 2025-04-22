package development.team.Models;

import java.time.LocalDateTime;

public class Compras {
    private int idCompra;
    private LocalDateTime fechaCompra;
    private int idProveedor;
    private int idUsuario;
    private double totalCompra;
    private int idMetodoPago;
    private int idEstado;

    public Compras() {
    }

    public Compras(int idCompra, LocalDateTime fechaCompra, int idProveedor, int idUsuario, double totalCompra, int idMetodoPago, int idEstado) {
        this.idCompra = idCompra;
        this.fechaCompra = fechaCompra;
        this.idProveedor = idProveedor;
        this.idUsuario = idUsuario;
        this.totalCompra = totalCompra;
        this.idMetodoPago = idMetodoPago;
        this.idEstado = idEstado;
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

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public String toString() {
        return "Compras{" +
                "idCompra=" + idCompra +
                ", fechaCompra=" + fechaCompra +
                ", idProveedor=" + idProveedor +
                ", idUsuario=" + idUsuario +
                ", totalCompra=" + totalCompra +
                ", idMetodoPago=" + idMetodoPago +
                ", idEstado=" + idEstado +
                '}';
    }
}
