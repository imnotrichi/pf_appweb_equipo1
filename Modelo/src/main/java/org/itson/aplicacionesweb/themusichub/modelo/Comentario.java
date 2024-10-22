package org.itson.aplicacionesweb.themusichub.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Equipo1
 */
@Entity
@Table(name = "comentarios")
public class Comentario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long id;
    
    @Column(name = "fechaHora", nullable = false)
    @Temporal(TemporalType.DATE)
    private Calendar fechaHora;
    
    @Column(name = "contenido", nullable = false, length = 200)
    private String contenido;
    
    @OneToMany(mappedBy = "comentario", cascade = CascadeType.PERSIST)
    private List<Comentario> comentarios;
    
    @ManyToOne
    @JoinColumn(name = "id_comentario_respuesta")
    private Comentario idComentarioRespuesta;
    
    @ManyToOne
    @JoinColumn(name = "id_comun", nullable = false)
    private Comun comun;
    
    @ManyToOne
    @JoinColumn(name = "id_normal", nullable = false)
    private Normal normal;

    public Comentario(){
        
    }
    public Comentario(Calendar fechaHora, String contenido, Comun comun, Normal normal) {
        this.fechaHora = fechaHora;
        this.contenido = contenido;
        this.comun = comun;
        this.normal = normal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Calendar fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Comentario getIdComentarioRespuesta() {
        return idComentarioRespuesta;
    }

    public void setIdComentarioRespuesta(Comentario idComentarioRespuesta) {
        this.idComentarioRespuesta = idComentarioRespuesta;
    }

    public Comun getComun() {
        return comun;
    }

    public void setComun(Comun comun) {
        this.comun = comun;
    }

    public Normal getNormal() {
        return normal;
    }

    public void setNormal(Normal normal) {
        this.normal = normal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.fechaHora);
        hash = 47 * hash + Objects.hashCode(this.contenido);
        hash = 47 * hash + Objects.hashCode(this.comentarios);
        hash = 47 * hash + Objects.hashCode(this.idComentarioRespuesta);
        hash = 47 * hash + Objects.hashCode(this.comun);
        hash = 47 * hash + Objects.hashCode(this.normal);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Comentario other = (Comentario) obj;
        if (!Objects.equals(this.contenido, other.contenido)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.fechaHora, other.fechaHora)) {
            return false;
        }
        if (!Objects.equals(this.comentarios, other.comentarios)) {
            return false;
        }
        if (!Objects.equals(this.idComentarioRespuesta, other.idComentarioRespuesta)) {
            return false;
        }
        if (!Objects.equals(this.comun, other.comun)) {
            return false;
        }
        return Objects.equals(this.normal, other.normal);
    }

    @Override
    public String toString() {
        return "Comentario{" + "id=" + id + ", fechaHora=" + fechaHora + ", contenido=" + contenido + ", comentarios=" + comentarios + ", idComentarioRespuesta=" + idComentarioRespuesta + ", comun=" + comun + ", normal=" + normal + '}';
    }
    
}
