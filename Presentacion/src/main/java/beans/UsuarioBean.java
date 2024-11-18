/*
 * UsuarioBean.java
 */
package beans;

/**
 * @author Equipo1
 */
public class UsuarioBean {

    String nombreUsuario;

    public UsuarioBean(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

}
