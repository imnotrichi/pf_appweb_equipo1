package com.mycompany.dto;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Equipo1
 */
public class AncladoDTO extends PostDTO{
    
    /**
     * Constructor para posts anclados.
     * @param id ID del post.
     * @param fechaHoraCreacion
     * @param titulo
     * @param subtitulo
     * @param contenido
     * @param categoria
     * @param usuario 
     * @param comentarios 
     */
    public AncladoDTO(Long id, Calendar fechaHoraCreacion, String titulo, String subtitulo, String contenido, String categoria, UsuarioDTO usuario, List<ComentarioDTO> comentarios, String imagen) {
        super(
                id,
                fechaHoraCreacion,
                titulo,
                subtitulo,
                contenido,
                categoria,
                usuario,
                comentarios,
                imagen);
    }
    
}