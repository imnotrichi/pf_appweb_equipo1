/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.itson.aplicacionesweb.themusichub.facade;

import com.mycompany.dto.ComentarioDTO;
import com.mycompany.dto.ComunDTO;
import com.mycompany.dto.EstadoDTO;
import com.mycompany.dto.MunicipioDTO;
import com.mycompany.dto.NormalDTO;
import com.mycompany.dto.NormalNuevoDTO;
import com.mycompany.dto.PostNuevoDTO;
import com.mycompany.dto.UsuarioDTO;
import com.mycompany.dto.UsuarioNuevoDTO;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.itson.aplicacionesweb.themusichub.persistenciaException.FacadeException;

/**
 *
 * @author Abe
 */
public class Pruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FacadeException {
        IAccesoDatosFacade accesoDatos = new AccesoDatosFacade();

        EstadoDTO estado = new EstadoDTO("Sonora");
        MunicipioDTO municipio = new MunicipioDTO("Cajeme",
                estado);
//        UsuarioNuevoDTO usuarioNuevodto = new NormalNuevoDTO("Ricardo Alán", 
//                "Gutierrez", 
//                "Garcés", 
//                "ricardo.gutierrez@gmail.com", 
//                "Ricardo12345", 
//                "45545", 
//                "Obregón", 
//                new GregorianCalendar(2004, 3, 21), 
//                "Masculino", 
//                municipio);
//        accesoDatos.registrarUsuario(usuarioNuevodto);

        UsuarioDTO usuarioComenta = new NormalDTO("Ricardo Alán", 
                "Gutierrez",
                "Garcés",
                "ricardo.gutierrez@gmail.com",
                "Ricardo12345",
                "45545",
                "Obregón",
                new GregorianCalendar(2004, 3, 21),
                "Masculino",
                municipio);
                
        UsuarioDTO usuarioDTO = new NormalDTO("Abel Eduardo",
                "Sanchez",
                "Guerrero",
                "abel.sanchez@gmail.com",
                "Abel12345",
                "65656566",
                "Obregón",
                new GregorianCalendar(2004, 5, 13),
                "Masculino",
                municipio);

        PostNuevoDTO post = new ComunDTO(new GregorianCalendar(2024, 10, 27),
                "Bb XCX",
                "Me encanta esta canción",
                "review");
//
//        accesoDatos.publicarPost(post, usuarioDTO);
//        accesoDatos.eliminarPost(post, usuarioDTO);
//
        ComentarioDTO comentario = new ComentarioDTO(
                Calendar.getInstance(), 
                "Me encanta, opino lo mismo", 
               (NormalDTO)usuarioComenta);

        accesoDatos.comentarPost(comentario, usuarioDTO, post);

    }

}
