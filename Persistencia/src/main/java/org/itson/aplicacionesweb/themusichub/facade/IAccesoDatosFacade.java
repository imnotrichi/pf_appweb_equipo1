/*
 * IAccesoDatosFacade.java
 */
package org.itson.aplicacionesweb.themusichub.facade;

import com.mycompany.dto.ComentarioDTO;
import com.mycompany.dto.NormalDTO;
import com.mycompany.dto.PostNuevoDTO;
import com.mycompany.dto.UsuarioDTO;
import com.mycompany.dto.UsuarioNuevoDTO;
import org.itson.aplicacionesweb.themusichub.persistenciaException.FacadeException;

/**
 * @author Equipo1
 */
public interface IAccesoDatosFacade {

    public void registrarUsuario(UsuarioNuevoDTO usuario) throws FacadeException;

    public void publicarPost(PostNuevoDTO post, UsuarioDTO usuario) throws FacadeException;

    public void eliminarPost(PostNuevoDTO post, UsuarioDTO usuario) throws FacadeException;

    public void comentarPost(ComentarioDTO comentario, UsuarioDTO usuarioPost, PostNuevoDTO postComentado) throws FacadeException;

    public void eliminarComentario(ComentarioDTO comentario);

    public void responderComentario(ComentarioDTO respuesta, NormalDTO usuario, ComentarioDTO comentarioRespondido);

    public UsuarioDTO obtenerUsuario(String correo, String contrasenia) throws FacadeException;
}
