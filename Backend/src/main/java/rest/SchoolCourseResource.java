/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entities.dto.SchoolCourseDTO;
import facades.SchoolCourseFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 * REST Web Service
 *
 * @author emilt
 */
@Path("SchoolCourse")
public class SchoolCourseResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
            "pu",
            "jdbc:mysql://localhost:3307/eksamen1",
            "dev",
            "ax2",
            EMF_Creator.Strategy.CREATE);
    
    private static final SchoolCourseFacade FACADE =  SchoolCourseFacade.getSchoolCourseFacade(EMF);

    public SchoolCourseResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        return "{\"msg\": \"School Course\"}";
    }
    
    
    @Path("all")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<SchoolCourseDTO> getAllCourses() {
        return FACADE.getAllCourses();
    }
    
    @Path("populate")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        FACADE.populate();
        return "{\"msg\": \"Database populated\"}";
    }
    
}
