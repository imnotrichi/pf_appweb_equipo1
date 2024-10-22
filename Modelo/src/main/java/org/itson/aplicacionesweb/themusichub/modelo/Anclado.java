/*
 * Anclado.java
 */
package org.itson.aplicacionesweb.themusichub.modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Equipo1
 */
@Entity
@Table(name = "anclados")
public class Anclado extends Post implements Serializable {

    @ManyToOne
    @JoinColumn(name = "id_administrador", nullable = false)
    private Administrador administrador;

    public Anclado() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Anclado{");
        sb.append("administrador=").append(administrador);
        sb.append('}');
        return sb.toString();
    }

}
