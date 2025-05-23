package development.team.Models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Produccion {
    private int idProduccion;
    private Producto producto;
    private LocalDateTime fechaProduccion;
    private LocalDateTime fechaCierre;
    private int cantidadPlaneada;
    private int cantidadProducida;
    private int cantidadDefectuosa;
    private BigDecimal rendimiento;
    private int estado;
    private Usuario usuario;

    public Produccion() {
    }

    public Produccion(int idProduccion, Producto producto, LocalDateTime fechaProduccion, int cantidadPlaneada, int estado, Usuario usuario) {
        this.idProduccion = idProduccion;
        this.producto = producto;
        this.fechaProduccion = fechaProduccion;
        this.cantidadPlaneada = cantidadPlaneada;
        this.estado = estado;
        this.usuario = usuario;
    }

    public Produccion(int idProduccion, Producto producto, LocalDateTime fechaProduccion, LocalDateTime fechaCierre, int cantidadPlaneada, int cantidadProducida, int cantidadDefectuosa, BigDecimal rendimiento, int estado, Usuario usuario) {
        this.idProduccion = idProduccion;
        this.producto = producto;
        this.fechaProduccion = fechaProduccion;
        this.fechaCierre = fechaCierre;
        this.cantidadPlaneada = cantidadPlaneada;
        this.cantidadProducida = cantidadProducida;
        this.cantidadDefectuosa = cantidadDefectuosa;
        this.rendimiento = rendimiento;
        this.estado = estado;
        this.usuario = usuario;
    }

    public int getIdProduccion() {
        return idProduccion;
    }

    public void setIdProduccion(int idProduccion) {
        this.idProduccion = idProduccion;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public LocalDateTime getFechaProduccion() {
        return fechaProduccion;
    }

    public void setFechaProduccion(LocalDateTime fechaProduccion) {
        this.fechaProduccion = fechaProduccion;
    }

    public LocalDateTime getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDateTime fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public int getCantidadPlaneada() {
        return cantidadPlaneada;
    }

    public void setCantidadPlaneada(int cantidadPlaneada) {
        this.cantidadPlaneada = cantidadPlaneada;
    }

    public int getCantidadProducida() {
        return cantidadProducida;
    }

    public void setCantidadProducida(int cantidadProducida) {
        this.cantidadProducida = cantidadProducida;
    }

    public int getCantidadDefectuosa() {
        return cantidadDefectuosa;
    }

    public void setCantidadDefectuosa(int cantidadDefectuosa) {
        this.cantidadDefectuosa = cantidadDefectuosa;
    }

    public BigDecimal getRendimiento() {
        return rendimiento;
    }

    public void setRendimiento(BigDecimal rendimiento) {
        this.rendimiento = rendimiento;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

