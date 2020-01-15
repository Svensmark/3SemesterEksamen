/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.SchoolClass;
import entities.SchoolSignedUp;
import entities.dto.SchoolClassDTO;
import entities.dto.SchoolCourseDTO;
import entities.dto.SchoolStudentDTO;
import entities.dto.SchoolTeacherDTO;
import java.util.ArrayList;
import java.util.List;
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
    public List<SchoolClassDTO> getAllClasses() {
        return getEntityManager().createQuery("SELECT new entities.dto.SchoolClassDTO(schoolclass) FROM SchoolClass schoolclass", SchoolClassDTO.class).getResultList();
    }
    
    public List<SchoolTeacherDTO> getSchoolClassTeachers(Long id) {
        EntityManager em = emf.createEntityManager();
          try {
              SchoolClass sc = em.createQuery("SELECT sc FROM SchoolClass sc WHERE sc.id = :id", SchoolClass.class)
                      .setParameter("id", id)
                      .getSingleResult();
              return new SchoolClassDTO(sc).getTeachers();
        } finally {
            em.close();
        }
    }
    
    public List<SchoolStudentDTO> getSchoolClassStudents(Long id) {
        EntityManager em = emf.createEntityManager();
          try {
              SchoolClass sc = em.createQuery("SELECT sc FROM SchoolClass sc WHERE sc.id = :id", SchoolClass.class)
                      .setParameter("id", id)
                      .getSingleResult();
              ArrayList<SchoolStudentDTO> students = new ArrayList();
              for (SchoolSignedUp su : sc.getSignedUps()) {
                  students.add(new SchoolStudentDTO(su.getStudent()));
              }
              return students;
        } finally {
            em.close();
        }
    }
    
    public SchoolCourseDTO getCourse(Long id) {
        EntityManager em = emf.createEntityManager();
          try {
              SchoolClass sc = em.createQuery("SELECT sc FROM SchoolClass sc WHERE sc.id = :id", SchoolClass.class)
                      .setParameter("id", id)
                      .getSingleResult();
              return new SchoolCourseDTO(sc.getCourse());
        } finally {
            em.close();
        }
    }
    
    
}
