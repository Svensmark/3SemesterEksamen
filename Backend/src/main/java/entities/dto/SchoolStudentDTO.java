/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.dto;

import entities.SchoolStudent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emilt
 */
public class SchoolStudentDTO {

    //
    private Long id;
    private String name;
    private String email;

    //
    public SchoolStudentDTO(SchoolStudent st) {
        this.name = st.getName();
        this.email = st.getEmail();
        this.id = st.getId();
    }

    //
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
