/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author emilt
 */
public class SchoolClassFacade {
    
    //
    private static SchoolClassFacade instance;
    private static EntityManagerFactory emf;

    //
    private SchoolClassFacade() {

    }

    //
    public static SchoolClassFacade getSchoolClassFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new SchoolClassFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //
    
}
