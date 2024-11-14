package org.itson.aplicacionesweb.themusichub.daos;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.itson.aplicacionesweb.themusichub.conexion.IConexion;
import org.itson.aplicacionesweb.themusichub.modelo.Comentario;
import org.itson.aplicacionesweb.themusichub.persistenciaException.PersistenciaException;

/**
 *
 * @author Equipo1
 */
public class ComentarioDAO implements IComentarioDAO {
    
    private IConexion conexion;
    private static final Logger logger = Logger.getLogger(ComentarioDAO.class.getName());

    public ComentarioDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public Comentario obtenerComentario(Long id) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();
        
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Comentario> cq = cb.createQuery(Comentario.class);
            Root<Comentario> root = cq.from(Comentario.class);
            cq.select(root).where(cb.equal(root.get("id"), id));
            Comentario comentario = em.createQuery(cq).getSingleResult();
            logger.log(Level.INFO, "Se ha consultado la tabla 'comentarios' y se obtuvo 1 resultado.");
            return comentario;
        } catch (NoResultException nre){
            logger.log(Level.INFO, "La tabla 'comentarios' está vacía.");
            return null;
        } catch (PersistenceException pe) {
            throw new PersistenciaException("No se pudo realizar la consulta.");
        } finally {
            em.close();
        }
    }

    @Override
    public List<Comentario> obtenerTodosComentarios() throws PersistenciaException {
        EntityManager em = conexion.crearConexion();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Comentario> cq = cb.createQuery(Comentario.class);
            Root<Comentario> root = cq.from(Comentario.class);
            cq.select(root);
            List<Comentario> listaComentarios = em.createQuery(cq).getResultList();

            int i = 0;
            for (Comentario comentario : listaComentarios) {
                i++;
            }

            logger.log(Level.INFO, "Se ha consultado la tabla 'comentarios' y se obtuvieron " + i + " resultados.");
            return listaComentarios;
        } catch (NoResultException nre){
            logger.log(Level.INFO, "La tabla 'comentarios' está vacía.");
            return null;
        } catch (PersistenceException pe) {
            throw new PersistenciaException("No se pudo realizar la consulta.");
        } finally {
            em.close();
        }
    }

    @Override
    public void publicarComentario(Comentario comentario) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();

        try {
            em.getTransaction().begin();
            em.persist(comentario);
            em.getTransaction().commit();
            em.close();
            logger.log(Level.INFO, "Se ha insertado 1 comentario correctamente.");
        } catch (PersistenceException pe) {
            throw new PersistenciaException("No se pudo insertar el comentario.",pe);
        }
    }

    @Override
    public void eliminarComentario(Comentario comentario) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();

        try {
            em.getTransaction().begin();
            comentario = em.merge(comentario);
            em.remove(comentario);
            em.getTransaction().commit();
            em.close();
            logger.log(Level.INFO, "Se ha eliminado 1 comentario correctamente.");
        } catch (PersistenceException pe) {
            throw new PersistenciaException("No se pudo eliminar el comentario.");
        }
    }
}
