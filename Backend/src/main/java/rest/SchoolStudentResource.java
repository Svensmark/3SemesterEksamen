/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entities.dto.SchoolStudentCourseDTO;
import facades.SchoolStudentFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author emilt
 */
@Path("SchoolStudent")
public class SchoolStudentResource {

    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/eksamen1",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    
    private static final SchoolStudentFacade FACADE =  SchoolStudentFacade.getSchoolStudentFacade(EMF);

    
    
    public SchoolStudentResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "{\"msg\": \"Students\"}";
    }
    
    @Path("name/{name}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<SchoolStudentCourseDTO> getAllCourses(@PathParam("name") String name) {
        return FACADE.getStudentCourses(FACADE.getStudent(name).getId());
    }
}
