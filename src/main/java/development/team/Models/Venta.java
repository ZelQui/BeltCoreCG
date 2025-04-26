package development.team.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Venta {
    private int idVenta;
    private Cliente cliente;
    private LocalDateTime fechaVenta;
    private BigDecimal totalVenta;
    private Usuario usuario;
    private MetodoPago metodoPago;
    private TipoVenta tipoVenta;

    public Venta() {
    }

    public Venta(int idVenta, Cliente cliente, LocalDateTime fechaVenta, BigDecimal totalVenta, Usuario usuario, MetodoPago metodoPago, TipoVenta tipoVenta) {
        this.idVenta = idVenta;
        this.cliente = cliente;
        this.fechaVenta = fechaVenta;
        this.totalVenta = totalVenta;
        this.usuario = usuario;
        this.metodoPago = metodoPago;
        this.tipoVenta = tipoVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public TipoVenta getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(TipoVenta tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    @Override
    public String toString() {
        return "Venta{" +
                "idVenta=" + idVenta +
                ", cliente=" + cliente +
                ", fechaVenta=" + fechaVenta +
                ", totalVenta=" + totalVenta +
                ", usuario=" + usuario +
                ", metodoPago=" + metodoPago +
                ", tipoVenta=" + tipoVenta +
                '}';
    }
}
