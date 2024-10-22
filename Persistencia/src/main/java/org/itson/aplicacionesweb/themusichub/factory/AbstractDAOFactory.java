package org.itson.aplicacionesweb.themusichub.factory;

import org.itson.aplicacionesweb.themusichub.daos.IAdministradorDAO;
import org.itson.aplicacionesweb.themusichub.daos.IAncladoDAO;
import org.itson.aplicacionesweb.themusichub.daos.ICategoriaPostDAO;
import org.itson.aplicacionesweb.themusichub.daos.IComentarioDAO;
import org.itson.aplicacionesweb.themusichub.daos.IComunDAO;
import org.itson.aplicacionesweb.themusichub.daos.IEstadoDAO;
import org.itson.aplicacionesweb.themusichub.daos.IMunicipioDAO;
import org.itson.aplicacionesweb.themusichub.daos.INormalDAO;
import org.itson.aplicacionesweb.themusichub.daos.IPostDAO;
import org.itson.aplicacionesweb.themusichub.daos.IUsuarioDAO;

/**
 *
 * @author Diego Valenzuela Parra
 */
public abstract class AbstractDAOFactory {
    public abstract IAdministradorDAO getAdministradorDAO();

    public abstract IAncladoDAO getAncladoDAO();

    public abstract ICategoriaPostDAO getCategoriaPostDAO();

    public abstract IComentarioDAO getComentarioDAO();

    public abstract IComunDAO getComunDAO();

    public abstract IEstadoDAO getEstadoDAO();

    public abstract IMunicipioDAO getMunicipioDAO();

    public abstract INormalDAO getNormalDAO();

    public abstract IPostDAO getPostDAO();

    public abstract IUsuarioDAO getUsuarioDAO();
}
