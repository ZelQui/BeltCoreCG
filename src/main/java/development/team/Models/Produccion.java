package development.team.Models;

import java.time.LocalDateTime;

public class Produccion {
    private int idProduccion;
    private int idProducto;
    private LocalDateTime fechaProduccion;
    private int cantidadProducida;
    private int idUsuario;

    public Produccion() {
    }

    public Produccion(int idProduccion, int idProducto, LocalDateTime fechaProduccion, int cantidadProducida, int idUsuario) {
        this.idProduccion = idProduccion;
        this.idProducto = idProducto;
        this.fechaProduccion = fechaProduccion;
        this.cantidadProducida = cantidadProducida;
        this.idUsuario = idUsuario;
    }

    public int getIdProduccion() {
        return idProduccion;
    }

    public void setIdProduccion(int idProduccion) {
        this.idProduccion = idProduccion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "Produccion{" +
                "idProduccion=" + idProduccion +
                ", idProducto=" + idProducto +
                ", fechaProduccion=" + fechaProduccion +
                ", cantidadProducida=" + cantidadProducida +
                ", idUsuario=" + idUsuario +
                '}';
    }
}

