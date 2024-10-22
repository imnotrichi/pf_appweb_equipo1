package com.mycompany.dto;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Equipo1
 */
public class UsuarioDTO {
    
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String correo;
    private byte[] contrasenia;
    private byte[] telefono;
    private String avatar;
    private String ciudad;
    private Calendar fechaNacimiento;
    private String genero;
    private MunicipioDTO municipio;
    private List<ComunDTO> posts;

    public UsuarioDTO(String nombres, String apellidoPaterno, String apellidoMaterno, String correo, byte[] contrasenia, byte[] telefono, String ciudad, Calendar fechaNacimiento, String genero, MunicipioDTO municipio) {
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

    public byte[] getContrasenia() {
        return contrasenia;
    }

    public byte[] getTelefono() {
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

    public List<ComunDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<ComunDTO> posts) {
        this.posts = posts;
    }
    
}
