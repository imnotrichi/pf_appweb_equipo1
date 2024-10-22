package org.itson.aplicacionesweb.themusichub.daos;

import org.itson.aplicacionesweb.themusichub.conexion.IConexion;

/**
 *
 * @author Diego Valenzuela Parra
 */
public class ComentarioDAO implements IComentarioDAO {

    private IConexion conexion;

    public ComentarioDAO(IConexion conexion) {
        this.conexion = conexion;
    }

}
