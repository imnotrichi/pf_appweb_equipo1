package com.mycompany.dto;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Equipo1
 */
public class NormalDTO extends UsuarioDTO{
    
    private List<ComentarioDTO> comentarios;

    public NormalDTO(String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String contrasenia, String telefono, String nombreUsuario, String avatar, String ciudad, Calendar fechaNacimiento, String genero, MunicipioDTO municipio) {
        super(nombres, apellidoPaterno, apellidoMaterno, correo, contrasenia, telefono, nombreUsuario, avatar, ciudad, fechaNacimiento, genero, municipio);
    }
    
    public NormalDTO(String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String contrasenia, String telefono, String nombreUsuario, String avatar, String ciudad, Calendar fechaNacimiento, String genero, List<PostDTO> posts, MunicipioDTO municipio) {
        super(nombres, apellidoPaterno, apellidoMaterno, correo, contrasenia, telefono, nombreUsuario, avatar, ciudad, fechaNacimiento, genero, posts, municipio);
    }
   
    
    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }
    
}
