/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.aplicacionesweb.themusichub.entidades;

import java.io.Serializable;
import java.util.GregorianCalendar;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Abe
 */
@Entity
@Table(name="anclados")
public class Anclado extends Post implements Serializable {


    public Anclado() {
    }

    @Override
    public String toString() {
        return "Anclado{" + '}';
    }

    
    
}
