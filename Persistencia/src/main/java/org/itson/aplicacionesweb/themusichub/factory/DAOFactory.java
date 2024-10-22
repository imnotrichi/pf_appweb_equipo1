package org.itson.aplicacionesweb.themusichub.factory;

import org.itson.aplicacionesweb.themusichub.daos.AdministradorDAO;
import org.itson.aplicacionesweb.themusichub.daos.AncladoDAO;
import org.itson.aplicacionesweb.themusichub.daos.CategoriaPostDAO;
import org.itson.aplicacionesweb.themusichub.daos.ComentarioDAO;
import org.itson.aplicacionesweb.themusichub.daos.ComunDAO;
import org.itson.aplicacionesweb.themusichub.daos.EstadoDAO;
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
import org.itson.aplicacionesweb.themusichub.daos.MunicipioDAO;
import org.itson.aplicacionesweb.themusichub.daos.NormalDAO;
import org.itson.aplicacionesweb.themusichub.daos.PostDAO;
import org.itson.aplicacionesweb.themusichub.daos.UsuarioDAO;

/**
 *
 * @author Diego Valenzuela Parra
 */
public class DAOFactory extends AbstractDAOFactory {
    
    /**
     *
     * @return
     */
    @Override
    public IAdministradorDAO getAdministradorDAO() {
        return new AdministradorDAO();
    }

    /**
     *
     * @return
     */
    @Override
    public IAncladoDAO getAncladoDAO() {
        return new AncladoDAO();
    }

    /**
     *
     * @return
     */
    @Override
    public ICategoriaPostDAO getCategoriaPostDAO() {
        return new CategoriaPostDAO();
    }

    /**
     *
     * @return
     */
    @Override
    public IComentarioDAO getComentarioDAO() {
        return new ComentarioDAO();
    }

    /**
     *
     * @return
     */
    @Override
    public IComunDAO getComunDAO() {
        return new ComunDAO();
    }

    /**
     *
     * @return
     */
    @Override
    public IEstadoDAO getEstadoDAO() {
        return new EstadoDAO();
    }

    /**
     *
     * @return
     */
    @Override
    public IMunicipioDAO getMunicipioDAO() {
        return new MunicipioDAO();
    }

    /**
     *
     * @return
     */
    @Override
    public INormalDAO getNormalDAO() {
        return new NormalDAO();
    }

    /**
     *
     * @return
     */
    @Override
    public IPostDAO getPostDAO() {
        return new PostDAO();
    }

    /**
     *
     * @return
     */
    @Override
    public IUsuarioDAO getUsuarioDAO() {
        return new UsuarioDAO();
    }
}
