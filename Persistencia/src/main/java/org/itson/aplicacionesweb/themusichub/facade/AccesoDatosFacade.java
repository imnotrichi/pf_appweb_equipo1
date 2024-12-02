/*
 * AccesoDatosFacade.java
 */
package org.itson.aplicacionesweb.themusichub.facade;

import com.mycompany.dto.AdministradorDTO;
import com.mycompany.dto.AncladoDTO;
import com.mycompany.dto.ComentarioDTO;
import com.mycompany.dto.ComunDTO;
import com.mycompany.dto.EstadoDTO;
import com.mycompany.dto.MunicipioDTO;
import com.mycompany.dto.NormalDTO;
import com.mycompany.dto.PostDTO;
import com.mycompany.dto.UsuarioDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.itson.aplicacionesweb.themusichub.auxiliares.AESEncriptador;
import org.itson.aplicacionesweb.themusichub.conexion.Conexion;
import org.itson.aplicacionesweb.themusichub.conexion.IConexion;
import org.itson.aplicacionesweb.themusichub.daos.IComentarioDAO;
import org.itson.aplicacionesweb.themusichub.daos.IPostDAO;
import org.itson.aplicacionesweb.themusichub.daos.IUsuarioDAO;
import org.itson.aplicacionesweb.themusichub.factory.AbstractDAOFactory;
import org.itson.aplicacionesweb.themusichub.factory.DAOFactory;
import org.itson.aplicacionesweb.themusichub.modelo.Administrador;
import org.itson.aplicacionesweb.themusichub.enums.CategoriaPost;
import org.itson.aplicacionesweb.themusichub.modelo.Anclado;
import org.itson.aplicacionesweb.themusichub.modelo.Comentario;
import org.itson.aplicacionesweb.themusichub.modelo.Comun;
import org.itson.aplicacionesweb.themusichub.modelo.Estado;
import org.itson.aplicacionesweb.themusichub.modelo.Municipio;
import org.itson.aplicacionesweb.themusichub.modelo.Normal;
import org.itson.aplicacionesweb.themusichub.modelo.Post;
import org.itson.aplicacionesweb.themusichub.modelo.Usuario;
import org.itson.aplicacionesweb.themusichub.persistenciaException.FacadeException;
import org.itson.aplicacionesweb.themusichub.persistenciaException.PersistenciaException;

/**
 * @author Equipo1
 */
public class AccesoDatosFacade implements IAccesoDatosFacade {

    private IConexion conexion;
    private AbstractDAOFactory fabrica;

    private IUsuarioDAO usuariosDAO;
    private IPostDAO postsDAO;
    private IComentarioDAO comentariosDAO;

    public AccesoDatosFacade() {
        conexion = new Conexion();
        fabrica = new DAOFactory(conexion);

        usuariosDAO = fabrica.getUsuarioDAO();
        postsDAO = fabrica.getPostDAO();
        comentariosDAO = fabrica.getComentarioDAO();
    }

    @Override
    public void registrarUsuario(UsuarioDTO usuariodto) throws FacadeException {
        try {
            Usuario usuarioExistente;
            usuarioExistente = usuariosDAO.buscarUsuario(usuariodto.getCorreo());
            if (usuarioExistente != null) {
                throw new FacadeException("El correo que ingresó ya está registrado.");
            }
        } catch (PersistenciaException ex) {
            throw new FacadeException(ex.getMessage());
        }

        Normal usuario = new Normal(
                usuariodto.getCorreo(),
                usuariodto.getNombres(),
                usuariodto.getApellidoPaterno(),
                usuariodto.getApellidoMaterno(),
                usuariodto.getNombreUsuario(),
                AESEncriptador.encriptar(usuariodto.getContrasenia()),
                usuariodto.getTelefono(),
                usuariodto.getAvatar(),
                usuariodto.getCiudad(),
                usuariodto.getFechaNacimiento(),
                usuariodto.getGenero(),
                new Municipio(1L, usuariodto.getMunicipio().getNombre(), new Estado(1L, usuariodto.getMunicipio().getEstado().getNombre())));

        try {
            usuariosDAO.registrarUsuario(usuario);
        } catch (PersistenciaException ex) {
            throw new FacadeException("No se pudo publicar el usuario.");
        }
    }

