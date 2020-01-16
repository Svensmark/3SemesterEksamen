/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.dto;

import java.util.Date;

/**
 *
 * @author emilt
 */
public class SchoolStudentCourseDTO {
    
    //
    private String courseName;
    private String description;
    private String grade;
    private Date passed;

    
    //
    public SchoolStudentCourseDTO(String courseName, String description, String grade, Date passed) {
        this.courseName = courseName;
        this.description = description;
        this.grade = grade;
        this.passed = passed;
    }
    
    
    //
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Date getPassed() {
        return passed;
    }

    public void setPassed(Date passed) {
        this.passed = passed;
    }
    
    
}
