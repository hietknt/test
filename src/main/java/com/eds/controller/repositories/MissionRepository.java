package com.eds.controller.repositories;

import com.eds.model.Employee;
import com.eds.model.Mission;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
@Slf4j
public class MissionRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @EJB
    private EmployeeRepository employeeRepository;

    //METHODS
    // GET FULL MISSION LIST
    public List<Mission> getMissionList(){
        String queryString = "SELECT m FROM Mission m";
        TypedQuery<Mission> query = entityManager.createQuery(queryString, Mission.class);
        List<Mission> list = query.getResultList();

        return list;
    }

    // GET ONE MISSION
    public Mission getMission(long missionID){
        String queryString = "SELECT m FROM Mission m WHERE id = " + missionID;
        TypedQuery<Mission> query = entityManager.createQuery(queryString, Mission.class);
        Mission mission = null;
        try {
            mission = query.getSingleResult();
        }catch (NoResultException ex){
            log.error("NoResultException");
        }

        return mission;
    }

    // GET MISSIONS TO ME
    public List<Mission> getMissionListToMe(long employeeId){
        String queryString = "SELECT m FROM Mission m join m.employees e WHERE e.id = " + employeeId;
        TypedQuery<Mission> query = entityManager.createQuery(queryString, Mission.class);
        List<Mission> list = query.getResultList();

        return list;
    }

    // GET MISSIONS BY ME
    public List<Mission> getMissionListByMe(long employeeId){
        String queryString = "SELECT m FROM Mission m WHERE author.id = " + employeeId;
        TypedQuery<Mission> query = entityManager.createQuery(queryString, Mission.class);
        List<Mission> list = query.getResultList();

        return list;
    }


    // ADD MISSION
    public boolean addMission(String title, String executionPeriod,
                              String controlAttribute, String executionAttribute,
                              String text, List<String> employees,
                              String author){
        //VALIDATION

        //VALIDATION

        Set<Employee> employeeSet = new HashSet<>();
        for (String id : employees) {
            employeeSet.add(employeeRepository.getEmployeeById(Long.valueOf(id)));
        }
        String[] dateSplit = executionPeriod.split("/");
        LocalDate date = LocalDate.of(
                Integer.valueOf(dateSplit[2]),
                Integer.valueOf(dateSplit[0]),
                Integer.valueOf(dateSplit[1])
        );

        Mission mission = new Mission();
        mission.setTitle(title);
        mission.setControlAttribute(controlAttribute);
        mission.setExecuteAttribute(executionAttribute);
        mission.setText(text);
        mission.setEmployees(employeeSet);
        mission.setLimitDateTime(date);
        mission.setAuthor(employeeRepository.getEmployeeById(Long.valueOf(author)));

        entityManager.persist(mission);
        return true;
    }

    // DELETE MISSION
    public int deleteMission(long id){
        String queryString = "DELETE FROM Mission m WHERE id = :id";
        Query query = entityManager.createQuery(queryString);
        int deletedCount = query.setParameter("id", id).executeUpdate();

        return deletedCount;
    }
}
