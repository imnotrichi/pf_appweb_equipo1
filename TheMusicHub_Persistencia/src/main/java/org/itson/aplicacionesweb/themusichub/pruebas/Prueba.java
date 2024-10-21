/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.itson.aplicacionesweb.themusichub.pruebas;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ricar
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("TheMusicHubPU");
        //solicitamos una entity manager (acceso a la bd)
        EntityManager entityManager = emFactory.createEntityManager();
       
        entityManager.getTransaction().begin();
  
        entityManager.close();
    }
    
}
