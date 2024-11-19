///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
// */
//package org.itson.aplicacionesweb.themusichub.facade;
//
//import com.mycompany.dto.AdministradorDTO;
//import com.mycompany.dto.PostDTO;
//import com.mycompany.dto.UsuarioDTO;
//import java.util.GregorianCalendar;
//import java.util.List;
//import static org.itson.aplicacionesweb.themusichub.enums.CategoriaPost.NOTICIAS;
//import org.itson.aplicacionesweb.themusichub.persistenciaException.FacadeException;
//
///**
// *
// * @author Abe
// */
//public class Pruebas {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) throws FacadeException {
//        IAccesoDatosFacade facade = new AccesoDatosFacade();
////        facade.registrarUsuario(new UsuarioDTO("Diego", "Valenzuela", "Parra", "eskibidio@gmail.com", "12345", "0123456789", "Pepiwate", "", "Navoyork", new GregorianCalendar(), "Masculino", new MunicipioDTO("Cajeme", new EstadoDTO("Sonora"))));
//        UsuarioDTO usuario = facade.obtenerUsuario("eskibidio@gmail.com", "12345");
//        AdministradorDTO usuarioDTO = new AdministradorDTO(
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
//        PostDTO post = new PostDTO(new GregorianCalendar(), "ARRIBA ENTHEOS y así", "we are the vessel typeshi!", NOTICIAS.toString(), usuarioDTO);
//        facade.publicarPost(post);
//
//        List<PostDTO> lista = facade.obtenerPostsPorCategoria(null);
//        for (PostDTO postList : lista) {
//            if (postList.getUsuario() == null) {
//                System.out.println("Usuario nulo");
//            } else {
//                System.out.println(postList.getUsuario().getNombres());
//            }
//
//        }
//    }
//
//}
