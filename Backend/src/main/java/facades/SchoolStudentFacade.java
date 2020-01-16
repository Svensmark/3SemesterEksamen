/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.Role;
import entities.SchoolClass;
import entities.SchoolSignedUp;
import entities.SchoolStudent;
import entities.User;
import entities.dto.SchoolStudentCourseDTO;
import entities.dto.SchoolStudentDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;

/**
 *
 * @author emilt
 */
public class SchoolStudentFacade {

    //
    private static SchoolStudentFacade instance;
    private static EntityManagerFactory emf;

    //
    private SchoolStudentFacade() {

    }

    //
    public static SchoolStudentFacade getSchoolStudentFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new SchoolStudentFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void addStudent(String name, String email, String password) {
        EntityManager em = emf.createEntityManager();
        if (getStudentDTO(name) == null) {
            try {
                em.getTransaction().begin();
                User user = new User(name, password);
                Role userRole = null;
                try {
                    userRole = em.createQuery("SELECT ss FROM Role ss WHERE ss.roleName = :name", Role.class)
                            .setParameter("name", "Student")
                            .getSingleResult();
                } catch (NoResultException e) {
                    userRole = new Role("Student");
                    em.persist(userRole);
                }

                user.addRole(userRole);
                em.persist(user);

                SchoolStudent ss = new SchoolStudent(name, email);
                em.persist(ss);
                em.getTransaction().commit();
            } finally {
                em.close();
            }
        }
    }

    public SchoolStudentDTO getStudent(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            SchoolStudent ss = em.createQuery("SELECT ss FROM SchoolStudent ss WHERE ss.id = :id", SchoolStudent.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return new SchoolStudentDTO(ss);
        } finally {
            em.close();
        }
    }

    public SchoolStudentDTO getStudentDTO(String name) throws NoResultException{
        EntityManager em = emf.createEntityManager();
        try {
            SchoolStudent ss = em.createQuery("SELECT ss FROM SchoolStudent ss WHERE ss.name = :name", SchoolStudent.class)
                    .setParameter("name", name)
                    .getSingleResult();
            return new SchoolStudentDTO(ss);
        } catch (Exception e) {
                e.printStackTrace();
                return null;
        } finally {
            em.close();
        }
        
    }

    public SchoolStudent getStudent(String name) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT ss FROM SchoolStudent ss WHERE ss.name = :name", SchoolStudent.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<SchoolStudentCourseDTO> getStudentCourses(Long id) {
        ArrayList<SchoolStudentCourseDTO> sscL = new ArrayList();
        EntityManager em = emf.createEntityManager();
        try {
            List<SchoolSignedUp> l = em.createQuery("SELECT ss FROM SchoolSignedUp ss WHERE ss.student.id = :id", SchoolSignedUp.class)
                    .setParameter("id", id)
                    .getResultList();
            for (SchoolSignedUp ssu : l) {
                String grade = null;
                Date passed = null;
                if (ssu.getGrade() != null) {
                    grade = ssu.getGrade();
                    passed = ssu.getPassedDate();
                }

                String name = ssu.getSchoolClass().getCourse().getCourseName();
                String desc = ssu.getSchoolClass().getCourse().getDescription();

                sscL.add(new SchoolStudentCourseDTO(name, desc, grade, passed));
            }
        } finally {
            em.close();
        }

        return sscL;
    }
}
