/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.itson.aplicacionesweb.themusichub.daos;

import com.mycompany.dto.UsuarioDTO;
import org.itson.aplicacionesweb.themusichub.modelo.Usuario;

/**
 *
 * @author Familia
 */
public interface IUsuarioDAO {
    public Usuario registrarUsuario(UsuarioDTO usuarioDTO);
    
    public Usuario iniciarSesion(String contrasena, String correo);
    
}