    @Override
    public void publicarPost(PostDTO post) throws FacadeException {
        CategoriaPost categoria;
        switch (post.getCategoria().toUpperCase()) {
            case "GENERAL":
                categoria = CategoriaPost.GENERAL;
                break;
            case "NOTICIA":
                categoria = CategoriaPost.NOTICIAS;
                break;
            case "PLAYLIST":
                categoria = CategoriaPost.PLAYLIST;
                break;
            case "REVIEW":
                categoria = CategoriaPost.REVIEWS;
                break;
            default:
                categoria = CategoriaPost.GENERAL;
        }

        try {
            UsuarioDTO usuarioDTO = post.getUsuario();
            Usuario usuario = usuariosDAO.buscarUsuario(usuarioDTO.getCorreo());

            Post nuevoPost = new Comun(
                    post.getFechaHoraCreacion(),
                    post.getTitulo(),
                    post.getSubtitulo(),
                    post.getContenido(),
                    categoria,
                    usuario,
                    post.getImagen());
            postsDAO.publicarPost(nuevoPost);
        } catch (PersistenciaException ex) {
            throw new FacadeException("No se pudo publicar el post.");
        }
    }

    @Override
    public void eliminarPost(Long id, UsuarioDTO usuario) throws FacadeException {
        try {
            Post postExistente = postsDAO.obtenerPostPorID(id);

            if (postExistente == null) {
                throw new FacadeException("El post no existe.");
            }

            /**
             * Si el usuario que quiere borrar el post no es el dueño o un
             * administrador, se lanza excepción.
             */
            if (!postExistente.getUsuario().getCorreo().equals(usuario.getCorreo()) && !(usuario instanceof AdministradorDTO)) {
                throw new FacadeException("No tiene permisos para borrar este post.");
            }

            postsDAO.eliminarPost(postExistente);

        } catch (PersistenciaException ex) {
            throw new FacadeException("No se pudo eliminar el post.");
        }
    }

