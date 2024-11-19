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
import java.util.GregorianCalendar;
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
import org.itson.aplicacionesweb.themusichub.modelo.Anclado;
import org.itson.aplicacionesweb.themusichub.enums.CategoriaPost;
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
    public void publicarPost(ComunDTO post) throws FacadeException {
        CategoriaPost categoria;
        switch (post.getCategoria().toUpperCase()) {
            case "GENERAL":
                categoria = CategoriaPost.GENERAL;
                break;
            case "NOTICIAS":
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

        Usuario usuario = null;
        Comun nuevoPost = new Comun(
                post.getFechaHoraCreacion(),
                post.getTitulo(),
                post.getSubtitulo(),
                post.getContenido(),
                categoria,
                usuario);

        UsuarioDTO usuarioDTO = post.getUsuario();
        if (usuarioDTO instanceof NormalDTO) {
            usuario = new Normal(
                    usuarioDTO.getCorreo(),
                    usuarioDTO.getNombres(),
                    usuarioDTO.getApellidoPaterno(),
                    usuarioDTO.getApellidoMaterno(),
                    usuarioDTO.getNombreUsuario(),
                    AESEncriptador.encriptar(usuarioDTO.getContrasenia()),
                    usuarioDTO.getTelefono(),
                    usuarioDTO.getAvatar(),
                    usuarioDTO.getCiudad(),
                    usuarioDTO.getFechaNacimiento(),
                    usuarioDTO.getGenero(),
                    new Municipio(1L, usuarioDTO.getMunicipio().getNombre(), new Estado(1L, usuarioDTO.getMunicipio().getEstado().getNombre())));
        } else if (usuarioDTO instanceof AdministradorDTO) {
            usuario = new Administrador(
                    usuarioDTO.getCorreo(),
                    usuarioDTO.getNombres(),
                    usuarioDTO.getApellidoPaterno(),
                    usuarioDTO.getApellidoMaterno(),
                    usuarioDTO.getNombreUsuario(),
                    AESEncriptador.encriptar(usuarioDTO.getContrasenia()),
                    usuarioDTO.getTelefono(),
                    usuarioDTO.getAvatar(),
                    usuarioDTO.getCiudad(),
                    usuarioDTO.getFechaNacimiento(),
                    usuarioDTO.getGenero(),
                    new Municipio(1L, usuarioDTO.getMunicipio().getNombre(), new Estado(1L, usuarioDTO.getMunicipio().getEstado().getNombre())));
        }

        try {
            postsDAO.publicarPost(nuevoPost);
        } catch (PersistenciaException ex) {
            throw new FacadeException("No se pudo publicar el post.");
        }
    }

    @Override
    public void eliminarPost(PostDTO post) throws FacadeException {
        try {
            Post postExistente = postsDAO.obtenerPostPorID(post.getId());

            if (postExistente == null) {
                throw new FacadeException("El post no existe.");
            }

            postsDAO.eliminarPost(postExistente);

        } catch (PersistenciaException ex) {
            throw new FacadeException("No se pudo eliminar el post.");
        }
    }

    @Override
    public void comentarPost(ComentarioDTO comentarioDTO, PostDTO postDTO) throws FacadeException {
        try {
            // Obtenemos la entidad de Post.
            Post post = postsDAO.obtenerPostPorID(postDTO.getId());
            Comun postComun = null;
            if (postDTO instanceof ComunDTO) {
                // La convertimos a Post Común.
                postComun = new Comun(post.getId(), post.getFechaHoraCreacion(), post.getTitulo(), post.getSubtitulo(), post.getContenido(), post.getCategoria(), post.getComentarios(), post.getUsuario());
            } else if (postDTO instanceof AncladoDTO) {
                // Error si se trata comentar un post anclado.
                throw new FacadeException("No se pueden comentar posts anclados.");
            }

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
    public void eliminarComentario(ComentarioDTO comentario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void responderComentario(ComentarioDTO respuesta, NormalDTO usuario, ComentarioDTO comentarioRespondido) {

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

            // Convertimos el usuario de entidad a DTO.
            EstadoDTO estadoDTO = new EstadoDTO(usuario.getMunicipio().getEstado().getNombre());
            MunicipioDTO municipioDTO = new MunicipioDTO(usuario.getMunicipio().getNombre(), estadoDTO);
            UsuarioDTO usuarioDTO = new UsuarioDTO(
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
            postsDTO.add(
                    new PostDTO(
                            post.getId(),
                            post.getFechaHoraCreacion(),
                            post.getTitulo(),
                            post.getSubtitulo(),
                            post.getContenido(),
                            post.getCategoria().toString(),
                            convertirUsuarioAUsuarioDTO(post.getUsuario())));
            // Si el post es común, se obtiene el usuario que lo publicó.
        }
        return postsDTO;
    }

    /**
     * Método para convertir un usuario de entidad a DTO.
     *
     * @param usuario Usuario a convertir.
     * @return Usuario convertido en DTO.
     */
    public UsuarioDTO convertirUsuarioAUsuarioDTO(Usuario usuario) {
        EstadoDTO estadoDTO = new EstadoDTO(usuario.getMunicipio().getEstado().getNombre());
        MunicipioDTO municipioDTO = new MunicipioDTO(usuario.getMunicipio().getNombre(), estadoDTO);
        UsuarioDTO usuarioDTO = new UsuarioDTO(
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
        return usuarioDTO;
    }

}
