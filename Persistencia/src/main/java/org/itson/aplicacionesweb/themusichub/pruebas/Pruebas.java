/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.itson.aplicacionesweb.themusichub.pruebas;

import com.mycompany.dto.PostDTO;
import java.util.GregorianCalendar;
import org.itson.aplicacionesweb.themusichub.facade.AccesoDatosFacade;
import org.itson.aplicacionesweb.themusichub.facade.IAccesoDatosFacade;

/**
 *
 * @author ricar
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        IAccesoDatosFacade facade = new AccesoDatosFacade();
        
        PostDTO postNuevo = new PostDTO(new GregorianCalendar(), "HolaMundo", "Hola", "General");

        
    }
    
}
