/*
 * IConexion.java
 */
package org.itson.aplicacionesweb.themusichub.conexion;

import javax.persistence.EntityManager;

/**
 * Interfaz con los métodos necesarios para crear conexiones con la base de
 * datos.
 *
 * @author Diego Valenzuela Parra
 */
public interface IConexion {

    /**
     * Método para crear una conexión con la base de datos.
     *
     * @return Un objeto del tipo EntityManager.
     */
    public EntityManager crearConexion();
}
