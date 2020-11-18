package com.eds.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private LocalDate limitDateTime;
    private String controlAttribute; // String? or 1 more entity like "attribute"
    private String executeAttribute;
    private String text;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "mission_employee",
            joinColumns = {@JoinColumn(name = "mission_id")},
            inverseJoinColumns = {@JoinColumn(name = "employee_id")}
    )
    private Set<Employee> employees;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee author;

    public Employee getAuthor() {
        return author;
    }

    public void setAuthor(Employee author) {
        this.author = author;
    }

    public Mission() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getLimitDateTime() {
        return limitDateTime;
    }

    public void setLimitDateTime(LocalDate localDate) {
        this.limitDateTime = localDate;
    }

    public String getControlAttribute() {
        return controlAttribute;
    }

    public void setControlAttribute(String controlAttribute) {
        this.controlAttribute = controlAttribute;
    }

    public String getExecuteAttribute() {
        return executeAttribute;
    }

    public void setExecuteAttribute(String executeAttribute) {
        this.executeAttribute = executeAttribute;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return
                "{" +
                    "\"id\":" + this.id + "," +
                    "\"title\":\"" + this.title + "\"," +
                    "\"limitDateTime\":\"" + this.limitDateTime + "\"," +
                    "\"controlAttribute\":\"" + this.controlAttribute + "\"," +
                    "\"executeAttribute\":\"" + this.executeAttribute + "\"," +
                    "\"text\":\"" + this.text + "\"," +
                    "\"author\":" + this.author + "," +
                    "\"employee\":" + this.employees +
                "}";
    }
}
