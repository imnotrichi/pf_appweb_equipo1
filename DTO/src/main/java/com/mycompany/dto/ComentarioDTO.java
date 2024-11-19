package com.mycompany.dto;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Equipo1
 */
public class ComentarioDTO {
    
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

    public ComentarioDTO(Long id, Calendar fechaHora, String contenido, UsuarioDTO convertirUsuarioAUsuarioDTO, List<ComentarioDTO> convertirComentariosAComentariosDTO) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    
}
