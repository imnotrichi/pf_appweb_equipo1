/**
 * PostDAO.java
 */
package org.itson.aplicacionesweb.themusichub.daos;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.itson.aplicacionesweb.themusichub.conexion.IConexion;
import org.itson.aplicacionesweb.themusichub.modelo.Anclado;
import org.itson.aplicacionesweb.themusichub.enums.CategoriaPost;
import org.itson.aplicacionesweb.themusichub.modelo.Comun;
import org.itson.aplicacionesweb.themusichub.modelo.Post;
import org.itson.aplicacionesweb.themusichub.persistenciaException.PersistenciaException;

/**
 * Clase que implementa la interfaz IPostDAO y define los métodos para realizar
 * operaciones relacionadas con la entidad Post en la base de datos.
 *
 * @author Diego Valenzuela Parra
 */
public class PostDAO implements IPostDAO {

    private IConexion conexion;
    private static final Logger logger = Logger.getLogger(PostDAO.class.getName());

    /**
     * Constructor de la clase. Recibe una conexión para poder interactuar con
     * la base de datos.
     *
     * @param conexion Conexión mediante la cual se podrá interactuar con la
     * base de datos.
     */
    public PostDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Método para obtener un post dado su id.
     *
     * @param id Número identificador del post.
     * @return El post si se encuentra, null si no se encuentra.
     */
    @Override
    public Post obtenerPostPorID(Long id) throws PersistenciaException {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        try {
            // Construimos una instancia de CriteriaBuilder.
            CriteriaBuilder cb = em.getCriteriaBuilder();
            // Creamos un objeto CriteriaQuery para indicar el resultado de la consulta.
            CriteriaQuery<Post> cq = cb.createQuery(Post.class);
            // Creamos una instancia instancia del tipo Root para indicar de qué entidad
            // se hará la consulta.
            Root<Post> root = cq.from(Post.class);

            // Con esta línea especificamos que la consulta seleccionará todos los
            // campos de Post donde el id coincida con el proporcionado.
            cq.select(root).where(cb.equal(root.get("id"), id));

            // Se manda a ejecutar la consulta y guardamos el resultado.
            Post post = em.createQuery(cq).getSingleResult();

            // Imprimimos un mensaje de que se obtuvo 1 resultado.
            logger.log(Level.INFO, "Se ha consultado la tabla 'posts' y se obtuvo 1 resultado.");

            // Retornamos el post encontrado.
            return post;
        } catch (NoResultException nre) {
            // Imprimimos un mensaje de que no se obtuvo nada.
            logger.log(Level.INFO, "Se ha consultado la tabla 'posts' y no se obtuvieron resultados.");

            return null;
        } catch (PersistenceException pe) {
            throw new PersistenciaException("No se pudo consultar obtener el post.");
        } finally {
            // Cerramos el entity manager.
            em.close();
        }
    }

    /**
     * Método que obtiene una lista con todas los posts.
     *
     * @return Lista con todos los posts.
     */
    @Override
    public List<Post> obtenerTodosPosts() throws PersistenciaException {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        try {
            // Construimos una instancia de CriteriaBuilder.
            CriteriaBuilder cb = em.getCriteriaBuilder();
            // Creamos un objeto CriteriaQuery para indicar el resultado de la consulta.
            CriteriaQuery<Post> cq = cb.createQuery(Post.class);
            // Creamos una instancia del tipo Root para indicar de qué entidad
            // se hará la consulta.
            Root<Post> root = cq.from(Post.class);

            // Con esta línea especificamos que la consulta seleccionará todos los
            // campos de Post.
            cq.select(root);

            // Obtenemos la lista de posts.
            List<Post> listaPosts = em.createQuery(cq).getResultList();

            // Obtenemos la cantidad de resultados.
            int i = 0;
            for (Post post : listaPosts) {
                i++;
            }

            // Imprimimos un mensaje de que se ejecutó una consulta.
            logger.log(Level.INFO, "Se ha consultado la tabla 'posts' y se obtuvieron " + i + " resultados.");
            // Retornamos la lista de posts.
            return listaPosts;
        } catch (PersistenceException pe) {
            logger.log(Level.WARNING, pe.getMessage());
            throw new PersistenciaException("No se pudieron consultar todos los posts.");
        } finally {
            // Cerramos el entity manager.
            em.close();
        }

    }

