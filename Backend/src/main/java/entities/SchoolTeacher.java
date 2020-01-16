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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

/**
 *
 * @author emilt
 */
@Entity
@NamedQuery(name = "SchoolTeacher.deleteAllRows", query = "DELETE from SchoolTeacher")
public class SchoolTeacher implements Serializable {

    //Fields
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    private List<SchoolClass> classes;

    
    //
    public SchoolTeacher() {
    }

    public SchoolTeacher(String name, List<SchoolClass> classes) {
        this.name = name;
        this.classes = classes;
    }
    
    
    //Methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
