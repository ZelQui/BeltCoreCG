package development.team.Models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Comprobantes {
    private int idComprobante;
    private int idVenta;
    private String tipoComprobante;
    private String serie;
    private String numero;
    private LocalDateTime fechaEmision;
    private double total;

    public Comprobantes() {
    }

    public Comprobantes(int idComprobante, int idVenta, String tipoComprobante, String serie, String numero, LocalDateTime fechaEmision, double total) {
        this.idComprobante = idComprobante;
        this.idVenta = idVenta;
        this.tipoComprobante = tipoComprobante;
        this.serie = serie;
        this.numero = numero;
        this.fechaEmision = fechaEmision;
        this.total = total;
    }

    public int getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(int idComprobante) {
        this.idComprobante = idComprobante;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Comprobantes{" +
                "idComprobante=" + idComprobante +
                ", idVenta=" + idVenta +
                ", tipoComprobante='" + tipoComprobante + '\'' +
                ", serie='" + serie + '\'' +
                ", numero='" + numero + '\'' +
                ", fechaEmision=" + fechaEmision +
                ", total=" + total +
                '}';
    }
}
