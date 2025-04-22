package development.team.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CuentasPorCobrar {
    private int idCuenta;
    private int idVenta;
    private int idCliente;
    private double montoTotal;
    private double montoPagado;
    private double saldoPendiente;
    private LocalDateTime fechaVencimiento;
    private int idEstadoCuenta;
    private LocalDate fechaCreacion;
    private int cuotas;

    public CuentasPorCobrar() {
    }

    public CuentasPorCobrar(int idCuenta, int idVenta, int idCliente, double montoTotal, double montoPagado, double saldoPendiente, LocalDateTime fechaVencimiento, int idEstadoCuenta, LocalDate fechaCreacion, int cuotas) {
        this.idCuenta = idCuenta;
        this.idVenta = idVenta;
        this.idCliente = idCliente;
        this.montoTotal = montoTotal;
        this.montoPagado = montoPagado;
        this.saldoPendiente = saldoPendiente;
        this.fechaVencimiento = fechaVencimiento;
        this.idEstadoCuenta = idEstadoCuenta;
        this.fechaCreacion = fechaCreacion;
        this.cuotas = cuotas;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
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

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public double getSaldoPendiente() {
        return saldoPendiente;
    }

    public void setSaldoPendiente(double saldoPendiente) {
        this.saldoPendiente = saldoPendiente;
    }

    public LocalDateTime getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDateTime fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getIdEstadoCuenta() {
        return idEstadoCuenta;
    }

    public void setIdEstadoCuenta(int idEstadoCuenta) {
        this.idEstadoCuenta = idEstadoCuenta;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getCuotas() {
        return cuotas;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    @Override
    public String toString() {
        return "CuentasPorCobrar{" +
                "idCuenta=" + idCuenta +
                ", idVenta=" + idVenta +
                ", idCliente=" + idCliente +
                ", montoTotal=" + montoTotal +
                ", montoPagado=" + montoPagado +
                ", saldoPendiente=" + saldoPendiente +
                ", fechaVencimiento=" + fechaVencimiento +
                ", idEstadoCuenta=" + idEstadoCuenta +
                ", fechaCreacion=" + fechaCreacion +
                ", cuotas=" + cuotas +
                '}';
    }
}
