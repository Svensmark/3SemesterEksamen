/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.SchoolClass;
import entities.SchoolTeacher;
import entities.dto.SchoolClassDTO;
import entities.dto.SchoolTeacherDTO;
import errorhandling.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author emilt
 */
public class SchoolTeacherFacade {

    //
    private static SchoolTeacherFacade instance;
    private static EntityManagerFactory emf;

    //
    private SchoolTeacherFacade() {

    }

    //
    public static SchoolTeacherFacade getSchoolTeacherFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new SchoolTeacherFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //
    public void addTeacher(SchoolTeacherDTO stdto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new SchoolTeacher(stdto.getName(), null));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<SchoolTeacherDTO> getAllTeachers() {
        return getEntityManager().createQuery("SELECT new entities.dto.SchoolTeacherDTO(schoolteacher) FROM SchoolTeacher schoolteacher", SchoolTeacherDTO.class).getResultList();
    }

}
