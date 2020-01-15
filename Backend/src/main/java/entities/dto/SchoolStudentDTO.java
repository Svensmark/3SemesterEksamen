/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.dto;

import entities.SchoolStudent;
import java.util.List;

/**
 *
 * @author emilt
 */
public class SchoolStudentDTO {
    
    //
    private String name;
    private String email;
    private List<SchoolSignedUpDTO> sudto;
    
    
    //
    public SchoolStudentDTO(SchoolStudent st) {
        this.name = st.getName();
        this.email = st.getEmail();
        for (int i = 0; i < st.getSignups().size(); ++i) {
            sudto.add(new SchoolSignedUpDTO(st.getSignups().get(i)));
        }
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

    public List<SchoolSignedUpDTO> getSudto() {
        return sudto;
    }

    public void setSudto(List<SchoolSignedUpDTO> sudto) {
        this.sudto = sudto;
    }

    
    
}