    @Override
    public void comentarPost(ComentarioDTO comentarioDTO, PostDTO postDTO) throws FacadeException {
        try {
            if (postDTO instanceof AncladoDTO) {
                // Error si se trata comentar un post anclado.
                throw new FacadeException("No se pueden comentar posts anclados.");
            }

            // Obtenemos la entidad de Post.
            Post post = postsDAO.obtenerPostPorID(postDTO.getId());

            Comun postComun = new Comun(post.getId(), post.getFechaHoraCreacion(), post.getTitulo(), post.getSubtitulo(), post.getContenido(), post.getCategoria(), post.getComentarios(), post.getUsuario(), post.getImagen());

            // Buscamos la entidad del usuario que comentó.
            Normal usuario = (Normal) usuariosDAO.buscarUsuario(comentarioDTO.getUsuario().getCorreo());

            Comentario comentario = new Comentario(
                    comentarioDTO.getFechaHora(),
                    comentarioDTO.getContenido(),
                    postComun,
                    usuario);
            comentariosDAO.publicarComentario(comentario);
        } catch (PersistenciaException ex) {
            Logger.getLogger(AccesoDatosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void eliminarComentario(ComentarioDTO comentarioDTO, UsuarioDTO usuario) throws FacadeException {
        try {
            Comentario comentario = comentariosDAO.obtenerComentario(comentarioDTO.getId());

            if (comentario == null) {
                throw new FacadeException("El comentario no existe.");
            }

            /**
             * Si el usuario que quiere borrar el post no es el dueño o un
             * administrador, se lanza excepción.
             */
            if (!comentario.getUsuario().getCorreo().equals(usuario.getCorreo()) && !(usuario instanceof AdministradorDTO)) {
                throw new FacadeException("No tiene permisos para borrar este comentario.");
            }

            comentariosDAO.eliminarComentario(comentario);
        } catch (PersistenciaException ex) {
            throw new FacadeException("No se pudo eliminar el comentario.");
        }
    }

    @Override
    public void responderComentario(ComentarioDTO respuestaDTO, ComentarioDTO comentarioDTO) throws FacadeException {
        try {
            // Obtenemos la entidad de Comentario.
            Comentario comentario = comentariosDAO.obtenerComentario(comentarioDTO.getId());

            // Buscamos la entidad del usuario que comentó.
            Normal usuario = (Normal) usuariosDAO.buscarUsuario(respuestaDTO.getUsuario().getCorreo());

            Comentario respuesta = new Comentario(
                    respuestaDTO.getFechaHora(),
                    respuestaDTO.getContenido(),
                    comentario,
                    usuario);
            comentariosDAO.publicarComentario(respuesta);
        } catch (PersistenciaException ex) {
            Logger.getLogger(AccesoDatosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para obtener un usuario dados un correo y contraseña.
     *
     * @param correo Correo del usuario.
     * @param contrasenia Contraseña del usuario.
     * @return
     * @throws FacadeException
     */
    @Override
    public UsuarioDTO obtenerUsuario(String correo, String contrasenia) throws FacadeException {
        try {
            // Encriptamos la contraseña recibida.
            contrasenia = AESEncriptador.encriptar(contrasenia);

            // Mandamos a buscar un usuario con el correo y la contraseña.
            Usuario usuario = usuariosDAO.obtenerUsuarioCorreoContra(correo, contrasenia);

            if (usuario != null) {
                // Convertimos el usuario de entidad a DTO.
                UsuarioDTO usuarioDTO = null;
                EstadoDTO estadoDTO = new EstadoDTO(usuario.getMunicipio().getEstado().getNombre());
                MunicipioDTO municipioDTO = new MunicipioDTO(usuario.getMunicipio().getNombre(), estadoDTO);
                if (usuario instanceof Normal) {
                    usuarioDTO = new NormalDTO(
                            usuario.getNombres(),
                            usuario.getApellidoPaterno(),
                            usuario.getApellidoMaterno(),
                            usuario.getCorreo(),
                            usuario.getContrasenia(),
                            usuario.getTelefono(),
                            usuario.getNombreUsuario(),
                            usuario.getAvatar(),
                            usuario.getCiudad(),
                            usuario.getFechaNacimiento(),
                            usuario.getGenero(),
                            convertirPostsAPostsDTO(usuario.getPosts()),
                            municipioDTO);
                } else if (usuario instanceof Administrador) {
                    usuarioDTO = new AdministradorDTO(
                            usuario.getNombres(),
                            usuario.getApellidoPaterno(),
                            usuario.getApellidoMaterno(),
                            usuario.getCorreo(),
                            usuario.getContrasenia(),
                            usuario.getTelefono(),
                            usuario.getNombreUsuario(),
                            usuario.getAvatar(),
                            usuario.getCiudad(),
                            usuario.getFechaNacimiento(),
                            usuario.getGenero(),
                            convertirPostsAPostsDTO(usuario.getPosts()),
                            municipioDTO);
                }

                // Retornamos el usuario.
                return usuarioDTO;
            } else {
                return null;
            }
        } catch (PersistenciaException ex) {
            throw new FacadeException("No se encontró ningún usuario con los datos proporcionados.");
        }
    }

    /**
     * Método para obtener un usuario dado un correo.
     *
     * @param correo Correo del usuario.
     * @return
     * @throws FacadeException
     */
    @Override
    public UsuarioDTO obtenerUsuario(String correo) throws FacadeException {
        try {
            // Mandamos a buscar un usuario con el correo y la contraseña.
            Usuario usuario = usuariosDAO.buscarUsuario(correo);

            // Convertimos el usuario de entidad a DTO.
            UsuarioDTO usuarioDTO = null;
            EstadoDTO estadoDTO = new EstadoDTO(usuario.getMunicipio().getEstado().getNombre());
            MunicipioDTO municipioDTO = new MunicipioDTO(usuario.getMunicipio().getNombre(), estadoDTO);
            if (usuario instanceof Normal) {
                usuarioDTO = new NormalDTO(
                        usuario.getNombres(),
                        usuario.getApellidoPaterno(),
                        usuario.getApellidoMaterno(),
                        usuario.getCorreo(),
                        usuario.getContrasenia(),
                        usuario.getTelefono(),
                        usuario.getNombreUsuario(),
                        usuario.getAvatar(),
                        usuario.getCiudad(),
                        usuario.getFechaNacimiento(),
                        usuario.getGenero(),
                        convertirPostsAPostsDTO(usuario.getPosts()),
                        municipioDTO);
            } else if (usuario instanceof Administrador) {
                usuarioDTO = new AdministradorDTO(
                        usuario.getNombres(),
                        usuario.getApellidoPaterno(),
                        usuario.getApellidoMaterno(),
                        usuario.getCorreo(),
                        usuario.getContrasenia(),
                        usuario.getTelefono(),
                        usuario.getNombreUsuario(),
                        usuario.getAvatar(),
                        usuario.getCiudad(),
                        usuario.getFechaNacimiento(),
                        usuario.getGenero(),
                        convertirPostsAPostsDTO(usuario.getPosts()),
                        municipioDTO);
            }

            // Retornamos el usuario.
            return usuarioDTO;
        } catch (PersistenciaException ex) {
            throw new FacadeException("No se encontró ningún usuario con los datos proporcionados.");
        }
    }

    /**
     * Método para obtener todos los posts por categoría y convertirlos a DTO.
     *
     * @param categoria Categoría por la cuál se filtrarán los posts, si es null
     * se obtendrán todos los posts.
     * @return Una lista con todos los posts encontrados.
     */
    @Override
    public List<PostDTO> obtenerPostsPorCategoria(CategoriaPost categoria) throws FacadeException {
        List<PostDTO> postsDTO = null;
        try {
            // Se obtienen los posts.
            List<Post> posts = postsDAO.obtenerPostsPorCategoria(categoria);
            // Se convierten a DTO.
            postsDTO = convertirPostsAPostsDTO(posts);
        } catch (PersistenciaException ex) {
            throw new FacadeException(ex.getMessage());
        }
        return postsDTO;
    }

    /**
     * Método para convertir los posts de entidad a DTO.
     *
     * @param posts Lista de posts a convertir.
     * @return Una lista con los posts convertidos.
     */
    private List<PostDTO> convertirPostsAPostsDTO(List<Post> posts) {
        List<PostDTO> postsDTO = new ArrayList<>();
        for (Post post : posts) {
            postsDTO.add(convertirPostAPostDTO(post));
            // Si el post es común, se obtiene el usuario que lo publicó.
        }
        return postsDTO;
    }

    private PostDTO convertirPostAPostDTO(Post post) {
        PostDTO postDTO = null;
        if (post instanceof Comun) {
            postDTO = new ComunDTO(
                    post.getId(),
                    post.getFechaHoraCreacion(),
                    post.getTitulo(),
                    post.getSubtitulo(),
                    post.getContenido(),
                    post.getCategoria().toString(),
                    convertirUsuarioAUsuarioDTO(post.getUsuario()),
                    convertirComentariosAComentariosDTO(post.getComentarios()),
                    post.getImagen(),
                    post.getTipoPost());
        } else {
            postDTO = new AncladoDTO(
                    post.getId(),
                    post.getFechaHoraCreacion(),
                    post.getTitulo(),
                    post.getSubtitulo(),
                    post.getContenido(),
                    post.getCategoria().toString(),
                    convertirUsuarioAUsuarioDTO(post.getUsuario()),
                    convertirComentariosAComentariosDTO(post.getComentarios()),
                    post.getImagen(),
                    post.getTipoPost());
        }
        // Si el post es común, se obtiene el usuario que lo publicó.
        return postDTO;
    }

    /**
     * Método para convertir un usuario de entidad a DTO.
     *
     * @param usuario Usuario a convertir.
     * @return Usuario convertido en DTO.
     */
    public UsuarioDTO convertirUsuarioAUsuarioDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = null;
        EstadoDTO estadoDTO = new EstadoDTO(usuario.getMunicipio().getEstado().getNombre());
        MunicipioDTO municipioDTO = new MunicipioDTO(usuario.getMunicipio().getNombre(), estadoDTO);
        if (usuario instanceof Normal) {
            usuarioDTO = new NormalDTO(
                    usuario.getNombres(),
                    usuario.getApellidoPaterno(),
                    usuario.getApellidoMaterno(),
                    usuario.getCorreo(),
                    usuario.getContrasenia(),
                    usuario.getTelefono(),
                    usuario.getNombreUsuario(),
                    usuario.getAvatar(),
                    usuario.getCiudad(),
                    usuario.getFechaNacimiento(),
                    usuario.getGenero(),
                    municipioDTO);
        } else {
            usuarioDTO = new AdministradorDTO(
                    usuario.getNombres(),
                    usuario.getApellidoPaterno(),
                    usuario.getApellidoMaterno(),
                    usuario.getCorreo(),
                    usuario.getContrasenia(),
                    usuario.getTelefono(),
                    usuario.getNombreUsuario(),
                    usuario.getAvatar(),
                    usuario.getCiudad(),
                    usuario.getFechaNacimiento(),
                    usuario.getGenero(),
                    municipioDTO);
        }

        return usuarioDTO;
    }

    private List<ComentarioDTO> convertirComentariosAComentariosDTO(List<Comentario> comentarios) {
        List<ComentarioDTO> comentariosDTO = new ArrayList<>();
        if (comentarios == null || comentarios.isEmpty()) {
            return comentariosDTO;
        }
        for (Comentario comentario : comentarios) {
            comentariosDTO.add(convertirComentarioAComentarioDTO(comentario));
        }
        return comentariosDTO;
    }

    private ComentarioDTO convertirComentarioAComentarioDTO(Comentario comentario) {
        if (comentario == null) {
            return null;
        }
        ComentarioDTO comentarioDTO;
        comentarioDTO = new ComentarioDTO(
                comentario.getId(),
                comentario.getFechaHora(),
                comentario.getContenido(),
                convertirComentariosAComentariosDTO(comentario.getRespuestas()),
                (comentario.getRespuesta() != null) ? acortarComentarioDTO(comentario.getRespuesta()) : null,
                (comentario.getPost() != null) ? acortarPostDTO(comentario.getPost()) : null,
                (NormalDTO) convertirUsuarioAUsuarioDTO(comentario.getUsuario()));
        return comentarioDTO;
    }

    private ComentarioDTO acortarComentarioDTO(Comentario comentario) {
        ComentarioDTO comentarioCorto = new ComentarioDTO(
                comentario.getId(),
                comentario.getFechaHora(),
                comentario.getContenido(),
                (NormalDTO) convertirUsuarioAUsuarioDTO(comentario.getUsuario()));
        return comentarioCorto;
    }

    private PostDTO acortarPostDTO(Post post) {
        PostDTO postCorto = new PostDTO(
                post.getId(),
                post.getFechaHoraCreacion(),
                post.getContenido(),
                (NormalDTO) convertirUsuarioAUsuarioDTO(post.getUsuario()));
        return postCorto;
    }

    @Override
    public PostDTO obtenerPostID(Long id) throws FacadeException {
        try {
            Post post = postsDAO.obtenerPostPorID(id);

            PostDTO postDTO = convertirPostAPostDTO(post);

            return postDTO;
        } catch (PersistenciaException ex) {
            throw new FacadeException(ex.getMessage());
        }
    }

    @Override
    public ComentarioDTO obtenerComentarioID(Long id) throws FacadeException {
        try {
            Comentario comentario = comentariosDAO.obtenerComentario(id);

            return convertirComentarioAComentarioDTO(comentario);
        } catch (PersistenciaException ex) {
            throw new FacadeException(ex.getMessage());
        }
    }

    @Override
    public List<PostDTO> obtenerPostsPorUsuario(String correo) throws FacadeException {
        List<PostDTO> postsDTO = null;
        try {
            Usuario usuario = usuariosDAO.buscarUsuario(correo);

            // Se obtienen los posts.
            List<Post> posts = postsDAO.obtenerPostsUsuario(usuario);
            // Se convierten a DTO.
            postsDTO = convertirPostsAPostsDTO(posts);
        } catch (PersistenciaException ex) {
            throw new FacadeException(ex.getMessage());
        }
        return postsDTO;
    }

    @Override
    public void anclarPost(Long idPost, String correoAdmin) throws FacadeException {
        try {
            Comun comun = null;
            try {
                comun = (Comun) postsDAO.obtenerPostPorID(idPost);
            } catch (ClassCastException cce) {
                throw new FacadeException("El post que trata de anclar ya está anclado.");
            }

            Administrador admin = (Administrador) usuariosDAO.buscarUsuario(correoAdmin);

            Anclado anclado = new Anclado(
                    comun.getId(),
                    comun.getFechaHoraCreacion(),
                    comun.getTitulo(),
                    comun.getSubtitulo(),
                    comun.getContenido(),
                    comun.getCategoria(),
                    comun.getComentarios(),
                    comun.getUsuario(),
                    admin,
                    comun.getImagen());

            postsDAO.anclarPost(anclado);
        } catch (PersistenciaException ex) {
            Logger.getLogger(AccesoDatosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Método para desanclar un post anclado.
     *
     * @param idPost El id del post que se quiere desanclar.
     * @throws FacadeException Si el post que se quiere desanclar no es del tipo
     * anclado.
     */
    @Override
    public void desanclarPost(Long idPost) throws FacadeException {
        try {
            Anclado anclado = null;
            try {
                anclado = (Anclado) postsDAO.obtenerPostPorID(idPost);
            } catch (ClassCastException cce) {
                throw new FacadeException("El post que trata de desanclar no está anclado.");
            }

            Comun comun = new Comun(
                    anclado.getId(),
                    anclado.getFechaHoraCreacion(),
                    anclado.getTitulo(),
                    anclado.getSubtitulo(),
                    anclado.getContenido(),
                    anclado.getCategoria(),
                    anclado.getComentarios(),
                    anclado.getUsuario(),
                    anclado.getImagen());

            postsDAO.desanclarPost(comun);
        } catch (PersistenciaException ex) {
            Logger.getLogger(AccesoDatosFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
