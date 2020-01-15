/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.dto;

import entities.SchoolClass;
import entities.SchoolSignedUp;
import entities.SchoolStudent;
import java.util.Date;

/**
 *
 * @author emilt
 */
public class SchoolSignedUpDTO {
    
    //
    private String grade;
    private Date passedDate;
    private SchoolStudentDTO student;
    private SchoolClassDTO schoolClass;
    
    
    //
    public SchoolSignedUpDTO(SchoolSignedUp su) {
        this.grade = su.getGrade();
        this.passedDate = su.getPassedDate();
        this.student = new SchoolStudentDTO(su.getStudent());
        this.schoolClass = new SchoolClassDTO(su.getSchoolClass());
    }
    
    
    //
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

    public SchoolStudentDTO getStudent() {
        return student;
    }

    public void setStudent(SchoolStudentDTO student) {
        this.student = student;
    }

    public SchoolClassDTO getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClassDTO schoolClass) {
        this.schoolClass = schoolClass;
    }

    
    
    
}
