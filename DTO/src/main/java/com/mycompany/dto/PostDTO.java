/*
 * PostDTO.java
 */
package com.mycompany.dto;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Equipo 1
 */
public class PostDTO {

    private Long id;
    private Calendar fechaHoraCreacion;
    private String titulo;
    private String subtitulo;
    private String contenido;
    private String categoria;
    private String imagen;
    private List<ComentarioDTO> comentarios;
    private UsuarioDTO usuario;
    private String tipoPost;

    /**
     * Constructor para un PostDTO nuevo.
     *
     * @param fechaHoraCreacion
     * @param titulo
     * @param subtitulo
     * @param contenido
     * @param categoria
     * @param usuario
     */
    public PostDTO(Calendar fechaHoraCreacion, String titulo, String subtitulo, String contenido, String categoria, UsuarioDTO usuario, String imagen) {
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.contenido = contenido;
        this.categoria = categoria;
        this.usuario = usuario;
        if (imagen.isBlank()) {
            imagen = "";
        }
        this.imagen = imagen;
        this.comentarios = new LinkedList<>();
    }

    /**
     * Constructor para un PostDTO existente.
     *
     * @param id ID del post.
     * @param fechaHoraCreacion
     * @param titulo
     * @param subtitulo
     * @param contenido
     * @param categoria
     * @param usuario
     */
    public PostDTO(Long id, Calendar fechaHoraCreacion, String titulo, String subtitulo, String contenido, String categoria, UsuarioDTO usuario, List<ComentarioDTO> comentarios, String imagen) {
        this.id = id;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.contenido = contenido;
        this.categoria = categoria;
        this.usuario = usuario;
        if (imagen.isBlank()) {
            imagen = "";
        }
        this.imagen = imagen;
        this.comentarios = comentarios;
    }

    /**
     * Constructor para un PostDTO existente.
     *
     * @param id ID del post.
     * @param fechaHoraCreacion
     * @param titulo
     * @param subtitulo
     * @param contenido
     * @param categoria
     * @param usuario
     * @param imagen
     */
    public PostDTO(Long id, Calendar fechaHoraCreacion, String titulo, String subtitulo, String contenido, String categoria, UsuarioDTO usuario, String imagen) {
        this.id = id;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.contenido = contenido;
        this.categoria = categoria;
        if (imagen.isBlank()) {
            imagen = "";
        }
        this.imagen = imagen;
        this.usuario = usuario;
    }

    public PostDTO(Long id, Calendar fechaHoraCreacion, String titulo, String subtitulo, String contenido, String imagen, List<ComentarioDTO> comentarios, UsuarioDTO usuario) {
        this.id = id;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.contenido = contenido;
        this.imagen = imagen;
        this.comentarios = comentarios;
        this.usuario = usuario;
    }

    public PostDTO(Long id, Calendar fechaHoraCreacion, String contenido, NormalDTO usuario) {
        this.id = id;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.contenido = contenido;
        this.usuario = usuario;
    }

    public PostDTO(Long id, Calendar fechaHoraCreacion, String titulo, String subtitulo, String contenido, String categoria, UsuarioDTO usuario, List<ComentarioDTO> comentarios, String imagen, String tipoPost) {
        this.id = id;
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.titulo = titulo;
        this.subtitulo = subtitulo;
        this.contenido = contenido;
        this.categoria = categoria;
        this.usuario = usuario;
        if (imagen.isBlank()) {
            imagen = "";
        }
        this.imagen = imagen;
        this.comentarios = comentarios;
        this.tipoPost = tipoPost;
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

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public String getTipoPost() {
        return tipoPost;
    }

    public void setTipoPost(String tipoPost) {
        this.tipoPost = tipoPost;
    }

    
}
