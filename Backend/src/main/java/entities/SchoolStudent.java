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
import javax.persistence.OneToMany;

/**
 *
 * @author emilt
 */
@Entity
public class SchoolStudent implements Serializable {

    //Fields
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    
    @OneToMany
    private List<SchoolSignedUp> signups;

    
    //
    public SchoolStudent()  {
        
    }

    public SchoolStudent(String name, String email, List<SchoolSignedUp> signups) {
        this.name = name;
        this.email = email;
        this.signups = signups;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SchoolSignedUp> getSignups() {
        return signups;
    }

    public void setSignups(List<SchoolSignedUp> signups) {
        this.signups = signups;
    }
   
    
}
