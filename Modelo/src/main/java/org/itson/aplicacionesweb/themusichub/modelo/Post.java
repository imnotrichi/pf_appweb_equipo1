/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.aplicacionesweb.themusichub.modelo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Equipo 1
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name ="tipoPost")
@Table(name="posts")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_post")
    protected Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_hora_creacion", nullable = false)
    protected Calendar fechaHoraCreacion;
    
    @Column(name="titulo",nullable = false)
    protected String titulo;
    
    @Column(name="contenido", nullable = false)
    protected String contenido;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    protected CategoriaPost categoria;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_hora_edicion", nullable = false)
    protected Calendar fechaHoraEdicion;

    public Post(Long id, Calendar fechaHoraCreacion, String titulo, String contenido, Calendar fechaHoraEdicion) {
        this.id = id;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaHoraEdicion = fechaHoraEdicion;
    }

    public Post() {
    }

    public CategoriaPost getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaPost categoria) {
        this.categoria = categoria;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public void setFechaHoraCreacion(Calendar fechaHoraCreacion) {
        this.fechaHoraCreacion = fechaHoraCreacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Calendar getFechaHoraEdicion() {
        return fechaHoraEdicion;
    }

    public void setFechaHoraEdicion(Calendar fechaHoraEdicion) {
        this.fechaHoraEdicion = fechaHoraEdicion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.fechaHoraCreacion);
        hash = 53 * hash + Objects.hashCode(this.titulo);
        hash = 53 * hash + Objects.hashCode(this.contenido);
        hash = 53 * hash + Objects.hashCode(this.fechaHoraEdicion);
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
        final Post other = (Post) obj;
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.contenido, other.contenido)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.fechaHoraCreacion, other.fechaHoraCreacion)) {
            return false;
        }
        return Objects.equals(this.fechaHoraEdicion, other.fechaHoraEdicion);
    }
    
    
    
}
