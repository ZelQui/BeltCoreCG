package development.team.DTO;

//CLASE DTO: Usada para listar un consulta multiple o anidada de dos o mas clases DAO

public class UsuarioRolDTO {
    private int idUsuario;
    private String dni;
    private String nombreUsuario;
    private String apellidoPaternoUsr;
    private String apellidoMaternoUsr;
    private String telefono;
    private String correo;
    private String userLogin;
    private String contrasena;
    private int idRol;
    private String nombreRol;
    private String descripcion;
    private int estado;

    public UsuarioRolDTO() {
    }

    public UsuarioRolDTO(int idUsuario, String dni, String nombreUsuario, String apellidoPaternoUsr, String apellidoMaternoUsr, String telefono, String correo, String userLogin, String contrasena, int idRol, String nombreRol, String descripcion, int estado) {
        this.idUsuario = idUsuario;
        this.dni = dni;
        this.nombreUsuario = nombreUsuario;
        this.apellidoPaternoUsr = apellidoPaternoUsr;
        this.apellidoMaternoUsr = apellidoMaternoUsr;
        this.telefono = telefono;
        this.correo = correo;
        this.userLogin = userLogin;
        this.contrasena = contrasena;
        this.idRol = idRol;
        this.nombreRol = nombreRol;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoPaternoUsr() {
        return apellidoPaternoUsr;
    }

    public void setApellidoPaternoUsr(String apellidoPaternoUsr) {
        this.apellidoPaternoUsr = apellidoPaternoUsr;
    }

    public String getApellidoMaternoUsr() {
        return apellidoMaternoUsr;
    }

    public void setApellidoMaternoUsr(String apellidoMaternoUsr) {
        this.apellidoMaternoUsr = apellidoMaternoUsr;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "UsuarioRolDTO{" +
                "idUsuario=" + idUsuario +
                ", dni='" + dni + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", apellidoPaternoUsr='" + apellidoPaternoUsr + '\'' +
                ", apellidoMaternoUsr='" + apellidoMaternoUsr + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", userLogin='" + userLogin + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", idRol=" + idRol +
                ", nombreRol='" + nombreRol + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                '}';
    }
}
