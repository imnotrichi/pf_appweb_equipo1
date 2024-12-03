/**
 * PostDAO.java
 */
package org.itson.aplicacionesweb.themusichub.daos;

import java.util.ArrayList;
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
import org.itson.aplicacionesweb.themusichub.enums.CategoriaPost;
import org.itson.aplicacionesweb.themusichub.modelo.Anclado;
import org.itson.aplicacionesweb.themusichub.modelo.Comentario;
import org.itson.aplicacionesweb.themusichub.modelo.Comun;
import org.itson.aplicacionesweb.themusichub.modelo.Post;
import org.itson.aplicacionesweb.themusichub.modelo.Usuario;
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
            
            em.refresh(post);

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

            // Ordenamos la lista: primero los anclados y luego los comunes.
            List<Post> postsAnclados = new ArrayList<>();
            List<Post> postsComunes = new ArrayList<>();

            for (Post post : listaPosts) {
                if (post instanceof Comun) {
                    postsComunes.add(post);
                } else {
                    postsAnclados.add(post);
                }
            }

            // Combinar ambas listas.
            postsAnclados.addAll(postsComunes);

            // Retornamos la lista ordenada.
            return postsAnclados;

        } catch (PersistenceException pe) {
            throw new PersistenciaException("No se pudieron consultar los posts.");
        } finally {
            // Cerramos el entity manager.
            em.close();
        }
    }

    @Override
    public List<Post> obtenerPostsUsuario(Usuario usuario) throws PersistenciaException {
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

            // Con esta línea especificamos que la consulta seleccionará
            // todos los posts de la categoría especificada.
            cq.where(cb.equal(root.get("usuario"), usuario));

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
            logger.log(Level.WARNING, pe.getMessage());
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
            // Obtenemos la lista de comentarios del post.
            List<Comentario> comentarios = post.getComentarios();

            for (Comentario comentario : comentarios) {
                // Se obtiene la lista de respuestas de cada comentario.
                List<Comentario> respuestas = comentario.getRespuestas();
                for (Comentario respuesta : respuestas) {
                    // Iniciamos la transacción.
                    em.getTransaction().begin();

                    // Mergeamos la entidad.
                    respuesta = em.merge(respuesta);

                    // Mandamos a eliminar la respuesta.
                    em.remove(respuesta);

                    // Hacemos el commit y cerramos el entity manager.
                    em.getTransaction().commit();
                }
            }
            // Iniciamos otra transacción.
            em.getTransaction().begin();

            // Mergeamos la entidad de Post y la refrescamos.
            post = em.merge(post);
            em.refresh(post);

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

    @Override
    public void anclarPost(Anclado postAnclado) throws PersistenciaException {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        try {
            // Iniciamos la transacción.
            em.getTransaction().begin();

            // Consulta JPQL para actualizar el tipoPost y el administrador
            em.createQuery(
                    "UPDATE Post p "
                    + "SET p.tipoPost = :tipoPost, p.administrador = :adminId "
                    + "WHERE p.id = :idPost")
                    .setParameter("tipoPost", "Anclado")
                    .setParameter("adminId", postAnclado.getAdministrador())
                    .setParameter("idPost", postAnclado.getId())
                    .executeUpdate();

            // Hacemos el commit y cerramos el entity manager.
            em.getTransaction().commit();
            em.close();

            // Imprimimos un mensaje de que se eliminó un post.
            logger.log(Level.INFO, "Se ha eliminado 1 post común correctamente.");
            logger.log(Level.INFO, "Se ha insertado 1 post anclado correctamente.");
        } catch (PersistenceException pe) {
            logger.log(Level.WARNING, pe.getMessage());
            throw new PersistenciaException("No se pudo eliminar el post.");
        }
    }

    /**
     * Método para eliminar un post anclado y persistirlo como un post común.
     *
     * @param postComun El id del post que se quiere desanclar.
     * @param postAnclado El id del post que se quiere desanclar.
     * @throws PersistenciaException
     */
    @Override
    public void desanclarPost(Comun postComun) throws PersistenciaException {
        // Creamos un entity manager.
        EntityManager em = conexion.crearConexion();

        try {
            // Iniciamos la transacción.
            em.getTransaction().begin();

            // Consulta JPQL para actualizar el tipoPost y el administrador
            em.createQuery(
                    "UPDATE Post p "
                    + "SET p.tipoPost = :tipoPost, p.administrador = :adminId "
                    + "WHERE p.id = :idPost")
                    .setParameter("tipoPost", "Comun")
                    .setParameter("adminId", null)
                    .setParameter("idPost", postComun.getId())
                    .executeUpdate();

            // Hacemos el commit y cerramos el entity manager.
            em.getTransaction().commit();
            em.close();

            // Imprimimos un mensaje de que se eliminó un post.
            logger.log(Level.INFO, "Se ha eliminado 1 post anclado correctamente.");
            logger.log(Level.INFO, "Se ha insertado 1 post común correctamente.");
        } catch (PersistenceException pe) {
            throw new PersistenciaException("No se pudo eliminar el post.");
        }
    }

}
