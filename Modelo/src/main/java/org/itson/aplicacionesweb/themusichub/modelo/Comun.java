/*
 * Comun.java
 */
package org.itson.aplicacionesweb.themusichub.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Equipo1
 */
@Entity
@Table(name = "comunes")
public class Comun extends Post implements Serializable {

    @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
    private List<Comentario> comentarios;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    public Comun() {
    }

    public Comun(Calendar fechaHoraCreacion, String titulo, String contenido, CategoriaPost categoria) {
        super(fechaHoraCreacion, titulo, contenido, categoria);
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
