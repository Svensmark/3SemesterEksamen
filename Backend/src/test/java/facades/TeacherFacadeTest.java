/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.SchoolClass;
import entities.SchoolTeacher;
import entities.dto.SchoolTeacherDTO;
import errorhandling.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

/**
 *
 * @author emilt
 */
public class TeacherFacadeTest {

    //
    private static EntityManagerFactory emf;
    private static SchoolTeacherFacade facade;
    private ArrayList<SchoolTeacherDTO> teachers = new ArrayList();

    //
    public TeacherFacadeTest() {
    }

    //
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/startcode_test",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
        facade = SchoolTeacherFacade.getSchoolTeacherFacade(emf);
    }

    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = SchoolTeacherFacade.getSchoolTeacherFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("SchoolTeacher.deleteAllRows").executeUpdate();
            List<SchoolClass> sc = new ArrayList();

            SchoolTeacher st1 = new SchoolTeacher("Teacher1", sc);
            SchoolTeacher st2 = new SchoolTeacher("Teacher2", sc);
            SchoolTeacher st3 = new SchoolTeacher("Teacher3", sc);

            em.persist(st1);
            em.persist(st2);
            em.persist(st3);

            teachers.add(new SchoolTeacherDTO(st1));
            teachers.add(new SchoolTeacherDTO(st2));
            teachers.add(new SchoolTeacherDTO(st3));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //
    @Test
    public void testAddTeacher() throws NotFoundException{
        EntityManager em = emf.createEntityManager();
        int before = em.createQuery("Select SchoolTeacher from SchoolTeacher schoolteacher", SchoolTeacher.class).getResultList().size();
        List<SchoolClass> sc = new ArrayList();
        SchoolTeacher st1 = new SchoolTeacher("Teacher1", sc);
        facade.addTeacher(new SchoolTeacherDTO(st1).getName(),"randompassword");
        int after = em.createQuery("Select SchoolTeacher from SchoolTeacher schoolteacher", SchoolTeacher.class).getResultList().size();;
        assertEquals(before+1,after);
    }

    @Test
    public void testGetAllTeachers() {
        assertEquals(teachers.size(),facade.getAllTeachers().size());
    }

    @Test
    public void testGetSchoolClassesByName() {
        
    }
}
