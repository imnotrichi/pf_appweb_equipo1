/*
 * Estado.java
 */
package org.itson.aplicacionesweb.themusichub.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Equipo1
 */
@Entity
@Table(name = "estados")
public class Estado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Long id;
    
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @OneToMany(mappedBy = "estado", cascade = CascadeType.PERSIST)
    private List<Municipio> municipio;

    /**
     * Constructor vacío.
     */
    public Estado() {
    }

    public Estado(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Municipio> getMunicipio() {
        return municipio;
    }

    public void setMunicipio(List<Municipio> municipio) {
        this.municipio = municipio;
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
        if (!(object instanceof Estado)) {
            return false;
        }
        Estado other = (Estado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Estado{");
        sb.append("nombre=").append(nombre);
        sb.append(", municipio=").append(municipio);
        sb.append('}');
        return sb.toString();
    }
    
}
