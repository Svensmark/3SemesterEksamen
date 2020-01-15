/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.dto;

import entities.SchoolClass;
import entities.SchoolTeacher;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emilt
 */
public class SchoolTeacherDTO {
    
    //Fields
    Long id;
    private String name;
    private List<SchoolClassDTO> classes;
    
    
    //Con
    public SchoolTeacherDTO(SchoolTeacher st) {
        this.name = st.getName();
        this.id = st.getId();
        List<SchoolClass> sc = st.getClasses();
        for (int i = 0; i < sc.size(); ++i) {
            classes.add(new SchoolClassDTO(sc.get(i)));
        }
    }
    
    
    //Methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SchoolClassDTO> getClasses() {
        return classes;
    }

    public void setClasses(List<SchoolClassDTO> classes) {
        this.classes = classes;
    }
    
    
}
