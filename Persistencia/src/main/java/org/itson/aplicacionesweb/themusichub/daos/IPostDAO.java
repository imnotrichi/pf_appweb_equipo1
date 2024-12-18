/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.aplicacionesweb.themusichub.daos;

import java.util.List;
import org.itson.aplicacionesweb.themusichub.enums.CategoriaPost;
import org.itson.aplicacionesweb.themusichub.modelo.Anclado;
import org.itson.aplicacionesweb.themusichub.modelo.Comun;
import org.itson.aplicacionesweb.themusichub.modelo.Post;
import org.itson.aplicacionesweb.themusichub.modelo.Usuario;
import org.itson.aplicacionesweb.themusichub.persistenciaException.PersistenciaException;

/**
 *
 * @author Familia
 */
public interface IPostDAO {
    public Post obtenerPostPorID(Long id) throws PersistenciaException;
    public List<Post> obtenerTodosPosts() throws PersistenciaException;
    public List<Post> obtenerPostsPorCategoria(CategoriaPost categoria) throws PersistenciaException;
    public List<Post> obtenerPostsUsuario(Usuario usuario) throws PersistenciaException;
    public void publicarPost(Post post) throws PersistenciaException;
    public void editarPost(Post post) throws PersistenciaException;
    public void eliminarPost(Post post) throws PersistenciaException;
    public Post buscarPostPorAtributos(Post post) throws PersistenciaException;
    public void anclarPost(Comun postComun, Anclado postAnclado) throws PersistenciaException;
    public void desanclarPost(Comun comun, Anclado anclado) throws PersistenciaException;
}
