package com.mycompany.dto;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Equipo1
 */
public class AdministradorDTO extends UsuarioNuevoDTO{
    
    private List<AncladoDTO> postsAnclados;

    public AdministradorDTO(List<AncladoDTO> postsAnclados, String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String contrasenia, String telefono, String ciudad, Calendar fechaNacimiento, String genero, MunicipioDTO municipio) {
        super(nombres, apellidoPaterno, apellidoMaterno, correo, contrasenia, telefono, ciudad, fechaNacimiento, genero, municipio);
        this.postsAnclados = postsAnclados;
    }

    public List<AncladoDTO> getPostsAnclados() {
        return postsAnclados;
    }
    
}
