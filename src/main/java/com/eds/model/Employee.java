package com.eds.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String secondName;
    private String middleName;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    private Post post;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "leader")
    private Subdivision subdivision;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "leader")
    private Organization organization;

    @ManyToMany(mappedBy = "employees")
    private Set<Mission> missions;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "author")
    private Mission mission;

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public Employee() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Subdivision getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(Subdivision subdivision) {
        this.subdivision = subdivision;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Set<Mission> getMissions() {
        return missions;
    }

    public void setMissions(Set<Mission> missions) {
        this.missions = missions;
    }

    @Override
    public String toString() {
        return "{" +
                    "\"id\":" + this.id + "," +
                    "\"firstname\":\"" + this.firstName + "\"," +
                    "\"secondname\":\"" + this.secondName + "\"," +
                    "\"middlename\":\"" + this.middleName + "\"," +
                    "\"post:\":" + this.post.toString() +
                "}";
    }
}
