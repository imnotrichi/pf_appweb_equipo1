/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto;

import java.util.Calendar;

/**
 *
 * @author ricar
 */
public class PostDTO {
    private Calendar fechaHoraCreacion;
    private String titulo;
    private String contenido;
    private String categoria;
    private UsuarioNuevoDTO usuario;

    public PostDTO(Calendar fechaHoraCreacion, String titulo, String contenido, String categoria, UsuarioNuevoDTO usuario) {
        this.fechaHoraCreacion = fechaHoraCreacion;
        this.titulo = titulo;
        this.contenido = contenido;
        this.categoria = categoria;
        this.usuario = usuario;
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

    public UsuarioNuevoDTO getUsuario() {
        return usuario;
    }
    
    
}
