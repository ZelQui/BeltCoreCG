package development.team.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Venta {
    private int idVenta;
    private int idCliente;
    private LocalDateTime fechaVenta;
    private BigDecimal totalVenta;
    private int idUsuario;
    private int idMetodoPago;
    private int idTipoVenta;

    public Venta() {
    }

    public Venta(int idVenta, int idCliente, LocalDateTime fechaVenta, BigDecimal totalVenta, int idUsuario, int idMetodoPago, int idTipoVenta) {
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.fechaVenta = fechaVenta;
        this.totalVenta = totalVenta;
        this.idUsuario = idUsuario;
        this.idMetodoPago = idMetodoPago;
        this.idTipoVenta = idTipoVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDateTime getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(LocalDateTime fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public BigDecimal getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(BigDecimal totalVenta) {
        this.totalVenta = totalVenta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public int getIdTipoVenta() {
        return idTipoVenta;
    }

    public void setIdTipoVenta(int idTipoVenta) {
        this.idTipoVenta = idTipoVenta;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", idCliente=" + idCliente +
                ", fechaVenta=" + fechaVenta +
                ", totalVenta=" + totalVenta +
                ", idUsuario=" + idUsuario +
                ", idMetodoPago=" + idMetodoPago +
                ", idTipoVenta=" + idTipoVenta +
                '}';
    }
}
