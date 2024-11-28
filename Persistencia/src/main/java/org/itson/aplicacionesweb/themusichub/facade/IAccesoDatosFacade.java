/*
 * IAccesoDatosFacade.java
 */
package org.itson.aplicacionesweb.themusichub.facade;

import com.mycompany.dto.ComentarioDTO;
import com.mycompany.dto.PostDTO;
import com.mycompany.dto.UsuarioDTO;
import java.util.List;
import org.itson.aplicacionesweb.themusichub.enums.CategoriaPost;
import org.itson.aplicacionesweb.themusichub.persistenciaException.FacadeException;

/**
 * @author Equipo1
 */
public interface IAccesoDatosFacade {

    public void registrarUsuario(UsuarioDTO usuario) throws FacadeException;

    public void publicarPost(PostDTO post) throws FacadeException;

    public void eliminarPost(Long id, UsuarioDTO usuario) throws FacadeException;

    public void comentarPost(ComentarioDTO comentarioDTO, PostDTO postDTO) throws FacadeException;

    public void eliminarComentario(ComentarioDTO comentario, UsuarioDTO usuario) throws FacadeException;

    public void responderComentario(ComentarioDTO respuesta, ComentarioDTO comentario) throws FacadeException;

    public UsuarioDTO obtenerUsuario(String correo, String contrasenia) throws FacadeException;

    public UsuarioDTO obtenerUsuario(String correo) throws FacadeException;

    public List<PostDTO> obtenerPostsPorCategoria(CategoriaPost categoria) throws FacadeException;

    public List<PostDTO> obtenerPostsPorUsuario(String correo) throws FacadeException;

    public PostDTO obtenerPostID(Long id) throws FacadeException;

    public ComentarioDTO obtenerComentarioID(Long id) throws FacadeException;

    public void anclarPost(Long idPost, String correoAdmin) throws FacadeException;

    public void desanclarPost(Long idPost) throws FacadeException;
}
