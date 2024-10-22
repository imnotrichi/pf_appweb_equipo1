/*
 * IMunicipioDAO.java
 */
package org.itson.aplicacionesweb.themusichub.daos;

import java.util.List;
import org.itson.aplicacionesweb.themusichub.modelo.Municipio;
import org.itson.aplicacionesweb.themusichub.persistenciaException.PersistenciaException;

/**
 * @author Equipo1
 */
public interface IMunicipioDAO {

    public List<Municipio> obtenerMunicipios() throws PersistenciaException;

}
