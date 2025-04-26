package development.team.Models;

import java.time.LocalDate;

public class Merma {
    private int idMerma;
    private Produccion produccion;
    private Insumo insumo;
    private int cantidadMermada;
    private String descripcion;
    private LocalDate fechaRegistro;

    public Merma() {
    }

    public Merma(int idMerma, Produccion produccion, Insumo insumo, int cantidadMermada, String descripcion, LocalDate fechaRegistro) {
        this.idMerma = idMerma;
        this.produccion = produccion;
        this.insumo = insumo;
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

    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
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
                ", produccion=" + produccion +
                ", insumo=" + insumo +
                ", cantidadMermada=" + cantidadMermada +
                ", descripcion='" + descripcion + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}
