/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto;

import java.util.Calendar;

/**
 *
 * @author Equipo1
 */
public class NormalNuevoDTO extends UsuarioNuevoDTO{

    public NormalNuevoDTO(String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String contrasenia, String telefono, String avatar, String ciudad, String nombreUsuario, Calendar fechaNacimiento, String genero, MunicipioDTO municipio) {
        super(nombres, apellidoPaterno, apellidoMaterno, correo, contrasenia, telefono, avatar, ciudad, nombreUsuario, fechaNacimiento, genero, municipio);
    }
    
    
    
}
