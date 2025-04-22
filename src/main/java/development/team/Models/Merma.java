package development.team.Models;

import java.time.LocalDate;

public class Merma {
    private int idMerma;
    private int idProduccion;
    private int idInsumo;
    private int cantidadMermada;
    private String descripcion;
    private LocalDate fechaRegistro;

    public Merma() {
    }

    public Merma(int idMerma, int idProduccion, int idInsumo, int cantidadMermada, String descripcion, LocalDate fechaRegistro) {
        this.idMerma = idMerma;
        this.idProduccion = idProduccion;
        this.idInsumo = idInsumo;
        this.cantidadMermada = cantidadMermada;
        this.descripcion = descripcion;
        this.fechaRegistro = fechaRegistro;
    }

    public int getIdMerma() {
        return idMerma;
    }

    public void setIdMerma(int idMerma) {
        this.idMerma = idMerma;
    }

    public int getIdProduccion() {
        return idProduccion;
    }

    public void setIdProduccion(int idProduccion) {
        this.idProduccion = idProduccion;
    }

    public int getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(int idInsumo) {
        this.idInsumo = idInsumo;
    }

    public int getCantidadMermada() {
        return cantidadMermada;
    }

    public void setCantidadMermada(int cantidadMermada) {
        this.cantidadMermada = cantidadMermada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Override
    public String toString() {
        return "Merma{" +
                "idMerma=" + idMerma +
                ", idProduccion=" + idProduccion +
                ", idInsumo=" + idInsumo +
                ", cantidadMermada=" + cantidadMermada +
                ", descripcion='" + descripcion + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
