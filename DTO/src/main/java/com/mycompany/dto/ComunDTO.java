package com.mycompany.dto;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Equipo1
 */
public class ComunDTO extends PostDTO {

    /**
     * Constructor para posts comunes nuevos.
     *
     * @param fechaHoraCreacion
     * @param titulo
     * @param contenido
     * @param categoria
     * @param usuario
     */
    public ComunDTO(Calendar fechaHoraCreacion, String titulo, String subtitulo, String contenido, String categoria, UsuarioDTO usuario, String imagen) {
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
     * Constructor para posts comunes existentes.
     *
     * @param id ID del post.
     * @param fechaHoraCreacion
     * @param titulo
     * @param contenido
     * @param categoria
     * @param usuario
     */
    public ComunDTO(Long id, Calendar fechaHoraCreacion, String titulo, String subtitulo, String contenido, String categoria, UsuarioDTO usuario, List<ComentarioDTO> comentarios, String imagen) {
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

    public ComunDTO(Long id, Calendar fechaHoraCreacion, String titulo, String subtitulo, String contenido, String categoria, UsuarioDTO usuario, List<ComentarioDTO> comentarios, String imagen, String tipoPost) {
        super(
                id,
                fechaHoraCreacion,
                titulo,
                subtitulo,
                contenido,
                categoria,
                usuario,
                comentarios,
                imagen,
                tipoPost);
    }

}
