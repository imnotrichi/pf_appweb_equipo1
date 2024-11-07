package com.mycompany.dto;

import java.util.Calendar;

/**
 *
 * @author Equipo1
 */
public class AncladoDTO extends PostNuevoDTO{
    
    private AdministradorDTO administrador;

    public AncladoDTO(Calendar fechaHoraCreacion, String titulo, String contenido, String categoria) {
        super(fechaHoraCreacion, titulo, contenido, categoria);
    }

    public AdministradorDTO getAdministrador() {
        return administrador;
    }

    public void setAdministrador(AdministradorDTO administrador) {
        this.administrador = administrador;
    }
    
    
}
