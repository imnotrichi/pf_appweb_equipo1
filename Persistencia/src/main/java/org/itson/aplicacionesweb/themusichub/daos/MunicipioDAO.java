/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.itson.aplicacionesweb.themusichub.daos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.itson.aplicacionesweb.themusichub.conexion.IConexion;
import org.itson.aplicacionesweb.themusichub.modelo.Estado;
import org.itson.aplicacionesweb.themusichub.modelo.Municipio;
import org.itson.aplicacionesweb.themusichub.persistenciaException.PersistenciaException;

/**
 *
 * @author ricar
 */
public class MunicipioDAO implements IMunicipioDAO {

    private IConexion conexion;
    
    public MunicipioDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    
    @Override
    public List<Municipio> obtenerMunicipios() throws PersistenciaException {
        EntityManager entityManager = conexion.crearConexion();
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Municipio> criteria = builder.createQuery(Municipio.class);
            TypedQuery<Municipio> query = entityManager.createQuery(criteria);

            List<Municipio> municipios = query.getResultList();

            return municipios;
        } catch (Exception e) {
            throw new PersistenciaException("No se pudieron consultar los estados.");
        } finally {
            entityManager.close();
        }
    }
    
}
