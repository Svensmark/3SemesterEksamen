/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.dto;

import entities.SchoolClass;
import entities.SchoolCourse;
import java.util.List;

/**
 *
 * @author emilt
 */
public class SchoolCourseDTO {

    //
    private String courseName;
    private String description;
    private List<SchoolClassDTO> classes;

    //
    public SchoolCourseDTO(SchoolCourse sco) {
        this.courseName = sco.getCourseName();
        this.description = sco.getDescription();
        if (sco.getClasses() != null) {
            for (int i = 0; i < sco.getClasses().size(); ++i) {
                this.classes.add(new SchoolClassDTO(sco.getClasses().get(i)));
            }
        }
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

    public List<SchoolClassDTO> getClasses() {
        return classes;
    }

    public void setClasses(List<SchoolClassDTO> classes) {
        this.classes = classes;
    }

    public void addClass(SchoolClass sc) {
        this.classes.add(new SchoolClassDTO(sc));
    }

    public void addClassDTO(SchoolClassDTO sc) {
        this.classes.add(sc);
    }

}
