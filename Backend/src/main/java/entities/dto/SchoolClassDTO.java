/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.dto;

import entities.SchoolClass;
import entities.SchoolCourse;
import entities.SchoolSignedUp;
import entities.SchoolTeacher;
import java.util.List;

/**
 *
 * @author emilt
 */
public class SchoolClassDTO {
    
    //
    private int semester;
    private int maxNumbOfStudents;
    
    private SchoolCourseDTO course;
    private List<SchoolTeacherDTO> teachers;
    private List<SchoolSignedUpDTO> signedUps;
     
    
    //
    public SchoolClassDTO(SchoolClass sc) {
        this.semester = sc.getSemester();
        this.maxNumbOfStudents = sc.getMaxNumbOfStudents();
        this.course = new SchoolCourseDTO(sc.getCourse());
        //this.teachers = sc.getTeachers();
        //this.signedUps = sc.getSignedUps();
    }
    
    
    //
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

    public SchoolCourseDTO getCourse() {
        return course;
    }

    public void setCourse(SchoolCourseDTO course) {
        this.course = course;
    }

    public List<SchoolTeacherDTO> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<SchoolTeacherDTO> teachers) {
        this.teachers = teachers;
    }

    public List<SchoolSignedUpDTO> getSignedUps() {
        return signedUps;
    }

    public void setSignedUps(List<SchoolSignedUpDTO> signedUps) {
        this.signedUps = signedUps;
    }
    
    
    
}
