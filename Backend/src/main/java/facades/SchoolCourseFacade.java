/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.SchoolCourse;
import entities.dto.SchoolCourseDTO;
import errorhandling.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author emilt
 */
public class SchoolCourseFacade {
    
    //
    private static SchoolCourseFacade instance;
    private static EntityManagerFactory emf;

    
    //
    private SchoolCourseFacade() {

    }

    
    //
    public static SchoolCourseFacade getSchoolCourseFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new SchoolCourseFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    
    //
    public void createCourse(String name, String description) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new SchoolCourse(name, description, null));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public void deleteCourse(Long id) throws NotFoundException{
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();            
            SchoolCourse sc = em.find(SchoolCourse.class, id);
            
            if (sc == null) {
                throw new NotFoundException("Course not found.");
            }
            
            em.remove(sc);
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public List<SchoolCourseDTO> getAllCourses() {
        EntityManager em = emf.createEntityManager();
        List<SchoolCourse> SchoolCourses = em.createQuery("Select SchoolCourse from SchoolCourse schoolcourse", SchoolCourse.class).getResultList();
        ArrayList<SchoolCourseDTO> SchoolCoursesDTO = new ArrayList();
        for (SchoolCourse sc : SchoolCourses) {
            SchoolCoursesDTO.add(new SchoolCourseDTO(sc));
        }        
        return SchoolCoursesDTO;
    }
    
    public void addCourse(SchoolCourseDTO scdto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new SchoolCourse(scdto.getCourseName(),scdto.getDescription()));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
}
