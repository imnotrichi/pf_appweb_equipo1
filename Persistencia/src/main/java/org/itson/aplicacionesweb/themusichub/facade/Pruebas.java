/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.itson.aplicacionesweb.themusichub.facade;

import com.mycompany.dto.EstadoDTO;
import com.mycompany.dto.MunicipioDTO;
import com.mycompany.dto.NormalDTO;
import com.mycompany.dto.PostDTO;
import com.mycompany.dto.PostNuevoDTO;
import com.mycompany.dto.UsuarioDTO;
import com.mycompany.dto.UsuarioNuevoDTO;
import java.util.GregorianCalendar;
import java.util.List;
import static org.itson.aplicacionesweb.themusichub.modelo.CategoriaPost.GENERAL;
import static org.itson.aplicacionesweb.themusichub.modelo.CategoriaPost.REVIEWS;
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
//        facade.registrarUsuario(new UsuarioNuevoDTO("Diego", "Valenzuela", "Parra", "pipu@gmail.com", "12345", "0123456789", "Obregón", new GregorianCalendar(), "Masculino", new MunicipioDTO("Cajeme", new EstadoDTO("Sonora"))));
//        UsuarioDTO usuario = facade.obtenerUsuario("pipu@gmail.com", "12345");
//        NormalDTO normal = new NormalDTO(
//                usuario.getNombres(),
//                usuario.getApellidoPaterno(),
//                usuario.getApellidoMaterno(),
//                usuario.getCorreo(),
//                usuario.getContrasenia(),
//                usuario.getTelefono(),
//                usuario.getCiudad(),
//                usuario.getFechaNacimiento(),
//                usuario.getGenero(),
//                usuario.getMunicipio());
//        PostNuevoDTO post1 = new PostNuevoDTO(new GregorianCalendar(), "asdasdasd", "lkllklklklklkl", GENERAL.toString());
//        facade.publicarPost(post1, normal);
//        PostNuevoDTO post2 = new PostNuevoDTO(new GregorianCalendar(), "UCUUCUCUCUCUCU", "=)=)=)=90909", REVIEWS.toString());
//        facade.publicarPost(post2, normal);

        List<PostDTO> lista = facade.obtenerPostsPorCategoria(null);
        for (PostDTO postList : lista) {
            if (postList.getUsuario() == null) {
                System.out.println("Usuario nulo");
            } else {
                System.out.println(postList.getUsuario().getNombres());
            }

        }
    }

}
