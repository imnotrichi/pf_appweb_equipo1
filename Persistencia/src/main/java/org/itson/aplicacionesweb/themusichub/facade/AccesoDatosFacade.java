/*
 * AccesoDatosFacade.java
 */
package org.itson.aplicacionesweb.themusichub.facade;

import com.mycompany.dto.ComentarioDTO;
import com.mycompany.dto.NormalDTO;
import com.mycompany.dto.PostNuevoDTO;
import com.mycompany.dto.UsuarioNuevoDTO;
import org.itson.aplicacionesweb.themusichub.conexion.Conexion;
import org.itson.aplicacionesweb.themusichub.conexion.IConexion;
import org.itson.aplicacionesweb.themusichub.daos.IComentarioDAO;
import org.itson.aplicacionesweb.themusichub.daos.IPostDAO;
import org.itson.aplicacionesweb.themusichub.daos.IUsuarioDAO;
import org.itson.aplicacionesweb.themusichub.factory.AbstractDAOFactory;
import org.itson.aplicacionesweb.themusichub.factory.DAOFactory;
import org.itson.aplicacionesweb.themusichub.modelo.CategoriaPost;
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
    public void registrarUsuario(UsuarioNuevoDTO usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void publicarPost(PostNuevoDTO post, UsuarioNuevoDTO usuario) throws FacadeException {
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
        
        Usuario usuarioPost = 
        
        Post postNuevo = new Comun(post.getFechaHoraCreacion(), post.getTitulo(), post.getContenido(), categoria);

        try {
            postsDAO.publicarPost(postNuevo);
        } catch (PersistenciaException ex) {
            throw new FacadeException("No se pudo publicar el post.");
        }
    }

    @Override
    public void eliminarPost(PostNuevoDTO post) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void comentarPost(ComentarioDTO comentario, NormalDTO usuario, PostNuevoDTO postComentado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarComentario(ComentarioDTO comentario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void responderComentario(ComentarioDTO respuesta, NormalDTO usuario, ComentarioDTO comentarioRespondido) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
