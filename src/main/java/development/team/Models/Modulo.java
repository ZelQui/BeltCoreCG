package development.team.Models;

public class Modulo {
    private String nombre;
    private String ruta;
    private String icono;
    private Integer moduloPadreId; // puede ser null

    public Modulo() {
    }

    public Modulo(String nombre, String ruta, String icono, Integer moduloPadreId) {
        this.nombre = nombre;
        this.ruta = ruta;
        this.icono = icono;
        this.moduloPadreId = moduloPadreId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public Integer getModuloPadreId() {
        return moduloPadreId;
    }

    public void setModuloPadreId(Integer moduloPadreId) {
        this.moduloPadreId = moduloPadreId;
    }

    @Override
    public String toString() {
        return "Modulo{" +
                "nombre='" + nombre + '\'' +
                ", ruta='" + ruta + '\'' +
                ", icono='" + icono + '\'' +
                ", moduloPadreId=" + moduloPadreId +
                '}';
    }
}

