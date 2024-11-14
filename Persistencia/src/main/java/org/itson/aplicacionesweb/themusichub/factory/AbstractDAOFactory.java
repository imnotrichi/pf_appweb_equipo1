package org.itson.aplicacionesweb.themusichub.factory;

import org.itson.aplicacionesweb.themusichub.daos.IComentarioDAO;
import org.itson.aplicacionesweb.themusichub.daos.IEstadoDAO;
import org.itson.aplicacionesweb.themusichub.daos.IMunicipioDAO;
import org.itson.aplicacionesweb.themusichub.daos.IPostDAO;
import org.itson.aplicacionesweb.themusichub.daos.IUsuarioDAO;

/**
 *
 * @author Diego Valenzuela Parra
 */
public abstract class AbstractDAOFactory {

    public abstract IComentarioDAO getComentarioDAO();

    public abstract IPostDAO getPostDAO();

    public abstract IUsuarioDAO getUsuarioDAO();
    
    public abstract IMunicipioDAO getMunicipioDAO();
    
    public abstract IEstadoDAO getEstadoDAO();
}
