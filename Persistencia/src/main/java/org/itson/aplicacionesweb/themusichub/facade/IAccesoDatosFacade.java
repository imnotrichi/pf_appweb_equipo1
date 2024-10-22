/*
 * IAccesoDatosFacade.java
 */
package org.itson.aplicacionesweb.themusichub.facade;

import com.mycompany.dto.ComentarioDTO;
import com.mycompany.dto.PostDTO;
import com.mycompany.dto.UsuarioDTO;
import org.itson.aplicacionesweb.themusichub.persistenciaException.FacadeException;

/**
 * @author Equipo1
 */
public interface IAccesoDatosFacade {
    
    public void registrarUsuario(UsuarioDTO usuario);
    
    public void publicarPost(PostDTO post) throws FacadeException;
    
    public void eliminarPost(PostDTO post);
    
    public void comentarPost(ComentarioDTO comentario);
    
    public void eliminarComentario(ComentarioDTO comentario);
    
    public void responderComentario(ComentarioDTO respuesta);
    
}
