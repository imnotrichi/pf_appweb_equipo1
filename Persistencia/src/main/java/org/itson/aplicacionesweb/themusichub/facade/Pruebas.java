/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.itson.aplicacionesweb.themusichub.facade;

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
//        facade.registrarUsuario(new UsuarioDTO("Diego", "Valenzuela", "Parra", "eskibidio@gmail.com", "12345", "0123456789", "Pipucate", "", "Ciudad Obregón", new GregorianCalendar(2004, 6, 3), "M", new MunicipioDTO("Cajeme", new EstadoDTO("Sonora"))));
        NormalDTO usuario = (NormalDTO) facade.obtenerUsuario("eskibidio@gmail.com", "12345");
//        NormalDTO usuarioDTO = new NormalDTO(
//                usuario.getNombres(),
//                usuario.getApellidoPaterno(),
//                usuario.getApellidoMaterno(),
//                usuario.getCorreo(),
//                usuario.getContrasenia(),
//                usuario.getTelefono(),
//                usuario.getNombreUsuario(),
//                usuario.getAvatar(),
//                usuario.getCiudad(),
//                usuario.getFechaNacimiento(),
//                usuario.getGenero(),
//                usuario.getMunicipio());
//        ComunDTO post = new ComunDTO(new GregorianCalendar(), "Fishmans", "Nice choice", "sdasdasdas", GENERAL.toString(), usuarioDTO);
//        facade.publicarPost(post);

        PostDTO postDTO = facade.obtenerPostID(3L);

        ComentarioDTO comentarioDTO2 = facade.obtenerComentarioID(1L);

        ComentarioDTO respuestaDTO = new ComentarioDTO(new GregorianCalendar(), "RESPUESTA DE PRUEBA", comentarioDTO2, usuario);

        facade.responderComentario(respuestaDTO, comentarioDTO2);
    }

}
