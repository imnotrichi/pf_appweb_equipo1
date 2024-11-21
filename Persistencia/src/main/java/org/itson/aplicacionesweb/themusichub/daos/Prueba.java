///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
// */
//package org.itson.aplicacionesweb.themusichub.daos;
//
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.itson.aplicacionesweb.themusichub.auxiliares.AESEncriptador;
//import org.itson.aplicacionesweb.themusichub.conexion.Conexion;
//import org.itson.aplicacionesweb.themusichub.conexion.IConexion;
//import org.itson.aplicacionesweb.themusichub.modelo.Anclado;
//import org.itson.aplicacionesweb.themusichub.modelo.CategoriaPost;
//import org.itson.aplicacionesweb.themusichub.modelo.Comentario;
//import org.itson.aplicacionesweb.themusichub.modelo.Comun;
//import org.itson.aplicacionesweb.themusichub.modelo.Estado;
//import org.itson.aplicacionesweb.themusichub.modelo.Municipio;
//import org.itson.aplicacionesweb.themusichub.modelo.Normal;
//import org.itson.aplicacionesweb.themusichub.modelo.Post;
//import org.itson.aplicacionesweb.themusichub.modelo.Usuario;
//import org.itson.aplicacionesweb.themusichub.persistenciaException.PersistenciaException;
//
///**
// *
// * @author Abe
// */
//public class Prueba {
//
//    private static final Logger logger = Logger.getLogger(Prueba.class.getName());
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) throws PersistenciaException {
//
////        try {
////            IConexion conexion = new Conexion();
////
////            IUsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
////
////            // Crear Estado
////            Estado estado = new Estado("Sonora");
////
////            // Crear Municipio
////            Municipio municipio = new Municipio("Obregon");
////            municipio.setEstado(estado);
////
////            // Crear Usuario
////            Usuario usuario = new Normal(
////                    "Juanito",
////                    "Sanchez",
////                    "Perez",
////                    "juanitoPerez@gmail.com",
////                    "contraseña123",
////                    "6441234567",
////                    "avatar.jpg",
////                    "Obregon",
////                    new GregorianCalendar(1990, Calendar.JANUARY, 1),
////                    "Masculino"
////            );
////
////            usuario.setMunicipio(municipio);
////
////            usuario.setContrasenia(AESEncriptador.encriptar(usuario.getContrasenia()));
////            usuarioDAO.registrarUsuario(usuario);
////
////            try {
////                Usuario iniciarSesion = usuarioDAO.iniciarSesion(usuario.getContrasenia(), "hola@gmail.com");
////                System.out.println("Se inicio sesión");
////                System.out.println(iniciarSesion.toString());
////            } catch (Exception e) {
////                System.out.println("Ese usuario no existe");
////            }
////
////        } catch (PersistenciaException e) {
////            System.err.println("Error al persistir los datos: " + e.getMessage());
////            e.printStackTrace();
////        } catch (Exception e) {
////            System.err.println("Error general: " + e.getMessage());
////            e.printStackTrace();
////        } finally {
////
////        }
//        IConexion conexion = new Conexion();
//
//        PostDAO postDAO = new PostDAO(conexion);
//
//        try {
////            // Crear un nuevo post y publicarlo
////            Comun nuevoPost = new Comun();
////            nuevoPost.setTitulo("Primer Post");
////            nuevoPost.setContenido("Este es el contenido del primer post.");
////            nuevoPost.setFechaHoraCreacion(Calendar.getInstance());
////            Usuario usuario = new Normal(
////                    "Juanito",
////                    "Sanchez",
////                    "Perez",
////                    "juanitoPerez@gmail.com",
////                    "contraseña123",
////                    "6441234567",
////                    "avatar.jpg",
////                    "Obregon",
////                    new GregorianCalendar(1990, Calendar.JANUARY, 1),
////                    "Masculino"
////            );
////            nuevoPost.setCategoria(CategoriaPost.GENERAL);
////            nuevoPost.setUsuario(usuario);
////            postDAO.publicarPost(nuevoPost);
////            
////            logger.log(Level.INFO, "Post publicado con éxito.");
//
//
//// Crear un nuevo post y publicarlo con comentario
//            Comun nuevoPost = new Comun();
//            nuevoPost.setTitulo("Segundo Post");
//            nuevoPost.setContenido("Este es el contenido del segundo post.");
//            nuevoPost.setFechaHoraCreacion(Calendar.getInstance());
//            Normal usuario = new Normal(
//                    "Juanito",
//                    "Sanchez",
//                    "Perez",
//                    "juanitoPerez@gmail.com",
//                    "contraseña123",
//                    "6441234567",
//                    "avatar.jpg",
//                    "Obregon",
//                    new GregorianCalendar(1990, Calendar.JANUARY, 1),
//                    "Masculino"
//            );
//            nuevoPost.setCategoria(CategoriaPost.PLAYLIST);
//            nuevoPost.setUsuario(usuario);
////            postDAO.publicarPost(nuevoPost);
////            logger.log(Level.INFO, "Post publicado con éxito.");
////            
////            Comentar
////            Comentario comentario = new Comentario(Calendar.getInstance(), "Me encantaaa:)", nuevoPost, usuario);
////            ComentarioDAO comentarioDAO = new ComentarioDAO(conexion);
////            comentarioDAO.publicarComentario(comentario);
//
//            // Obtener todos los posts
//            List<Post> listaPosts = postDAO.obtenerTodosPosts();
//            logger.log(Level.INFO, "Se han encontrado " + listaPosts.size() + " posts.");
////
//            // Actualizar el post
////            if (!listaPosts.isEmpty()) {
////                Post postAEditar = listaPosts.get(0);
////                postAEditar.setTitulo("Post Editado");
////
////                postDAO.editarPost(postAEditar);
////                logger.log(Level.INFO, "Post actualizado con éxito.");
////            }
//
////            // Eliminar el post
////            if (!listaPosts.isEmpty()) {
////                Post postAEliminar = listaPosts.get(0);
////
////                postDAO.eliminarPost(postAEliminar);
////                logger.log(Level.INFO, "Post eliminado con éxito.");
////            }
//
//            // Comentar
//            Comentario comentario = new Comentario(Calendar.getInstance(), "Me encantaaa:)", nuevoPost, usuario);
//            ComentarioDAO comentarioDAO = new ComentarioDAO(conexion);
//            
//            Comentario comentario1 = new Comentario(Calendar.getInstance(), "Yo lo odio la verdad :)", nuevoPost, usuario);
//            comentario1.setRespuesta(comentario);
//            comentarioDAO.publicarComentario(comentario1);
//
//
//        } catch (PersistenciaException e) {
//            logger.log(Level.SEVERE, "Error en la operación con posts.", e);
//        }
//    }
//
//    // Crear un post general
////        Calendar fechaCreacion = Calendar.getInstance();
////        Post post = new Comun();
////        
////        
////        System.out.println("Post creado: " + post.getTitulo());
////        // Crear un post anclado
////        Anclado anclado = new Anclado();
////        anclado.setTitulo("Post Anclado");
////        anclado.setContenido("Contenido importante anclado.");
////        anclado.setFechaHoraCreacion(fechaCreacion);
////        anclado.setCategoria(CategoriaPost.GENERAL);
////        System.out.println("Anclado creado: " + anclado.getTitulo());
////    
////        Usuario usuario = new Normal(
////                    "Juanito",
////                    "Sanchez",
////                    "Perez",
////                    "hola@gmail.com",
////                    "contraseña123",
////                    "6441234567",
////                    "avatar.jpg",
////                    "Obregon",
////                    new GregorianCalendar(1990, Calendar.JANUARY, 1),
////                    "Mujer"
////            );
////        
////        // Crear un comentario sobre el post
////        Comentario comentario = new Comentario(
////                Calendar.getInstance(), 
////                "Este es un comentario sobre el post", 
////                (Comun) post, 
////                (Normal)usuario
////        );
////        System.out.println("Comentario creado: " + comentario.getContenido());
////
////        // Crear respuesta a un comentario
////        Comentario respuesta = new Comentario(
////                Calendar.getInstance(), 
////                "Esta es una respuesta al comentario anterior", 
////                (Comun) post, 
////                (Normal)usuario
////        );
////        comentario.setRespuesta(respuesta);
////
////        System.out.println("Respuesta creada: " + respuesta.getContenido());
//}
