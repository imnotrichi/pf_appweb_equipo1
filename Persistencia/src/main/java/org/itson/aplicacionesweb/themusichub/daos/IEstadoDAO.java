/*
 * IEstadoDAO.java
 */
package org.itson.aplicacionesweb.themusichub.daos;

import java.util.List;
import org.itson.aplicacionesweb.themusichub.modelo.Estado;
import org.itson.aplicacionesweb.themusichub.persistenciaException.PersistenciaException;

/**
 * @author Equipo1
 */
public interface IEstadoDAO {
    
    public List<Estado> obtenerEstados() throws PersistenciaException;
}
