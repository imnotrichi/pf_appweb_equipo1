package com.mycompany.dto;

import java.util.List;

/**
 *
 * @author Equipo1
 */
public class EstadoDTO {
    
    private String nombre;
    private List<MunicipioDTO> municipio;

    public EstadoDTO(String nombre, List<MunicipioDTO> municipio) {
        this.nombre = nombre;
        this.municipio = municipio;
    }

    public String getNombre() {
        return nombre;
    }

    public List<MunicipioDTO> getMunicipio() {
        return municipio;
    }
    
}
