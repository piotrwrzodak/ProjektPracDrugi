package com.pracownia.spring.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="studio_table")
public class Studios
{
    @Id
    @Column(name="id")
    @GeneratedValue(generator = "incrementator")
    @GenericGenerator(name= "incrementator", strategy= "increment")
    private int id;

    @Column
    private String studioId;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;

    @OneToMany
    @JoinColumn(name = "studio_id")
    private Set<Films> filmy = new HashSet<Films>();

    @OneToOne
    @JoinColumn(name="owner_id")
    private StudioOwners studioOwners;

    @OneToOne
    @JoinColumn(name="ceo_id")
    private Ceos ceo;

    public Studios(){}

    public Studios(String studioId, String name, String address)
    {
        this.studioId = studioId;
        this.name = name;
        this.address = address;
    }


    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public Set<Films> getFilmy() {
        return filmy;
    }

    public void setFilmy(Set<Films> filmy) {
        this.filmy = filmy;
    }

    public StudioOwners getStudioOwners() {
        return studioOwners;
    }

    public void setStudioOwners(StudioOwners studioOwners) {
        this.studioOwners = studioOwners;
    }

    public Ceos getCeo() {
        return ceo;
    }

    public void setCeo(Ceos ceo) {
        this.ceo = ceo;
    }

    public String getStudioId() {
        return studioId;
    }

    public void setStudioId(String studioId) {
        this.studioId = studioId;
    }
}
