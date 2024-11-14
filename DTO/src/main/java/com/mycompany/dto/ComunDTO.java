package com.mycompany.dto;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Equipo1
 */
public class ComunDTO extends PostNuevoDTO{
    
    private List<ComentarioDTO> comentarios;
    private UsuarioNuevoDTO usuario;

    public ComunDTO(Calendar fechaHoraCreacion, String titulo, String contenido, String categoria) {
        super(fechaHoraCreacion, titulo, contenido, categoria);
    }

    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    public UsuarioNuevoDTO getUsuario() {
        return usuario;
    }
    
}
