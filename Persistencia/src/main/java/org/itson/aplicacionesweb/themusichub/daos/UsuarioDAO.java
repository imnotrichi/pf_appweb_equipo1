package org.itson.aplicacionesweb.themusichub.daos;

import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import org.itson.aplicacionesweb.themusichub.conexion.IConexion;
import org.itson.aplicacionesweb.themusichub.modelo.Estado;
import org.itson.aplicacionesweb.themusichub.modelo.Municipio;
import org.itson.aplicacionesweb.themusichub.modelo.Usuario;
import org.itson.aplicacionesweb.themusichub.persistenciaException.PersistenciaException;

/**
 *
 * @author Diego Valenzuela Parra
 */
public class UsuarioDAO implements IUsuarioDAO {

    private IConexion conexion;
    private static final Logger logger = Logger.getLogger(UsuarioDAO.class.getName());

    public UsuarioDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public Usuario registrarUsuario(Usuario usuario) throws PersistenciaException {
        EntityManager em = null;
        try {
            em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
            return usuario;

        } catch (PersistenceException e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "Error al registrar el usuario", e);
            throw new PersistenciaException("No se pudo registrar el usuario", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Usuario obtenerUsuarioCorreoContra(String correo, String contrasenia) throws PersistenciaException {
        EntityManager em = null;
        try {
            em = this.conexion.crearConexion();

            String jpqlQuery = """
            SELECT u FROM Usuario u 
            WHERE u.correo = :correo 
            AND u.contrasenia = :contrasenia
            """;

            TypedQuery<Usuario> query = em.createQuery(jpqlQuery, Usuario.class);
            query.setParameter("correo", correo);
            query.setParameter("contrasenia", contrasenia);

            List<Usuario> resultados = query.getResultList();
            if (resultados.isEmpty()) {
                return null;
            } else {
                return resultados.get(0);
            }

        } catch (PersistenceException e) {
            logger.log(Level.SEVERE, "No se pudo iniciar sesión", e);
            throw new PersistenciaException("No se pudo consultar la información", e);
        } finally {
            em.close();
        }

    }

    @Override
    public Usuario buscarUsuario(String correo) throws PersistenciaException {
        EntityManager em = null;
        try {
            em = this.conexion.crearConexion();

            String jpqlQuery = """
            SELECT u FROM Usuario u 
            WHERE u.correo = :correo 
            """;

            TypedQuery<Usuario> query = em.createQuery(jpqlQuery, Usuario.class);
            query.setParameter("correo", correo);

            List<Usuario> resultados = query.getResultList();
            if (resultados.isEmpty()) {
                return null;
            } else {
                return resultados.get(0);
            }

        } catch (PersistenceException e) {
            logger.log(Level.SEVERE, "No se pudo iniciar sesión", e);
            throw new PersistenciaException("No se pudo consultar la información", e);
        } finally {
            em.close();
        }
    }
}
