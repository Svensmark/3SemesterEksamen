/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.SchoolClass;
import entities.SchoolCourse;
import entities.SchoolSignedUp;
import entities.SchoolStudent;
import entities.dto.SchoolCourseDTO;
import errorhandling.NotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

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
    
    public void populate() {
        EntityManager em = emf.createEntityManager();
        EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/eksamen1",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
        SchoolStudentFacade facade =  SchoolStudentFacade.getSchoolStudentFacade(EMF);
        try {
            em.getTransaction().begin();
            SchoolCourse sc1 = new SchoolCourse("Course name1", "Fall 2010");
            SchoolCourse sc2 = new SchoolCourse("Course name2", "Winter 2010");
            SchoolCourse sc3 = new SchoolCourse("Course name3", "Spring 2099");
            SchoolCourse sc4 = new SchoolCourse("Course name4", "Summer 2099");
            SchoolCourse sc5 = new SchoolCourse("Course name5", "Fallout 2010");
            SchoolCourse sc6 = new SchoolCourse("Course name6", "Summer 2020");
            SchoolCourse sc7 = new SchoolCourse("Course name7", "Spring 3333");
            SchoolCourse sc8 = new SchoolCourse("Course name8", "Summer 2009");

            em.persist(sc1);
            em.persist(sc2);
            em.persist(sc3);
            em.persist(sc4);
            em.persist(sc5);
            em.persist(sc6);
            em.persist(sc7);
            em.persist(sc8);

            SchoolClass sca1 = new SchoolClass(1, 20, sc1);
            SchoolClass sca2 = new SchoolClass(1, 20, sc3);
            SchoolClass sca3 = new SchoolClass(1, 20, sc2);
            SchoolClass sca4 = new SchoolClass(1, 20, sc5);
            SchoolClass sca5 = new SchoolClass(1, 20, sc7);
            SchoolClass sca6 = new SchoolClass(1, 20, sc2);
            SchoolClass sca7 = new SchoolClass(1, 20, sc1);
            SchoolClass sca8 = new SchoolClass(1, 20, sc8);
            SchoolClass sca9 = new SchoolClass(1, 3, sc5);

            em.persist(sca1);
            em.persist(sca2);
            em.persist(sca3);
            em.persist(sca4);
            em.persist(sca5);
            em.persist(sca6);
            em.persist(sca7);
            em.persist(sca8);
            em.persist(sca9);

            facade.addStudent("Emil", "Svensmark@gmail.com", "test1");
            facade.addStudent("Melody", "mail@mail.com", "test1");
            facade.addStudent("Akira", "mail2@mail.com", "test1");

            SchoolStudent emil = facade.getStudent("Emil");
            SchoolStudent melody = facade.getStudent("Melody");
            SchoolStudent akira = facade.getStudent("Akira");

            SchoolSignedUp ssu1 = new SchoolSignedUp("A+", new Date(), emil, sca1);
            SchoolSignedUp ssu2 = new SchoolSignedUp("B-", new Date(), emil, sca2);
            SchoolSignedUp ssu3 = new SchoolSignedUp(null, null, emil, sca3);
            SchoolSignedUp ssu4 = new SchoolSignedUp(null, null, emil, sca9);
            SchoolSignedUp ssu5 = new SchoolSignedUp("12", new Date(), melody, sca1);
            SchoolSignedUp ssu6 = new SchoolSignedUp("12", new Date(), melody, sca2);
            SchoolSignedUp ssu7 = new SchoolSignedUp("F-", new Date(), akira, sca2);
            SchoolSignedUp ssu8 = new SchoolSignedUp("F-", new Date(), akira, sca1);
            SchoolSignedUp ssu9 = new SchoolSignedUp("F-", new Date(), akira, sca2);

            em.persist(ssu1);
            em.persist(ssu2);
            em.persist(ssu3);
            em.persist(ssu4);
            em.persist(ssu5);
            em.persist(ssu6);
            em.persist(ssu7);
            em.persist(ssu8);
            em.persist(ssu9);

            SchoolTeacherFacade facade2 = SchoolTeacherFacade.getSchoolTeacherFacade(emf);
            facade2.addTeacher("Deku", "99");

            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
