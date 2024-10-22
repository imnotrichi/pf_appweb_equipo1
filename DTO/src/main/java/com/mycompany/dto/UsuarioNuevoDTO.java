package com.mycompany.dto;

import java.util.Calendar;

/**
 *
 * @author Equipo1
 */
public class UsuarioNuevoDTO {
    
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private String contrasenia;
    private String telefono;
    private String avatar;
    private String ciudad;
    private Calendar fechaNacimiento;
    private String genero;
    private MunicipioDTO municipio;

    public UsuarioNuevoDTO(String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String contrasenia, String telefono, String ciudad, Calendar fechaNacimiento, String genero, MunicipioDTO municipio) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.municipio = municipio;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCiudad() {
        return ciudad;
    }

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public MunicipioDTO getMunicipio() {
        return municipio;
    }
    
}