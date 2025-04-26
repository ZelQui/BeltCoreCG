package development.team.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pago {
    private int idPago;
    private CuentaPorCobrar cuenta;
    private LocalDateTime fechaPago;
    private BigDecimal montoPago;
    private MetodoPago metodoPago;
    private String observaciones;
    private Usuario usuario;

    public Pago() {
    }

    public Pago(int idPago, CuentaPorCobrar cuenta, LocalDateTime fechaPago, BigDecimal montoPago, MetodoPago metodoPago, String observaciones, Usuario usuario) {
        this.idPago = idPago;
        this.cuenta = cuenta;
        this.fechaPago = fechaPago;
        this.montoPago = montoPago;
        this.metodoPago = metodoPago;
        this.observaciones = observaciones;
        this.usuario = usuario;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public CuentaPorCobrar getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaPorCobrar cuenta) {
        this.cuenta = cuenta;
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

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Pago{" +
                "idPago=" + idPago +
                ", cuenta=" + cuenta +
                ", fechaPago=" + fechaPago +
                ", montoPago=" + montoPago +
                ", metodoPago=" + metodoPago +
                ", observaciones='" + observaciones + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
