package com.mycompany.dto;

import java.util.List;

/**
 *
 * @author Equipo1
 */
public class ComunDTO {
    
    private List<ComentarioDTO> comentarios;
    private UsuarioDTO usuario;

    public ComunDTO() {
    }

    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }
    
}
