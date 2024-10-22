/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.itson.aplicacionesweb.themusichub.daos;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.itson.aplicacionesweb.themusichub.auxiliares.AESEncriptador;
import org.itson.aplicacionesweb.themusichub.auxiliares.Encriptador;
import org.itson.aplicacionesweb.themusichub.conexion.Conexion;
import org.itson.aplicacionesweb.themusichub.conexion.IConexion;
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
        try {
            IConexion conexion = new Conexion();

            IUsuarioDAO usuarioDAO = new UsuarioDAO(conexion);

            // Crear Estado
            Estado estado = new Estado("Sonora");

            // Crear Municipio
            Municipio municipio = new Municipio("Obregon");
            municipio.setEstado(estado);

            // Crear Usuario
            Usuario usuario = new Normal(
                    "Juanito",
                    "Sanchez",
                    "Perez",
                    "hola@gmail.com",
                    "contraseña123",
                    "6441234567",
                    "avatar.jpg",
                    "Obregon",
                    new GregorianCalendar(1990, Calendar.JANUARY, 1),
                    "Mujer"
            );

            usuario.setMunicipio(municipio);
            AESEncriptador ae = new AESEncriptador();

            usuario.setContrasenia(AESEncriptador.encriptar(usuario.getContrasenia()));
            usuarioDAO.registrarUsuario(usuario);

            try {
                Usuario iniciarSesion = usuarioDAO.iniciarSesion(usuario.getContrasenia(), "hola@gmail.com");
                System.out.println("Se inicio sesión");
            } catch (Exception e) {
                System.out.println("Ese usuario no existe");
            }

        } catch (PersistenciaException e) {
            System.err.println("Error al persistir los datos: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error general: " + e.getMessage());
            e.printStackTrace();
        } finally {

        }

    }

}
