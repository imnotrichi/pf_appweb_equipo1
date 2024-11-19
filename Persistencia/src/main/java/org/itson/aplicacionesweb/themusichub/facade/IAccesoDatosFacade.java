/*
 * IAccesoDatosFacade.java
 */
package org.itson.aplicacionesweb.themusichub.facade;

import com.mycompany.dto.ComentarioDTO;
import com.mycompany.dto.ComunDTO;
import com.mycompany.dto.NormalDTO;
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

    public void publicarPost(ComunDTO post) throws FacadeException;

    public void eliminarPost(PostDTO post) throws FacadeException;

    public void comentarPost(ComentarioDTO comentario, PostDTO postComentado) throws FacadeException;

    public void eliminarComentario(ComentarioDTO comentario);

    public void responderComentario(ComentarioDTO respuesta, NormalDTO usuario, ComentarioDTO comentarioRespondido);

    public UsuarioDTO obtenerUsuario(String correo, String contrasenia) throws FacadeException;
    
    public List<PostDTO> obtenerPostsPorCategoria(CategoriaPost categoria) throws FacadeException;
}
