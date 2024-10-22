package org.itson.aplicacionesweb.themusichub.daos;

import com.mycompany.dto.UsuarioDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import org.itson.aplicacionesweb.themusichub.conexion.IConexion;
import org.itson.aplicacionesweb.themusichub.modelo.Normal;
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
    public Usuario registrarUsuario(Usuario usuario) throws PersistenciaException{

        EntityManager em = null;
        try {
            em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
            return usuario; 
        } catch (PersistenceException e) {
            
            logger.log(Level.SEVERE, "Error al registrar el usuario", e);
            throw new PersistenciaException("No se pudo registrar el usuario", e);
        } finally {
            if (em != null) {
                em.close(); // Cerrar EntityManager
            }
        }
    }

    @Override
    public Usuario iniciarSesion(String contrasena, String correo) throws PersistenciaException {
        EntityManager em = this.conexion.crearConexion();

        String jpqlQuery = """
                           SELECT * FROM usuarios u 
                           WHERE u.correo = :correo
                           AND u.contrasena = :contrasena;
                           """;
        try {
            TypedQuery<Usuario> query = em.createQuery(jpqlQuery, Usuario.class);
            query.setParameter("correo", correo);
            query.setParameter("contrasena", contrasena);
            return query.getResultStream().findFirst().orElse(null);

        } catch (PersistenceException e) {
            logger.log(Level.SEVERE, "No se pudo inicir sesion", e);
            throw new PersistenciaException("No se pudo consultar la información", e);
        } finally {
            em.close();
        }
    }

}
