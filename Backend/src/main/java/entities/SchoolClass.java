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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author emilt
 */
@Entity
public class SchoolClass implements Serializable {

    //Fields
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int semester;
    private int maxNumbOfStudents;
    @ManyToOne
    private SchoolCourse course;
    
    @ManyToMany
    private List<SchoolTeacher> teachers;
    
    @OneToMany(mappedBy="schoolClass")
    private List<SchoolSignedUp> signedUps;
    
    
    //Con
    public SchoolClass() {
    }

    public SchoolClass(int semester, int maxNumbOfStudents, SchoolCourse course, List<SchoolTeacher> teachers, List<SchoolSignedUp> signedUps) {
        this.semester = semester;
        this.maxNumbOfStudents = maxNumbOfStudents;
        this.course = course;
        this.teachers = teachers;
        this.signedUps = signedUps;
    }
    
    public SchoolClass(int semester, int maxNumb, SchoolCourse course) {
        this.semester = semester;
        this.maxNumbOfStudents = maxNumb;
        this.course = course;
    }
    

    //Methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getMaxNumbOfStudents() {
        return maxNumbOfStudents;
    }

    public void setMaxNumbOfStudents(int maxNumbOfStudents) {
        this.maxNumbOfStudents = maxNumbOfStudents;
    }

    public SchoolCourse getCourse() {
        return course;
    }

    public void setCourse(SchoolCourse course) {
        this.course = course;
    }

    public List<SchoolTeacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<SchoolTeacher> teachers) {
        this.teachers = teachers;
    }

    public List<SchoolSignedUp> getSignedUps() {
        return signedUps;
    }

    public void setSignedUps(List<SchoolSignedUp> signedUps) {
        this.signedUps = signedUps;
    }

    public void addSignedUp(SchoolSignedUp su) {
        this.signedUps.add(su);
    }
    
}
