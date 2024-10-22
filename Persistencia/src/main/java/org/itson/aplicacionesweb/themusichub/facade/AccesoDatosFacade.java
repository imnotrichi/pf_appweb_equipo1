/*
 * AccesoDatosFacade.java
 */
package org.itson.aplicacionesweb.themusichub.facade;

/**
 * @author Equipo1
 */
public class AccesoDatosFacade implements IAccesoDatosFacade {
    
    private IConexion conexion;
    private AbstractDAOFactory fabrica;
    
    private IUsuariosDAO usuariosDAO;
    private IPostsDAO postsDAO;
    private IComentariosDAO comentariosDAO;
    
    public AccesoDatosFacade() {
        conexion = new Conexion();
        fabrica = new DAOFactory(conexion);
        
        usuariosDAO = fabrica.getUsuariosDAO();
        postsDAO = fabrica.getPostsDAO();
        comentariosDAO = fabrica.getcomentarioDAO();
    }

    @Override
    public void registrarUsuario(Object usuario) {
        
        usuariosDAO.registrarUsuario(usuario);
    }

    @Override
    public void publicarPost(Object post) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarPost(Object post) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void comentarPost(Object comentario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void eliminarComentario(Object comentario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void responderComentario(Object respuesta) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
