package com.eds.controller.api;

import com.eds.controller.repositories.EmployeeRepository;
import com.eds.controller.repositories.MissionRepository;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

@Path("/api/missions")
@Slf4j
public class ApiController {

    @EJB
    private MissionRepository missionRepository;
    @EJB
    private EmployeeRepository employeeRepository;

    @GET
    @Produces("application/json")
    public String getMissionList(){
        return missionRepository.getMissionList().toString();
    }

    @GET
    @Path("/tome/{employeeId}")
    @Produces("application/json")
    public String getMissionListToMe(@PathParam("employeeId") long employeeId){
        return missionRepository.getMissionListToMe(employeeId).toString();
    }

    @GET
    @Path("/byme/{employeeId}")
    @Produces("application/json")
    public String getMissionListByMe(@PathParam("employeeId") long employeeId){
        return missionRepository.getMissionListByMe(employeeId).toString();
    }

    @GET
    @Path("/{missionid}")
    public String getMission(@PathParam("missionid") long missionId){

        return missionRepository.getMission(missionId).toString();
    }

    //ADD
    @POST
    public String addMission(@FormParam("title") String title,
                             @FormParam("executionPeriod") String executionPeriod,
                             @FormParam("controlAttribute") String controlAttribute,
                             @FormParam("executionAttribute") String executionAttribute,
                             @FormParam("text") String text,
                             @FormParam("employees") List<String> employees,
                             @FormParam("author") String author){

        boolean result = missionRepository.addMission(title, executionPeriod, controlAttribute , executionAttribute, text,
                employees, author);
        return result == true ? "{\"status\":\"success\", \"code\":200}" : "{\"status\":\"error\", \"code\":404}"; // example
    }

    //EDIT
    @PUT
    @Path("/{missionid}")
    public String editMission(@PathParam("missionid") long missionId,
                              @FormParam("title") String title,
                              @FormParam("executionPeriod") String executionPeriod,
                              @FormParam("controlAttribute") String controlAttribute,
                              @FormParam("executionAttribute") String executionAttribute,
                              @FormParam("text") String text,
                              @FormParam("employees") List<String> employees,
                              @FormParam("author") String author){



        return "putAPi " + missionId;
    }

    @DELETE
    @Path("/{missionid}")
    public String removeMission(@PathParam("missionid") long missionId){
        int deletedCount = missionRepository.deleteMission(missionId);
        return deletedCount == 0
                ? "{\"status\":\"error\", \"code\":404, \"deletedCount\":" + deletedCount +"}"
                : "{\"status\":\"success\", \"code\":200, \"deletedCount\":" + deletedCount +"}";
    }

}
