package com.eds.model;


import javax.persistence.*;

@Entity
public class Contact{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String type;
    private String contact;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subdivision_id", referencedColumnName = "id")
    private Subdivision subdivision;

    public Contact() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Subdivision getSubdivision() {
        return subdivision;
    }

    public void setSubdivision(Subdivision subdivision) {
        this.subdivision = subdivision;
    }
}
