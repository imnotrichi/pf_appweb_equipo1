package com.mycompany.dto;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

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
    private String nombreUsuario;
    private Calendar fechaNacimiento;
    private String genero;
    private MunicipioDTO municipio;
    private List<PostDTO> posts;

    public UsuarioNuevoDTO(String nombres, String apellidoPaterno, String apellidoMaterno, String correo, String contrasenia, String telefono, String avatar, String ciudad, String nombreUsuario, Calendar fechaNacimiento, String genero, MunicipioDTO municipio) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.avatar = avatar;
        this.ciudad = ciudad;
        this.nombreUsuario = nombreUsuario;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
        this.municipio = municipio;
        this.posts = new LinkedList<>();
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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

    public List<PostDTO> getPosts() {
        return posts;
    }

}
