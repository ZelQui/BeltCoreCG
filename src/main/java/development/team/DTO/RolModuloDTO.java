package development.team.DTO;

public class RolModuloDTO {
    private int idRol;
    private String nombreRol;
    private String descripcionRol;
    private int idModulo;
    private String nombreModulo;
    private String rutaModulo;
    private String iconoModulo;
    private Integer moduloPadreId;

    public RolModuloDTO() {
    }

    public RolModuloDTO(int idRol, String nombreRol, String descripcionRol, int idModulo, String nombreModulo, String rutaModulo, String iconoModulo, Integer moduloPadreId) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.descripcionRol = descripcionRol;
        this.idModulo = idModulo;
        this.nombreModulo = nombreModulo;
        this.rutaModulo = rutaModulo;
        this.iconoModulo = iconoModulo;
        this.moduloPadreId = moduloPadreId;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcionRol() {
        return descripcionRol;
    }

    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public String getRutaModulo() {
        return rutaModulo;
    }

    public void setRutaModulo(String rutaModulo) {
        this.rutaModulo = rutaModulo;
    }

    public String getIconoModulo() {
        return iconoModulo;
    }

    public void setIconoModulo(String iconoModulo) {
        this.iconoModulo = iconoModulo;
    }

    public Integer getModuloPadreId() {
        return moduloPadreId;
    }

    public void setModuloPadreId(Integer moduloPadreId) {
        this.moduloPadreId = moduloPadreId;
    }

    @Override
    public String toString() {
        return "RolModuloDTO{" +
                "idRol=" + idRol +
                ", nombreRol='" + nombreRol + '\'' +
                ", descripcionRol='" + descripcionRol + '\'' +
                ", idModulo=" + idModulo +
                ", nombreModulo='" + nombreModulo + '\'' +
                ", rutaModulo='" + rutaModulo + '\'' +
                ", iconoModulo='" + iconoModulo + '\'' +
                ", moduloPadreId=" + moduloPadreId +
                '}';
    }
}
