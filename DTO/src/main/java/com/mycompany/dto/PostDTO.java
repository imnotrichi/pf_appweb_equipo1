package com.mycompany.dto;

import java.util.Calendar;

/**
 *
 * @author Equipo1
 */
public class PostDTO {
    
    private Calendar fechaHoraCreacion;
    private String titulo;
    private String contenido;
    private String categoria;
    private Calendar fechaHoraEdicion;

    public PostDTO(Calendar fechaHoraCreacion, String titulo, String contenido, String categoria) {
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.titulo = titulo;
        this.contenido = contenido;
        this.categoria = categoria;
    }

    public Calendar getFechaHoraCreacion() {
        return fechaHoraCreacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public String getCategoria() {
        return categoria;
    }

    public Calendar getFechaHoraEdicion() {
        return fechaHoraEdicion;
    }

    public void setFechaHoraEdicion(Calendar fechaHoraEdicion) {
        this.fechaHoraEdicion = fechaHoraEdicion;
    }
    
}
