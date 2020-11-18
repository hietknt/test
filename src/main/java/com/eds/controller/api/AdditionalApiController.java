package com.eds.controller.api;

import com.eds.controller.repositories.EmployeeRepository;
import com.eds.controller.repositories.MissionRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/additional")
public class AdditionalApiController {

    @EJB
    private MissionRepository missionRepository;
    @EJB
    private EmployeeRepository employeeRepository;

    // CONTACTS
    @GET
    @Path("/contacts")
    public String getContactList(){
        return "GET contactList";
    }

    @GET
    @Path("/contacts/{contactid}")
    public String getContact(@PathParam("contactid") long contactId){
        return "GET contact with id=" + contactId;
    }

    @GET
    @Path("/contacts/test")
    public String getTest(){
        return missionRepository.getMissionListByMe(2).toString();
    }
    @GET
    @Path("/contacts/test2")
    public String getTest2(){
        return missionRepository.getMissionListToMe(2).toString();
    }

    @POST
    @Path("/contacts")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addContact(){
        return "GET contact with id=";
    }

    // EMPLOYEE
    @GET
    @Path("/employee")
    @Produces("application/json")
    public String getEmployeeList(){
        return employeeRepository.getEmployeeList().toString();
    }
}
