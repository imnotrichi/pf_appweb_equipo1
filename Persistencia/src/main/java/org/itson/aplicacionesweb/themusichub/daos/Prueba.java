/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.itson.aplicacionesweb.themusichub.daos;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import org.itson.aplicacionesweb.themusichub.conexion.Conexion;
import org.itson.aplicacionesweb.themusichub.conexion.IConexion;
import static org.itson.aplicacionesweb.themusichub.modelo.Comun_.usuario;
import org.itson.aplicacionesweb.themusichub.modelo.Estado;
import org.itson.aplicacionesweb.themusichub.modelo.Municipio;
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
        Municipio municipio = new Municipio("Obregon");
        Estado estado = new Estado("Sonora");
        
        List<Municipio> municipios = new LinkedList<>();
        estado.setMunicipio(municipios);
        municipio.setEstado(estado);
        
        Usuario usuario = new Normal("Juanito", "Sanchez", "Perez", "hola@gmail.com", contra.getBytes(), telefono.getBytes(), "Si", "obregon", new GregorianCalendar(), "Mujer");
        usuario.setMunicipio(municipio);
        
        List<Usuario> usuarios = new LinkedList<>();
        usuarios.add(usuario);
        municipio.setUsuarios(usuarios);
        
        usuarioDAO.registrarUsuario(usuario);
        
    }
    
}
