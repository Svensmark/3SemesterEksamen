package utils;


import entities.Role;
import entities.SchoolClass;
import entities.SchoolCourse;
import entities.User;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.DROP_AND_CREATE);
    EntityManager em = emf.createEntityManager();
    
    // IMPORTAAAAAAAAAANT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // This breaks one of the MOST fundamental security rules in that it ships with default users and passwords
    // CHANGE the three passwords below, before you uncomment and execute the code below
    // Also, either delete this file, when users are created or rename and add to .gitignore
    // Whatever you do DO NOT COMMIT and PUSH with the real passwords

    User user = new User("user", "test1");
    User admin = new User("admin", "test1");
    User both = new User("user_admin", "test1");

    if(admin.getUserPass().equals("test")||user.getUserPass().equals("test")||both.getUserPass().equals("test"))
      throw new UnsupportedOperationException("You have not changed the passwords");

    em.getTransaction().begin();
    Role userRole = new Role("user");
    Role adminRole = new Role("admin");
    user.addRole(userRole);
    admin.addRole(adminRole);
    both.addRole(userRole);
    both.addRole(adminRole);
    
    List<SchoolClass> sc = new ArrayList();
    
    SchoolCourse sc1 = new SchoolCourse("Course name1", "Fall 2010", sc);
    SchoolCourse sc2 = new SchoolCourse("Course name2", "Winter 2010", sc);
    SchoolCourse sc3 = new SchoolCourse("Course name3", "Spring 2010");
    SchoolCourse sc4 = new SchoolCourse("Course name4", "Summer 2010");

    em.persist(sc1);
    em.persist(sc2);
    em.persist(sc3);
    em.persist(sc4);
    
    em.persist(userRole);
    em.persist(adminRole);
    em.persist(user);
    em.persist(admin);
    em.persist(both);
    em.getTransaction().commit();
    System.out.println("PW: " + user.getUserPass());
    System.out.println("Testing user with OK password: " + user.verifyPassword("test"));
    System.out.println("Testing user with wrong password: " + user.verifyPassword("test1"));
    System.out.println("Created TEST Users");
   
  }

}
