/*
 * AccesoDatosFacade.java
 */
package org.itson.aplicacionesweb.themusichub.facade;

import com.mycompany.dto.AdministradorDTO;
import com.mycompany.dto.AncladoDTO;
import com.mycompany.dto.ComentarioDTO;
import com.mycompany.dto.ComunDTO;
import com.mycompany.dto.NormalDTO;
import com.mycompany.dto.PostNuevoDTO;
import com.mycompany.dto.UsuarioDTO;
import com.mycompany.dto.UsuarioNuevoDTO;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.AEADBadTagException;
import org.itson.aplicacionesweb.themusichub.auxiliares.AESEncriptador;
import org.itson.aplicacionesweb.themusichub.conexion.Conexion;
import org.itson.aplicacionesweb.themusichub.conexion.IConexion;
import org.itson.aplicacionesweb.themusichub.daos.IComentarioDAO;
import org.itson.aplicacionesweb.themusichub.daos.IPostDAO;
import org.itson.aplicacionesweb.themusichub.daos.IUsuarioDAO;
import org.itson.aplicacionesweb.themusichub.factory.AbstractDAOFactory;
import org.itson.aplicacionesweb.themusichub.factory.DAOFactory;
import org.itson.aplicacionesweb.themusichub.modelo.Administrador;
import org.itson.aplicacionesweb.themusichub.modelo.Anclado;
import org.itson.aplicacionesweb.themusichub.modelo.CategoriaPost;
import org.itson.aplicacionesweb.themusichub.modelo.Comentario;
import org.itson.aplicacionesweb.themusichub.modelo.Comun;
import org.itson.aplicacionesweb.themusichub.modelo.Normal;
import org.itson.aplicacionesweb.themusichub.modelo.Post;
import org.itson.aplicacionesweb.themusichub.modelo.Usuario;
import org.itson.aplicacionesweb.themusichub.persistenciaException.FacadeException;
import org.itson.aplicacionesweb.themusichub.persistenciaException.PersistenciaException;

/**
 * @author Equipo1
 */
public class AccesoDatosFacade implements IAccesoDatosFacade {

    private IConexion conexion;
    private AbstractDAOFactory fabrica;

    private IUsuarioDAO usuariosDAO;
    private IPostDAO postsDAO;
    private IComentarioDAO comentariosDAO;

    public AccesoDatosFacade() {
        conexion = new Conexion();
        fabrica = new DAOFactory(conexion);

        usuariosDAO = fabrica.getUsuarioDAO();
        postsDAO = fabrica.getPostDAO();
        comentariosDAO = fabrica.getComentarioDAO();
    }

    @Override
    public void registrarUsuario(UsuarioNuevoDTO usuariodto) throws FacadeException {
        Normal usuario = new Normal(usuariodto.getNombres(),
                usuariodto.getApellidoPaterno(),
                usuariodto.getApellidoMaterno(),
                usuariodto.getCorreo(),
                AESEncriptador.encriptar(usuariodto.getContrasenia()),
                usuariodto.getTelefono(),
                usuariodto.getAvatar(),
                usuariodto.getCiudad(),
                usuariodto.getFechaNacimiento(),
                usuariodto.getGenero());

        try {
            usuariosDAO.registrarUsuario(usuario);
        } catch (PersistenciaException ex) {
            throw new FacadeException("No se pudo publicar el usuario.");
        }
    }

