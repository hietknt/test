package com.eds.controller.repositories;

import com.eds.model.Employee;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@Slf4j
public class EmployeeRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @EJB
    private MissionRepository missionRepository;

    // GET EMPLOYEE LIST
    public List<Employee> getEmployeeList(){
        String queryString = "SELECT e FROM Employee e";
        TypedQuery<Employee> query = entityManager.createQuery(queryString, Employee.class);
        List<Employee> list = query.getResultList();

        return list;
    }

    // GET EMPLOYEE BY NAME
    public Employee getEmployeeByName(String fullName){
        String[] name = fullName.split(" ");

        String queryString = "SELECT e FROM Employee e WHERE secondName = " + name[0]
                + " AND firstName = " + name[1] + " AND middleName = " + name[2];
        TypedQuery<Employee> query = entityManager.createQuery(queryString, Employee.class);
        Employee employee = null;
        try {
            employee = query.getSingleResult();
        }catch (NoResultException ex){
            log.error("NoResultException");
        }

        return employee;
    }

    // GET EMPLOYEE BY ID
    public Employee getEmployeeById(long id){

        String queryString = "SELECT e FROM Employee e WHERE id = " + id;
        TypedQuery<Employee> query = entityManager.createQuery(queryString, Employee.class);
        Employee employee = null;
        try {
            employee = query.getSingleResult();
        }catch (NoResultException ex){
            log.error("NoResultException");
        }

        return employee;
    }
}
