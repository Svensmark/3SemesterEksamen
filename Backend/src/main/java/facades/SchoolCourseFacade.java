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
    
    public static SchoolCourseFacade getSchoolCourseFacade() {
        if (instance == null) {
            instance = new SchoolCourseFacade();
        }
        return instance;
    }


    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    //
    public List<SchoolCourseDTO> getAllCourses() {
        List<SchoolCourse> sc = getEntityManager().createQuery("SELECT SchoolCourse FROM SchoolCourse schoolcourse", SchoolCourse.class).getResultList();
        ArrayList<SchoolCourseDTO> scdto = new ArrayList();
        for (SchoolCourse schoolCourse : sc) {
            SchoolCourseDTO dto = new SchoolCourseDTO(schoolCourse);
            System.out.println(dto);
            scdto.add(dto);
        }
        return scdto;
    }

    public void addCourse(SchoolCourseDTO scdto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new SchoolCourse(scdto.getCourseName(), scdto.getDescription()));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void deleteCourse(Long id) throws NotFoundException {
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

    public void editCourse(SchoolCourseDTO scdto) throws NotFoundException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(scdto);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public SchoolCourseDTO getCourse(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            SchoolCourse sc = em.createQuery("SELECT sc FROM SchoolCourse sc WHERE sc.id = :id", SchoolCourse.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return new SchoolCourseDTO(sc);
        } finally {
            em.close();
        }
    }

    public int getNumberOfClasses(Long id) {
        return getCourse(id).getClasses().size();
    }

}
