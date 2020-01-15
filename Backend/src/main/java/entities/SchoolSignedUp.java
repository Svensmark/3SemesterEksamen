/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author emilt
 */
@Entity
public class SchoolSignedUp implements Serializable {

    //Fields
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String grade;
    @Temporal(TemporalType.DATE)
    private Date passedDate;
    
    @ManyToOne
    private SchoolStudent student;

    @ManyToOne
    private SchoolClass schoolClass;
    
    
    //
    public SchoolSignedUp() {
    }

    public SchoolSignedUp(String grade, Date passedDate, SchoolStudent student, SchoolClass schoolClass) {
        this.grade = grade;
        this.passedDate = passedDate;
        this.student = student;
        this.schoolClass = schoolClass;
    }
    
       
    
    //Methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Date getPassedDate() {
        return passedDate;
    }

    public void setPassedDate(Date passedDate) {
        this.passedDate = passedDate;
    }

    public SchoolStudent getStudent() {
        return student;
    }

    public void setStudent(SchoolStudent student) {
        this.student = student;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

      
}
