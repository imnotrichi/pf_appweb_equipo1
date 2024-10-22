/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.itson.aplicacionesweb.themusichub.daos;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.itson.aplicacionesweb.themusichub.conexion.Conexion;
import org.itson.aplicacionesweb.themusichub.conexion.IConexion;
import org.itson.aplicacionesweb.themusichub.modelo.Normal;
import org.itson.aplicacionesweb.themusichub.modelo.Usuario;
import org.itson.aplicacionesweb.themusichub.persistenciaException.PersistenciaException;

/**
 *
 * @author Abe
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PersistenciaException {
        IConexion conexion = new Conexion();
        
        IUsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
        
        String contra = "si";
        String telefono = "13123";
        Usuario usuario = new Normal("Juanito", "Sanchez", "Perez", "hola@gmail.com", contra.getBytes(), telefono.getBytes(), "Si", "obregon", new GregorianCalendar(), "Mujer");
        usuarioDAO.registrarUsuario(usuario);
    }
    
}
