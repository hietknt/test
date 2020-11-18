package com.eds;

import com.eds.model.*;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Singleton
@Startup
public class PreloadDataBaseForTests {

    @PersistenceContext
    EntityManager entityManager;

    @PostConstruct
    public void preload(){
        // Posts - Employee
        Employee employee1 = new Employee();
        Post dev = new Post();

        employee1.setFirstName("Leonid");
        employee1.setMiddleName("Andreevich");
        employee1.setSecondName("Verenev");
        employee1.setPost(dev);

        dev.setTitle("Developer");
        dev.setEmployee(employee1);

        entityManager.persist(dev);


        Employee employee2 = new Employee();
        Post hr = new Post();

        employee2.setFirstName("n1");
        employee2.setMiddleName("m1");
        employee2.setSecondName("s1");
        employee2.setPost(hr);

        hr.setTitle("HR");
        hr.setEmployee(employee2);

        entityManager.persist(hr);

        entityManager.persist(employee1);
        entityManager.persist(employee2);

        //

        Subdivision subdivision1 = new Subdivision();
        Contact contact1 = new Contact();
        Contact contact2 = new Contact();
        Set<Contact> contactList = new HashSet<>();
        contactList.add(contact1);
        contactList.add(contact2);

        subdivision1.setTitle("SUBDIV1");
        subdivision1.setLeader(employee1);
        subdivision1.setContacts(contactList);

        contact1.setType("Number");
        contact1.setContact("+7777777777");
        contact1.setSubdivision(subdivision1);

        contact2.setType("Address");
        contact2.setContact("SomeAddress");
        contact2.setSubdivision(subdivision1);

        entityManager.persist(subdivision1);
        entityManager.persist(contact1);
        entityManager.persist(contact2);

        //

        Organization organization = new Organization();
        organization.setLeader(employee2);
        organization.setTitle("Title");
        organization.setLegalAddress("LegalAddress");
        organization.setPhysicalAddress("PhysicalAddress");

        entityManager.persist(organization);

        //
        Employee employee3 = new Employee();

        employee3.setFirstName("n2");
        employee3.setMiddleName("m2");
        employee3.setSecondName("s2");
        employee3.setPost(dev);

        Employee employee4 = new Employee();

        employee4.setFirstName("n4");
        employee4.setMiddleName("m4");
        employee4.setSecondName("s4");
        employee4.setPost(dev);


        Set set2 = new HashSet();
        set2.add(employee3);
        set2.add(employee4);

        Mission mission1 = new Mission();
        mission1.setTitle("Title1");
        mission1.setControlAttribute("ControlAttribute");
        mission1.setExecuteAttribute("ExecAttribute");
        mission1.setText("Text");
        mission1.setEmployees(set2);
        mission1.setLimitDateTime(LocalDate.of(2020,1,1));
        mission1.setAuthor(employee1);

        Mission mission2 = new Mission();
        mission2.setTitle("Title2");
        mission2.setControlAttribute("ControlAttribute");
        mission2.setExecuteAttribute("ExecAttribute");
        mission2.setText("Text");
        mission2.setEmployees(set2);
        mission2.setLimitDateTime(LocalDate.of(2020,1,1));
        mission2.setAuthor(employee1);

        entityManager.persist(mission1);
        entityManager.persist(mission2);

        employee3.setMissions(set2);
        employee4.setMissions(set2);

        entityManager.persist(employee3);
        entityManager.persist(employee4);
    }


}
