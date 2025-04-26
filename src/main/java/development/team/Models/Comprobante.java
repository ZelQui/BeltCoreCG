package development.team.Models;

import java.time.LocalDateTime;

public class Comprobante {
    private int idComprobante;
    private Venta venta;
    private String tipoComprobante;
    private String serie;
    private String numero;
    private LocalDateTime fechaEmision;
    private double total;

    public Comprobante() {
    }

    public Comprobante(int idComprobante, Venta venta, String tipoComprobante, String serie, String numero, LocalDateTime fechaEmision, double total) {
        this.idComprobante = idComprobante;
        this.venta = venta;
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

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
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
        return "Comprobante{" +
                "idComprobante=" + idComprobante +
                ", venta=" + venta +
                ", tipoComprobante='" + tipoComprobante + '\'' +
                ", serie='" + serie + '\'' +
                ", numero='" + numero + '\'' +
                ", fechaEmision=" + fechaEmision +
                ", total=" + total +
                '}';
    }
}
