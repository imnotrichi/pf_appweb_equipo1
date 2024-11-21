/*
 * UsuarioBean.java
 */
package beans;

import java.io.Serializable;

/**
 * @author Equipo1
 */
public class UsuarioBean implements Serializable {

    String nombreUsuario;
    String correo;
    String ciudad;
    String avatar;
    String tipo;

    public UsuarioBean(String nombreUsuario, String correo, String ciudad, String avatar, String tipo) {
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.ciudad = ciudad;
        this.avatar = avatar;
        this.tipo = tipo;
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
    
    public String getTipo() {
        return tipo;
    }

}
