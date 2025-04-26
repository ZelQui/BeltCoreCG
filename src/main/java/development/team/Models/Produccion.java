package development.team.Models;

import java.time.LocalDateTime;

public class Produccion {
    private int idProduccion;
    private Producto producto;
    private LocalDateTime fechaProduccion;
    private int cantidadProducida;
    private Usuario usuario;

    public Produccion() {
    }

    public Produccion(int idProduccion, Producto producto, LocalDateTime fechaProduccion, int cantidadProducida, Usuario usuario) {
        this.idProduccion = idProduccion;
        this.producto = producto;
        this.fechaProduccion = fechaProduccion;
        this.cantidadProducida = cantidadProducida;
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

    public int getCantidadProducida() {
        return cantidadProducida;
    }

    public void setCantidadProducida(int cantidadProducida) {
        this.cantidadProducida = cantidadProducida;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Produccion{" +
                "idProduccion=" + idProduccion +
                ", producto=" + producto +
                ", fechaProduccion=" + fechaProduccion +
                ", cantidadProducida=" + cantidadProducida +
                ", usuario=" + usuario +
                '}';
    }
}