    /**
     * Método para obtener todos los posts de una categoría en específico o
     * todos los posts si así se desea.
     *
     * @param categoria Categoría de la que se quieren buscar los posts.
     * @return La lista con todos los posts de la categoría buscada.
     * @throws PersistenciaException Si ocurre un error al consultar los posts.
     */
    @Override
    public List<Post> obtenerPostsPorCategoria(CategoriaPost categoria) throws PersistenciaException {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        try {
            // Construimos una instancia de CriteriaBuilder.
            CriteriaBuilder cb = em.getCriteriaBuilder();
            // Creamos un objeto CriteriaQuery para indicar el resultado de la consulta.
            CriteriaQuery<Post> cq = cb.createQuery(Post.class);
            // Creamos una instancia del tipo Root para indicar de qué entidad
            // se hará la consulta.
            Root<Post> root = cq.from(Post.class);

            if (categoria != null) {
                // Con esta línea especificamos que la consulta seleccionará
                // todos los posts de la categoría especificada.
                cq.where(cb.equal(root.get("categoria"), categoria));
            } else {
                // Si no se especifica una categoría, se obtendrán todos los posts.
                return obtenerTodosPosts();
            }

            // Obtenemos la lista de posts.
            List<Post> listaPosts = em.createQuery(cq).getResultList();

            // Obtenemos la cantidad de resultados.
            int i = 0;
            for (Post post : listaPosts) {
                i++;
                if (post instanceof Comun) {
                    post = obtenerPostPorID(post.getId());
                }
            }

            // Imprimimos un mensaje de que se ejecutó una consulta.
            logger.log(Level.INFO, "Se ha consultado la tabla 'posts' y se obtuvieron " + i + " resultados.");
            // Retornamos la lista de posts.
            return listaPosts;
        } catch (PersistenceException pe) {
            throw new PersistenciaException("No se pudieron consultar los posts.");
        } finally {
            // Cerramos el entity manager.
            em.close();
        }
    }

    /**
     * Método para insertar un post en la base de datos.
     *
     * @param post Post a insertar.
     */
    @Override
    public void publicarPost(Post post) throws PersistenciaException {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        try {
            // Iniciamos la transacción.
            em.getTransaction().begin();

            // Mandamos a persistir el post.
            em.persist(post);

            // Hacemos el commit y cerramos el entity manager.
            em.getTransaction().commit();
            em.close();

            // Imprimimos un mensaje de que se registró un post.
            logger.log(Level.INFO, "Se ha insertado 1 post correctamente.");
        } catch (PersistenceException pe) {
            throw new PersistenciaException("No se pudo insertar el post.");
        }
    }

    /**
     * Método para actualizar un post.
     *
     * @param post Post a actualizar.
     */
    @Override
    public void editarPost(Post post) throws PersistenciaException {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        try {
            // Iniciamos la transacción.
            em.getTransaction().begin();

            // Mandamos a actualizar el post.
            em.merge(post);

            // Hacemos el commit y cerramos el entity manager.
            em.getTransaction().commit();
            em.close();

            // Imprimimos un mensaje de que se actualizó una post.
            logger.log(Level.INFO, "Se ha actualizado 1 post correctamente.");
        } catch (PersistenceException pe) {
            throw new PersistenciaException("No se pudo editar el post.");
        }
    }

    /**
     * Método para borrar un post de la base de datos.
     *
     * @param post Post a eliminar.
     */
    @Override
    public void eliminarPost(Post post) throws PersistenciaException {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        try {
            // Iniciamos la transacción.
            em.getTransaction().begin();

            post = em.merge(post);

            // Mandamos a eliminar el post.
            em.remove(post);

            // Hacemos el commit y cerramos el entity manager.
            em.getTransaction().commit();
            em.close();

            // Imprimimos un mensaje de que se eliminó un post.
            logger.log(Level.INFO, "Se ha eliminado 1 post correctamente.");
        } catch (PersistenceException pe) {
            throw new PersistenciaException("No se pudo eliminar el post.");
        }
    }

    /**
     * Obtiene un post que cumpla con todos los atributos
     *
     * @param postParaBuscar
     * @return
     * @throws PersistenciaException
     */
    @Override
    public Post buscarPostPorAtributos(Post postParaBuscar) throws PersistenciaException {
        EntityManager em = conexion.crearConexion();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Post> cq = cb.createQuery(Post.class);

            Root<? extends Post> root;
            if (postParaBuscar instanceof Comun) {
                root = cq.from(Comun.class);
            } else if (postParaBuscar instanceof Anclado) {
                root = cq.from(Anclado.class);
            } else {
                root = cq.from(Post.class);
            }

            cq.select(root);

            if (postParaBuscar.getTitulo() != null) {
                cq.where(cb.equal(root.get("titulo"), postParaBuscar.getTitulo()));
            }
            if (postParaBuscar.getContenido() != null) {
                cq.where(cb.equal(root.get("contenido"), postParaBuscar.getContenido()));
            }
            if (postParaBuscar.getCategoria() != null) {
                cq.where(cb.equal(root.get("categoria"), postParaBuscar.getCategoria()));
            }

            if (postParaBuscar instanceof Comun) {
                Comun comun = (Comun) postParaBuscar;
                if (comun.getUsuario() != null) {
                    cq.where(cb.equal(root.get("usuario"), comun.getUsuario()));
                }
            }
//
//            if (postParaBuscar instanceof Anclado) {
//                Anclado anclado = (Anclado) postParaBuscar;
//                if (anclado.getAdministrador() != null) {
//                    cq.where(cb.equal(root.get("administrador"), anclado.getAdministrador()));
//                }
//            }

            TypedQuery<Post> query = em.createQuery(cq);
            List<Post> resultados = query.getResultList();

            return resultados.isEmpty() ? null : resultados.get(0);
        } catch (PersistenceException pe) {
            throw new PersistenciaException("Error al buscar el post.", pe);
        } finally {
            em.close();
        }
    }

}