    @Override
    public void publicarPost(PostNuevoDTO post, UsuarioDTO usuariodto) throws FacadeException {
        CategoriaPost categoria;
        switch (post.getCategoria().toUpperCase()) {
            case "GENERAL":
                categoria = CategoriaPost.GENERAL;
                break;
            case "NOTICIAS":
                categoria = CategoriaPost.NOTICIAS;
                break;
            case "PLAYLIST":
                categoria = CategoriaPost.PLAYLIST;
                break;
            case "REVIEW":
                categoria = CategoriaPost.REVIEWS;
                break;
            default:
                categoria = CategoriaPost.GENERAL;
        }

        Comun nuevoPost = new Comun(post.getFechaHoraCreacion(),
                post.getTitulo(),
                post.getContenido(),
                categoria);
        Usuario usuario = null;

        if (usuariodto instanceof NormalDTO) {
            usuario = new Normal(usuariodto.getNombres(),
                    usuariodto.getApellidoPaterno(),
                    usuariodto.getApellidoMaterno(),
                    usuariodto.getCorreo(),
                    AESEncriptador.encriptar(usuariodto.getContrasenia()),
                    usuariodto.getTelefono(),
                    usuariodto.getAvatar(),
                    usuariodto.getCiudad(),
                    usuariodto.getFechaNacimiento(),
                    usuariodto.getGenero());
        } else if (usuariodto instanceof AdministradorDTO) {
            Usuario usuario1 = new Administrador(usuariodto.getNombres(),
                    usuariodto.getApellidoPaterno(),
                    usuariodto.getApellidoMaterno(),
                    usuariodto.getCorreo(),
                    AESEncriptador.encriptar(usuariodto.getContrasenia()),
                    usuariodto.getTelefono(),
                    usuariodto.getAvatar(),
                    usuariodto.getCiudad(),
                    usuariodto.getFechaNacimiento(),
                    usuariodto.getGenero());
        }

        nuevoPost.setUsuario(usuario);

        try {
            postsDAO.publicarPost(nuevoPost);
        } catch (PersistenciaException ex) {
            throw new FacadeException("No se pudo publicar el post.");
        }
    }

    @Override
    public void eliminarPost(PostNuevoDTO post, UsuarioDTO usuariodto) throws FacadeException {
        CategoriaPost categoria;
        switch (post.getCategoria().toUpperCase()) {
            case "GENERAL":
                categoria = CategoriaPost.GENERAL;
                break;
            case "NOTICIAS":
                categoria = CategoriaPost.NOTICIAS;
                break;
            case "PLAYLIST":
                categoria = CategoriaPost.PLAYLIST;
                break;
            case "REVIEW":
                categoria = CategoriaPost.REVIEWS;
                break;
            default:
                categoria = CategoriaPost.GENERAL;
        }

        Usuario usuario = null;

        if (usuariodto instanceof NormalDTO) {
            usuario = new Normal(usuariodto.getNombres(),
                    usuariodto.getApellidoPaterno(),
                    usuariodto.getApellidoMaterno(),
                    usuariodto.getCorreo(),
                    AESEncriptador.encriptar(usuariodto.getContrasenia()),
                    usuariodto.getTelefono(),
                    usuariodto.getAvatar(),
                    usuariodto.getCiudad(),
                    usuariodto.getFechaNacimiento(),
                    usuariodto.getGenero());
        } else if (usuariodto instanceof AdministradorDTO) {
            usuario = new Administrador(usuariodto.getNombres(),
                    usuariodto.getApellidoPaterno(),
                    usuariodto.getApellidoMaterno(),
                    usuariodto.getCorreo(),
                    AESEncriptador.encriptar(usuariodto.getContrasenia()),
                    usuariodto.getTelefono(),
                    usuariodto.getAvatar(),
                    usuariodto.getCiudad(),
                    usuariodto.getFechaNacimiento(),
                    usuariodto.getGenero());
        }

        // Crear una instancia de Post con los atributos que quieres buscar
        Post postParaEliminar = null;
        if (post instanceof ComunDTO) {
            Comun comun = new Comun(post.getFechaHoraCreacion(),
                    post.getTitulo(),
                    post.getContenido(),
                    categoria);
            comun.setUsuario(usuario);
            postParaEliminar = comun;
        } else if (post instanceof AncladoDTO) {
            Anclado anclado = new Anclado(post.getFechaHoraCreacion(),
                    post.getTitulo(),
                    post.getContenido(),
                    categoria);
            anclado.setAdministrador((Administrador) usuario);
            postParaEliminar = anclado;
        }

        try {
            Post postExistente = postsDAO.buscarPostPorAtributos(postParaEliminar);

            if (postExistente == null) {
                throw new FacadeException("El post no existe.");
            }

            if (postExistente instanceof Comun && !((Comun) postExistente).getUsuario().equals(usuario)) {
                throw new FacadeException("No se ha eliminado el post comun.");
            } else if (postExistente instanceof Anclado && !(postExistente instanceof Anclado)) {
                if (!((Anclado) postExistente).getAdministrador().equals(usuario)) {
                    throw new FacadeException("No se ha eliminado el post anclado");
                }
            }

            postsDAO.eliminarPost(postExistente);

        } catch (PersistenciaException ex) {
            throw new FacadeException("No se pudo eliminar el post.");
        }
    }

