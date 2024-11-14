package org.itson.aplicacionesweb.themusichub.daos;

import java.util.List;
import org.itson.aplicacionesweb.themusichub.modelo.Comentario;
import org.itson.aplicacionesweb.themusichub.persistenciaException.PersistenciaException;

/**
 *
 * @author Equipo1
 */
public interface IComentarioDAO {
    
    public Comentario obtenerComentario(Long id) throws PersistenciaException;
    public List<Comentario> obtenerTodosComentarios() throws PersistenciaException;
    public void publicarComentario(Comentario comentario) throws PersistenciaException;
    public void eliminarComentario(Comentario comentario) throws PersistenciaException;
}
