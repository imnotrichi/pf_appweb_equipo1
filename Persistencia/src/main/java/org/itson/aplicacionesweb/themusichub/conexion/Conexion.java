package org.itson.aplicacionesweb.themusichub.conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Diego Valenzuela Parra
 */
public class Conexion implements IConexion {

    @Override
    public EntityManager crearConexion() {
        // Creamos el EntityManagerFactory.
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TheMusicHubPU");
        
        // Creamos el EntityManager.
        EntityManager em = emf.createEntityManager();

        // Retornamos el EntityManager.
        return em;
    }
}
