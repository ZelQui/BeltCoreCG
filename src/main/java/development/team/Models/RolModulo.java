package development.team.Models;

public class RolModulo {
    private Rol rol;
    private Modulo modulo;

    public RolModulo() {
    }

    public RolModulo(Rol rol, Modulo modulo) {
        this.rol = rol;
        this.modulo = modulo;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    @Override
    public String toString() {
        return "RolModulo{" +
                "rol=" + rol +
                ", modulo=" + modulo +
                '}';
    }
}
