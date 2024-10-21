/*
 * Usuario.java
 */
package org.itson.aplicacionesweb.themusichub.entidades;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Equipo1
 */
@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_usuario")
    protected Long id;

    @Column(name = "nombres", nullable = false, length = 50)
    protected String nombres;

    @Column(name = "apellidoPaterno", nullable = false, length = 50)
    protected String apellidoPaterno;

    @Column(name = "apellidoMaterno", length = 50)
    protected String apellidoMaterno;

    @Column(name = "correo", nullable = false, length = 200)
    protected String correo;

    @Column(name = "contrasenia", nullable = false, columnDefinition = "VARBINARY(32)")
    @Lob
    protected byte[] contrasenia;

    @Column(name = "telefono", nullable = false, columnDefinition = "VARBINARY(32)")
    @Lob
    protected byte[] telefono;

    @Column(name = "avatar", length = 500)
    protected String avatar;

    @Column(name = "ciudad", length = 150)
    protected String ciudad;

    @Column(name = "fechaNacimiento", nullable = false)
    @Temporal(TemporalType.DATE)
    protected Calendar fechaNacimiento;

    @Column(name = "genero", length = 20)
    protected String genero;

    /**
     * Constructor vacío.
     */
    public Usuario() {
    }

    public Usuario(String nombres, String apellidoPaterno, String apellidoMaterno, String correo, byte[] contrasenia, byte[] telefono, String avatar, String ciudad, Calendar fechaNacimiento, String genero) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.avatar = avatar;
        this.ciudad = ciudad;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public byte[] getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(byte[] contrasenia) {
        this.contrasenia = contrasenia;
    }

    public byte[] getTelefono() {
        return telefono;
    }

    public void setTelefono(byte[] telefono) {
        this.telefono = telefono;
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

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{");
        sb.append("nombres=").append(nombres);
        sb.append(", apellidoPaterno=").append(apellidoPaterno);
        sb.append(", apellidoMaterno=").append(apellidoMaterno);
        sb.append(", correo=").append(correo);
        sb.append(", contrasenia=").append(contrasenia);
        sb.append(", telefono=").append(telefono);
        sb.append(", avatar=").append(avatar);
        sb.append(", ciudad=").append(ciudad);
        sb.append(", fechaNacimiento=").append(fechaNacimiento);
        sb.append(", genero=").append(genero);
        sb.append('}');
        return sb.toString();
    }

}
