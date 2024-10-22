///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
// */
//package org.itson.aplicacionesweb.themusichub.pruebas;
//
//import com.mycompany.dto.EstadoDTO;
//import com.mycompany.dto.MunicipioDTO;
//import com.mycompany.dto.UsuarioDTO;
//import java.util.GregorianCalendar;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.itson.aplicacionesweb.themusichub.facade.AccesoDatosFacade;
//import org.itson.aplicacionesweb.themusichub.facade.IAccesoDatosFacade;
//import org.itson.aplicacionesweb.themusichub.persistenciaException.FacadeException;
//
///**
// *
// * @author ricar
// */
//public class Pruebas {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        
//        IAccesoDatosFacade facade = new AccesoDatosFacade();
//        
//        try {
//            List<EstadoDTO> estados = facade.obtenerEstados();
//            List<MunicipioDTO> municipios = facade.obtenerMunicipios();
//            
//            for (MunicipioDTO municipio : municipios) {
//                System.out.println(municipio.getNombre() + municipio.getEstado().getNombre());
//            }
//            
//            for (EstadoDTO estado : estados) {
//                System.out.println(estado.getNombre());
//            }
//            
//            UsuarioDTO usuarioNuevo = new UsuarioDTO("Ricardo Alán", "Gutiérrez", "Garcés",
//                    "ricky.rgr40@gmail.com", "Ricardo123", "6442289073", "Ciudad Obregón",
//                    new GregorianCalendar(2004, 3, 21), "masculino", 
//                    municipios.get(municipios.indexOf(new MunicipioDTO("Cajeme", estados.get(estados.indexOf(new EstadoDTO("Sonora")))))));
//            
//            facade.registrarUsuario(usuarioNuevo);
//        } catch (FacadeException ex) {
//            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//    }
//    
//}
