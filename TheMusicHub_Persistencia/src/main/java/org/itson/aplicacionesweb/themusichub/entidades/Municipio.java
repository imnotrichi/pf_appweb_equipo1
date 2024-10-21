/*
 * Municipio.java
 */
package org.itson.aplicacionesweb.themusichub.entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Equipo1
 */
@Entity
@Table(name = "municipios")
public class Municipio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_municipio")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @OneToOne(mappedBy = "municipio", cascade = CascadeType.PERSIST)
    private Usuario usuario;
    
    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;

    /**
     * Constructor vacío.
     */
    public Municipio() {
    }

    public Municipio(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Municipio)) {
            return false;
        }
        Municipio other = (Municipio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Municipio{");
        sb.append("nombre=").append(nombre);
        sb.append(", usuario=").append(usuario);
        sb.append(", estado=").append(estado);
        sb.append('}');
        return sb.toString();
    }

}
