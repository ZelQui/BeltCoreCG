package development.team.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pago {
    private int idPago;
    private int idCuenta;
    private LocalDateTime fechaPago;
    private BigDecimal montoPago;
    private int idMetodoPago;
    private String observaciones;
    private int idUsuario;

    public Pago() {
    }

    public Pago(int idPago, int idCuenta, LocalDateTime fechaPago, BigDecimal montoPago, int idMetodoPago, String observaciones, int idUsuario) {
        this.idPago = idPago;
        this.idCuenta = idCuenta;
        this.fechaPago = fechaPago;
        this.montoPago = montoPago;
        this.idMetodoPago = idMetodoPago;
        this.observaciones = observaciones;
        this.idUsuario = idUsuario;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public BigDecimal getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(BigDecimal montoPago) {
        this.montoPago = montoPago;
    }

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "idPago=" + idPago +
                ", idCuenta=" + idCuenta +
                ", fechaPago=" + fechaPago +
                ", montoPago=" + montoPago +
                ", idMetodoPago=" + idMetodoPago +
                ", observaciones='" + observaciones + '\'' +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
