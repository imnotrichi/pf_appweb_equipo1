/*
 * EstadoDAO.java
 */
package org.itson.aplicacionesweb.themusichub.daos;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.itson.aplicacionesweb.themusichub.conexion.IConexion;
import org.itson.aplicacionesweb.themusichub.modelo.Estado;
import org.itson.aplicacionesweb.themusichub.persistenciaException.PersistenciaException;

/**
 * @author Equipo1
 */
public class EstadoDAO implements IEstadoDAO {
    
    private IConexion conexion;
    
    public EstadoDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<Estado> obtenerEstados() throws PersistenciaException {
        EntityManager entityManager = conexion.crearConexion();
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Estado> criteria = builder.createQuery(Estado.class);
            TypedQuery<Estado> query = entityManager.createQuery(criteria);

            List<Estado> estados = query.getResultList();

            return estados;
        } catch (Exception e) {
            throw new PersistenciaException("No se pudieron consultar los estados.");
        } finally {
            entityManager.close();
        }
    }
    
}
