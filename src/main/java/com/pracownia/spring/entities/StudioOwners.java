package com.pracownia.spring.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name="wl_table")
public class StudioOwners
{
    @Id
    @Column(name="id")
    @GeneratedValue(generator = "incrementator")
    @GenericGenerator(name= "incrementator", strategy= "increment")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    public StudioOwners(){}

    public StudioOwners(String name, String surname)
    {
        this.name = name;
        this.surname = surname;
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

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }


}
