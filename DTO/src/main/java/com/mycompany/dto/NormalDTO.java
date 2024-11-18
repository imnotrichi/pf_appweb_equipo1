package com.mycompany.dto;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Equipo1
 */
public class NormalDTO extends UsuarioDTO{
    
    private List<ComentarioDTO> comentarios;
    
    
    public NormalDTO(List<ComentarioDTO> comentarios, String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String contrasenia, String telefono, String ciudad, Calendar fechaNacimiento, String genero, MunicipioDTO municipio) {
        super(nombres, apellidoPaterno, apellidoMaterno, correo, contrasenia, telefono, ciudad, fechaNacimiento, genero, municipio);
        this.comentarios = comentarios;
    }

    public NormalDTO(String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String contrasenia, String telefono, String ciudad, Calendar fechaNacimiento, String genero, MunicipioDTO municipio) {
        super(nombres, apellidoPaterno, apellidoMaterno, correo, contrasenia, telefono, ciudad, fechaNacimiento, genero, municipio);
    }
    
    

    
    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }
    
}
