/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.aplicacionesweb.themusichub.daos;

import com.mycompany.dto.UsuarioNuevoDTO;
import org.itson.aplicacionesweb.themusichub.modelo.Usuario;
import org.itson.aplicacionesweb.themusichub.persistenciaException.PersistenciaException;

/**
 *
 * @author Familia
 */
public interface IUsuarioDAO {
    public Usuario registrarUsuario(Usuario usuario) throws PersistenciaException;
    
    public Usuario buscarUsuario(String correo) throws PersistenciaException;

    public Usuario obtenerUsuarioCorreoContra(String contrasenia, String correo) throws PersistenciaException;
}
