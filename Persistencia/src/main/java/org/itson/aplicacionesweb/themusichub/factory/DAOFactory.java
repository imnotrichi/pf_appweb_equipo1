package org.itson.aplicacionesweb.themusichub.factory;

import org.itson.aplicacionesweb.themusichub.conexion.IConexion;
import org.itson.aplicacionesweb.themusichub.daos.ComentarioDAO;
import org.itson.aplicacionesweb.themusichub.daos.EstadoDAO;
import org.itson.aplicacionesweb.themusichub.daos.IComentarioDAO;
import org.itson.aplicacionesweb.themusichub.daos.IEstadoDAO;
import org.itson.aplicacionesweb.themusichub.daos.IMunicipioDAO;
import org.itson.aplicacionesweb.themusichub.daos.IPostDAO;
import org.itson.aplicacionesweb.themusichub.daos.IUsuarioDAO;
import org.itson.aplicacionesweb.themusichub.daos.MunicipioDAO;
import org.itson.aplicacionesweb.themusichub.daos.PostDAO;
import org.itson.aplicacionesweb.themusichub.daos.UsuarioDAO;

/**
 *
 * @author Diego Valenzuela Parra
 */
public class DAOFactory extends AbstractDAOFactory {
    
    private IConexion conexion;
    
    public DAOFactory(IConexion conexion) {
        this.conexion = conexion;
    }

    /**
     *
     * @return
     */
    @Override
    public IComentarioDAO getComentarioDAO() {
        return new ComentarioDAO(conexion);
    }

    /**
     *
     * @return
     */
    @Override
    public IEstadoDAO getEstadoDAO() {
        return new EstadoDAO(conexion);
    }

    /**
     *
     * @return
     */
    @Override
    public IMunicipioDAO getMunicipioDAO() {
        return new MunicipioDAO(conexion);
    }

    /**
     *
     * @return
     */
    @Override
    public IPostDAO getPostDAO() {
        return new PostDAO(conexion);
    }

    /**
     *
     * @return
     */
    @Override
    public IUsuarioDAO getUsuarioDAO() {
        return new UsuarioDAO(conexion);
    }
}
