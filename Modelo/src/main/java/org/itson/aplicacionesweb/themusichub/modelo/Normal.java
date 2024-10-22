/*
 * Normal.java
 */
package org.itson.aplicacionesweb.themusichub.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Equipo1
 */
@Entity
@Table(name = "normales")
public class Normal extends Usuario implements Serializable {

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.PERSIST)
    private List<Comentario> comentarios;

    /**
     * Constructor vacío.
     */
    public Normal() {
    }

    public Normal(String nombres, String apellidoPaterno, String apellidoMaterno, String correo, byte[] contrasenia, byte[] telefono, String avatar, String ciudad, Calendar fechaNacimiento, String genero) {
        super(nombres, apellidoPaterno, apellidoMaterno, correo, contrasenia, telefono, avatar, ciudad, fechaNacimiento, genero);
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Normal{");
        sb.append("nombres=").append(nombres);
        sb.append(", apellidoPaterno=").append(apellidoPaterno);
        sb.append(", apellidoMaterno=").append(apellidoMaterno);
        sb.append(", correo=").append(correo);
        sb.append(", contrasenia=").append(contrasenia);
        sb.append(", telefono=").append(telefono);
        sb.append(", avatar=").append(avatar);
        sb.append(", ciudad=").append(ciudad);
        sb.append(", fechaNacimiento=").append(fechaNacimiento);
        sb.append(", genero=").append(genero);
        sb.append('}');
        return sb.toString();
    }

}