    @Override
    public void comentarPost(ComentarioDTO comentario, UsuarioDTO usuarioPost, PostNuevoDTO postComentado) throws FacadeException {
        CategoriaPost categoria;
        switch (postComentado.getCategoria().toUpperCase()) {
            case "GENERAL":
                categoria = CategoriaPost.GENERAL;
                break;
            case "NOTICIAS":
                categoria = CategoriaPost.NOTICIAS;
                break;
            case "PLAYLIST":
                categoria = CategoriaPost.PLAYLIST;
                break;
            case "REVIEW":
                categoria = CategoriaPost.REVIEWS;
                break;
            default:
                categoria = CategoriaPost.GENERAL;
        }

        Usuario usuario = null;

        if (usuarioPost instanceof NormalDTO) {
            usuario = new Normal(usuarioPost.getNombres(),
                    usuarioPost.getApellidoPaterno(),
                    usuarioPost.getApellidoMaterno(),
                    usuarioPost.getCorreo(),
                    AESEncriptador.encriptar(usuarioPost.getContrasenia()),
                    usuarioPost.getTelefono(),
                    usuarioPost.getAvatar(),
                    usuarioPost.getCiudad(),
                    usuarioPost.getFechaNacimiento(),
                    usuarioPost.getGenero());
        } else if (usuarioPost instanceof AdministradorDTO) {
            usuario = new Administrador(usuarioPost.getNombres(),
                    usuarioPost.getApellidoPaterno(),
                    usuarioPost.getApellidoMaterno(),
                    usuarioPost.getCorreo(),
                    AESEncriptador.encriptar(usuarioPost.getContrasenia()),
                    usuarioPost.getTelefono(),
                    usuarioPost.getAvatar(),
                    usuarioPost.getCiudad(),
                    usuarioPost.getFechaNacimiento(),
                    usuarioPost.getGenero());
        }

        Post postComentar = null;
        if (postComentado instanceof ComunDTO) {
            Comun comun = new Comun(postComentado.getFechaHoraCreacion(),
                    postComentado.getTitulo(),
                    postComentado.getContenido(),
                    categoria);
            comun.setUsuario(usuario);
            postComentar = comun;
        } else if (postComentado instanceof AncladoDTO) {
            Anclado anclado = new Anclado(postComentado.getFechaHoraCreacion(),
                    postComentado.getTitulo(),
                    postComentado.getContenido(),
                    categoria);
            anclado.setAdministrador((Administrador) usuario);
            postComentar = anclado;
        }

        Post postExistente =null;
        try {
             postExistente = postsDAO.buscarPostPorAtributos(postComentar);
        } catch (PersistenciaException ex) {
            Logger.getLogger(AccesoDatosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        Normal usuarioComentario = new Normal(
                comentario.getUsuario().getNombres(), 
                comentario.getUsuario().getApellidoPaterno(), 
                comentario.getUsuario().getApellidoMaterno(),
                comentario.getUsuario().getCorreo(),
                AESEncriptador.encriptar(comentario.getUsuario().getContrasenia()), 
                comentario.getUsuario().getTelefono(),
                comentario.getUsuario().getAvatar(),
                comentario.getUsuario().getCiudad(),
                comentario.getUsuario().getFechaNacimiento(), 
                comentario.getUsuario().getGenero());
        
        Comentario comentario1 = new Comentario(
                GregorianCalendar.getInstance(),
                comentario.getContenido(),
                (Comun)postExistente,
                usuarioComentario);
        try {
            comentariosDAO.publicarComentario(comentario1);
        } catch (PersistenciaException ex) {
            Logger.getLogger(AccesoDatosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarComentario(ComentarioDTO comentario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void responderComentario(ComentarioDTO respuesta, NormalDTO usuario, ComentarioDTO comentarioRespondido) {
       
    }

    @Override
    public UsuarioDTO obtenerUsuario(UsuarioDTO usuarioBuscado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
