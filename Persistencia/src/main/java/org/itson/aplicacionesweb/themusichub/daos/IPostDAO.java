/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.aplicacionesweb.themusichub.daos;

import java.util.List;
import org.itson.aplicacionesweb.themusichub.modelo.Comun;
import org.itson.aplicacionesweb.themusichub.modelo.Post;

/**
 *
 * @author Familia
 */
public interface IPostDAO {
    public Post obtenerPostPorID(Long id);
    public List<Post> obtenerTodosPosts();
    public void publicarPost(Post post);
    public void editarPost(Post post);
    public void eliminarPost(Post post);
}
