/*
 * IAccesoDatosFacade.java
 */
package org.itson.aplicacionesweb.themusichub.facade;

/**
 * @author Equipo1
 */
public interface IAccesoDatosFacade {
    
    public void registrarUsuario(UsuarioDTO usuario);
    
    public void publicarPost(PostDTO post);
    
    public void eliminarPost(PostDTO post);
    
    public void comentarPost(ComentarioDTO comentario);
    
    public void eliminarComentario(ComentarioDTO comentario);
    
    public void responderComentario(ComentarioDTO respuesta);
    
}
