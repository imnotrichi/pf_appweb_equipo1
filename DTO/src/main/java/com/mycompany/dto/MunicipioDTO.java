package com.mycompany.dto;

import java.util.List;

/**
 *
 * @author Equipo1
 */
public class MunicipioDTO {
    
    private String nombre;
    private List<UsuarioDTO> usuarios;
    private EstadoDTO estado;

    public MunicipioDTO(String nombre, EstadoDTO estado) {
        this.nombre = nombre;
        this.estado = estado;
    }

    public String getNombre() {
        return nombre;
    }

    public List<UsuarioDTO> getUsuarios() {
        return usuarios;
    }

    public EstadoDTO getEstado() {
        return estado;
    }
    
}
