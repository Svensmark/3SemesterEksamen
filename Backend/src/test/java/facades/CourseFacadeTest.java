/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.SchoolClass;
import entities.SchoolCourse;
import entities.dto.SchoolCourseDTO;
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
public class CourseFacadeTest {
    
    //
    private static EntityManagerFactory emf;
    private static SchoolCourseFacade facade;
    private ArrayList<SchoolCourseDTO> courses = new ArrayList();

    //
    public CourseFacadeTest() {
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
        facade = SchoolCourseFacade.getSchoolCourseFacade(emf);
    }

    @BeforeAll
    public static void setUpClassV2() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);
        facade = SchoolCourseFacade.getSchoolCourseFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("SchoolCourse.deleteAllRows").executeUpdate();
            List<SchoolClass> sc = new ArrayList();

            SchoolCourse sc1 = new SchoolCourse("Datamatiker","Description DATA",sc);
            SchoolCourse sc2 = new SchoolCourse("Geomatiker","Description GEO",sc);
            SchoolCourse sc3 = new SchoolCourse("Graphamatiker","Description GRAPH",sc);
            
            courses.add(new SchoolCourseDTO(sc1));
            courses.add(new SchoolCourseDTO(sc2));
            courses.add(new SchoolCourseDTO(sc3));
            
            em.persist(sc1);
            em.persist(sc2);
            em.persist(sc3);
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    
    //
    @Test
    public void testGetAllCourses() {
        assertEquals(courses.size(), facade.getAllCourses().size());
    }
    
    @Test
    public void testAddClass() {
        EntityManager em = emf.createEntityManager();
        int before = em.createQuery("Select SchoolCourse from SchoolCourse schoolcourse", SchoolCourse.class).getResultList().size();
        System.out.println(before);
        facade.addCourse(new SchoolCourseDTO(new SchoolCourse("Course 1", "Desc 1")));
        int after = em.createQuery("Select SchoolCourse from SchoolCourse schoolcourse", SchoolCourse.class).getResultList().size();
        assertEquals(before+1,after);
    }
    
    @Test
    public void testRemoveClass() {
        
    }
}
