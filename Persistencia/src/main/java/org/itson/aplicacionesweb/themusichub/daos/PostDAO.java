/**
 * PostDAO.java
 */
package org.itson.aplicacionesweb.themusichub.daos;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.itson.aplicacionesweb.themusichub.conexion.IConexion;
import org.itson.aplicacionesweb.themusichub.modelo.Post;

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
    public Post obtenerPostPorID(Long id) {
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
    public List<Post> obtenerTodosPosts() {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

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
        logger.log(Level.INFO, "Se ha consultado la tabla 'tarifas_licencia' y se obtuvieron " + i + " resultados.");

        // Cerramos el entity manager.
        em.close();

        // Retornamos la lista de posts.
        return listaPosts;
    }

    /**
     * Método para insertar un post en la base de datos.
     *
     * @param post Post a insertar.
     */
    @Override
    public void publicarPost(Post post) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        // Iniciamos la transacción.
        em.getTransaction().begin();

        // Mandamos a persistir el post.
        em.persist(post);

        // Hacemos el commit y cerramos el entity manager.
        em.getTransaction().commit();
        em.close();

        // Imprimimos un mensaje de que se registró un post.
        logger.log(Level.INFO, "Se ha insertado 1 post correctamente.");
    }

    /**
     * Método para actualizar un post.
     *
     * @param post Post a actualizar.
     */
    @Override
    public void editarPost(Post post) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        // Iniciamos la transacción.
        em.getTransaction().begin();

        // Mandamos a actualizar el post.
        em.merge(post);

        // Hacemos el commit y cerramos el entity manager.
        em.getTransaction().commit();
        em.close();

        // Imprimimos un mensaje de que se actualizó una post.
        logger.log(Level.INFO, "Se ha actualizado 1 post correctamente.");
    }

    /**
     * Método para borrar un post de la base de datos.
     *
     * @param post Post a eliminar.
     */
    @Override
    public void eliminarPost(Post post) {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

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
    }

}
