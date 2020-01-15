/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author emilt
 */
@Entity
@NamedQuery(name = "SchoolCourse.deleteAllRows", query = "DELETE from SchoolCourse")
public class SchoolCourse implements Serializable {

    //Fields
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String description;
    
    @OneToMany(mappedBy ="course")
    private List<SchoolClass> classes;
    

    
    //
    public SchoolCourse() {
    }

    public SchoolCourse(String courseName, String description) {
        this.courseName = courseName;
        this.description = description;
    }
    
    public SchoolCourse(String courseName, String description, List<SchoolClass> classes) {
        this.courseName = courseName;
        this.description = description;
        this.classes = classes;
    }
    
    
    //Methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SchoolClass> getClasses() {
        return classes;
    }

    public void setClasses(List<SchoolClass> classes) {
        this.classes = classes;
    }

    public void addClass(SchoolClass sc) {
        this.classes.add(sc);
    }

    

    
}
