package org.itson.aplicacionesweb.themusichub.daos;

import java.nio.charset.StandardCharsets;
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
            
            Municipio municipio = usuario.getMunicipio();
            if (municipio != null && municipio.getId() == null) {
                Estado estado = municipio.getEstado();
                if (estado != null && estado.getId() == null) {
                    TypedQuery<Estado> queryEstado = em.createQuery(
                        "SELECT e FROM Estado e WHERE e.nombre = :nombre", 
                        Estado.class
                    );
                    queryEstado.setParameter("nombre", estado.getNombre());
                    
                    Estado estadoExistente = queryEstado.getResultStream()
                        .findFirst()
                        .orElse(null);
                    
                    if (estadoExistente == null) {
                        em.persist(estado);
                    } else {
                        municipio.setEstado(estadoExistente);
                    }
                }
                
                TypedQuery<Municipio> queryMunicipio = em.createQuery(
                    "SELECT m FROM Municipio m WHERE m.nombre = :nombre AND m.estado.id = :estadoId", 
                    Municipio.class
                );
                queryMunicipio.setParameter("nombre", municipio.getNombre());
                queryMunicipio.setParameter("estadoId", municipio.getEstado().getId());
                
                Municipio municipioExistente = queryMunicipio.getResultStream()
                    .findFirst()
                    .orElse(null);
                
                if (municipioExistente == null) {
                    em.persist(municipio);
                } else {
                    usuario.setMunicipio(municipioExistente);
                }
            }
            
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
    public Usuario iniciarSesion(String contrasena, String correo) throws PersistenciaException {
        EntityManager em = this.conexion.crearConexion();

        String jpqlQuery = """
                           SELECT u FROM Usuario u 
                           WHERE u.correo = :correo 
                           AND u.contrasenia = :contrasenia
                           """;
        try {
            byte[] contrasenaBytes = contrasena.getBytes();
            Usuario usuario = em.createQuery(jpqlQuery, Usuario.class)
                            .setParameter("correo", correo)
                            .setParameter("contrasenia", contrasenaBytes) 
                            .getSingleResult();

        return usuario;
         

        } catch (PersistenceException e) {
            logger.log(Level.SEVERE, "No se pudo inicir sesion", e);
            throw new PersistenciaException("No se pudo consultar la información", e);
        } finally {
            em.close();
        }
    }

}
