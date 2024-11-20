/*
 * UsuarioBean.java
 */
package beans;

/**
 * @author Equipo1
 */
public class UsuarioBean {

    String nombreUsuario;
    String correo;
    String ciudad;
    String avatar;

    public UsuarioBean(String nombreUsuario, String correo, String ciudad, String avatar) {
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.ciudad = ciudad;
        this.avatar = avatar;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getAvatar() {
        return avatar;
    }

}
