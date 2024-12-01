/*
 * Comun.java
 */
package org.itson.aplicacionesweb.themusichub.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.itson.aplicacionesweb.themusichub.enums.CategoriaPost;

/**
 * @author Equipo1
 */
@Entity
@DiscriminatorValue("Comun") // Valor discriminador para posts normales
public class Comun extends Post implements Serializable {

    public Comun() {
    }

    /**
     * Constructor para un Post Común nuevo.
     *
     * @param fechaHoraCreacion La fecha y hora de creación del post.
     * @param titulo El titulo del post
     * @param subtitulo El subtitulo del post
     * @param contenido El contenido del post
     * @param categoria La categoría del post
     * @param usuario El usuario que creó el post
     */
    public Comun(Calendar fechaHoraCreacion, String titulo, String subtitulo, String contenido, CategoriaPost categoria, Usuario usuario, String imagen) {
        super(
                fechaHoraCreacion,
                titulo,
                subtitulo,
                contenido,
                categoria,
                usuario, 
                imagen);
    }

    /**
     * Constructor para un Post Común ya existente.
     *
     * @param id
     * @param fechaHoraCreacion La fecha y hora de creación del post.
     * @param titulo El titulo del post
     * @param subtitulo El subtitulo del post
     * @param contenido El contenido del post
     * @param categoria La categoría del post
     * @param usuario El usuario que creó el post
     * @param comentarios Los comentarios del post
     */
    public Comun(Long id, Calendar fechaHoraCreacion, String titulo, String subtitulo, String contenido, CategoriaPost categoria, List<Comentario> comentarios, Usuario usuario, String imagen) {
        super(
                id,
                fechaHoraCreacion,
                titulo,
                subtitulo,
                contenido,
                categoria,
                comentarios,
                usuario, 
                imagen);
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Comun{");
        sb.append("comentarios=").append(comentarios);
        sb.append(", usuario=").append(usuario);
        sb.append('}');
        return sb.toString();
    }

}