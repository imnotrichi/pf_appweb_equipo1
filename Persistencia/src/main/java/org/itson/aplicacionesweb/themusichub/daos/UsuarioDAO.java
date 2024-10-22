package org.itson.aplicacionesweb.themusichub.daos;

import com.mycompany.dto.UsuarioDTO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.itson.aplicacionesweb.themusichub.conexion.IConexion;
import org.itson.aplicacionesweb.themusichub.modelo.Normal;
import org.itson.aplicacionesweb.themusichub.modelo.Usuario;

/**
 *
 * @author Diego Valenzuela Parra
 */
public class UsuarioDAO implements IUsuarioDAO {

    private IConexion conexion;
    private static final Logger LOG = Logger.getLogger(UsuarioDAO.class.getName());

    public UsuarioDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public Usuario registrarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuarioNuevo = null;
        try {
            usuarioNuevo = new Normal(
                    usuarioDTO.getNombres(),
                    usuarioDTO.getApellidoPaterno(),
                    usuarioDTO.getApellidoMaterno(),
                    usuarioDTO.getCorreo(),
                    usuarioDTO.getContrasenia().getBytes(),
                    usuarioDTO.getTelefono().getBytes(),
                    usuarioDTO.getAvatar(),
                    usuarioDTO.getCiudad(),
                    usuarioDTO.getFechaNacimiento(),
                    usuarioDTO.getGenero());

            EntityManager em = this.conexion.crearConexion();
            em.getTransaction().begin();
            em.persist(usuarioNuevo);
            em.getTransaction().commit();
            em.close();

        } catch (PersistenceException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuarioNuevo;
    }

    @Override
    public Usuario iniciarSesion(String contrasena, String correo) {
        
    }

}
