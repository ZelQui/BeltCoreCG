package development.team.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CuentaPorCobrar {
    private int idCuenta;
    private Venta venta;
    private Cliente cliente;
    private double montoTotal;
    private double montoPagado;
    private double saldoPendiente;
    private LocalDateTime fechaVencimiento;
    private EstadoCuenta estadoCuenta;
    private LocalDate fechaCreacion;
    private int cuotas;

    public CuentaPorCobrar() {
    }

    public CuentaPorCobrar(int idCuenta, Venta venta, Cliente cliente, double montoTotal, double montoPagado, double saldoPendiente, LocalDateTime fechaVencimiento, EstadoCuenta estadoCuenta, LocalDate fechaCreacion, int cuotas) {
        this.idCuenta = idCuenta;
        this.venta = venta;
        this.cliente = cliente;
        this.montoTotal = montoTotal;
        this.montoPagado = montoPagado;
        this.saldoPendiente = saldoPendiente;
        this.fechaVencimiento = fechaVencimiento;
        this.estadoCuenta = estadoCuenta;
        this.fechaCreacion = fechaCreacion;
        this.cuotas = cuotas;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public EstadoCuenta getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(EstadoCuenta estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
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
        return "CuentaPorCobrar{" +
                "idCuenta=" + idCuenta +
                ", venta=" + venta +
                ", cliente=" + cliente +
                ", montoTotal=" + montoTotal +
                ", montoPagado=" + montoPagado +
                ", saldoPendiente=" + saldoPendiente +
                ", fechaVencimiento=" + fechaVencimiento +
                ", estadoCuenta=" + estadoCuenta +
                ", fechaCreacion=" + fechaCreacion +
                ", cuotas=" + cuotas +
                '}';
    }
}
