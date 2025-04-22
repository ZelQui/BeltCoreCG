package development.team.Models;

public class ConceptosAdicionales {
    private int idConceptoAdicional;
    private String nombre;
    private String descripcion;

    public ConceptosAdicionales() {
    }

    public ConceptosAdicionales(int idConceptoAdicional, String nombre, String descripcion) {
        this.idConceptoAdicional = idConceptoAdicional;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public int getIdConceptoAdicional() {
        return idConceptoAdicional;
    }

    public void setIdConceptoAdicional(int idConceptoAdicional) {
        this.idConceptoAdicional = idConceptoAdicional;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "ConceptosAdicionales{" +
                "idConceptoAdicional=" + idConceptoAdicional +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
