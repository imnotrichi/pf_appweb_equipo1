package com.mycompany.dto;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Equipo1
 */
public class ComentarioDTO {
    
    private String id;
    private Calendar fechaHora;
    private String contenido;
    private List<ComentarioDTO> respuestas;
    private ComentarioDTO respuesta;
    private ComunDTO post;
    private NormalDTO usuario;

    public ComentarioDTO(Calendar fechaHora, String contenido, NormalDTO usuario) {
        this.fechaHora = fechaHora;
        this.contenido = contenido;
        this.usuario = usuario;
    }

    public ComentarioDTO(String id, Calendar fechaHora, String contenido, ComunDTO post) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.contenido = contenido;
        this.post = post;
    }

    public ComentarioDTO(Calendar fechaHora, String contenido, ComunDTO post, NormalDTO usuario) {
        this.fechaHora = fechaHora;
        this.contenido = contenido;
        this.post = post;
        this.usuario = usuario;
    }

    
    
    
    

    public Calendar getFechaHora() {
        return fechaHora;
    }

    public String getContenido() {
        return contenido;
    }

    public List<ComentarioDTO> getRespuestas() {
        return respuestas;
    }

    public ComentarioDTO getRespuesta() {
        return respuesta;
    }

    public ComunDTO getPost() {
        return post;
    }

    public NormalDTO getUsuario() {
        return usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFechaHora(Calendar fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setRespuestas(List<ComentarioDTO> respuestas) {
        this.respuestas = respuestas;
    }

    public void setRespuesta(ComentarioDTO respuesta) {
        this.respuesta = respuesta;
    }

    public void setPost(ComunDTO post) {
        this.post = post;
    }

    public void setUsuario(NormalDTO usuario) {
        this.usuario = usuario;
    }
    
    
    
}
