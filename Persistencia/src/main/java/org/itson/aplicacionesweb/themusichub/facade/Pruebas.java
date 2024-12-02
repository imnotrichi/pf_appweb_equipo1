/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.itson.aplicacionesweb.themusichub.facade;

import com.mycompany.dto.AdministradorDTO;
import com.mycompany.dto.ComentarioDTO;
import com.mycompany.dto.ComunDTO;
import com.mycompany.dto.NormalDTO;
import com.mycompany.dto.PostDTO;
import com.mycompany.dto.UsuarioDTO;
import java.util.GregorianCalendar;
import java.util.List;
import static org.itson.aplicacionesweb.themusichub.enums.CategoriaPost.GENERAL;
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
        IAccesoDatosFacade facade = new AccesoDatosFacade();
        List<PostDTO> posts = facade.obtenerPostsPorCategoria(null);
        for (int i = 0; i < posts.size(); i++) {
            System.out.println(i + posts.get(i).getCategoria());
            i++;
        }
    }

}
