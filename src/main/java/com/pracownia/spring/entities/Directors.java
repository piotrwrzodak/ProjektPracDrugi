package com.pracownia.spring.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name="rezyser_table")
public class Directors
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

    @Column(name="salary")
    private int salary;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @OneToMany
    @JoinColumn(name = "director_id")
    private Set<Films> filmy = new HashSet<Films>();

    public Directors(){}

    public Directors(String name, String surname, int salary)
    {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }

    public void setSurname(String surname) { this.surname = surname; }

    public int getSalary() { return salary; }

    public void setSalary(int salary) { this.salary = salary; }

    public void setDate(Date dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public Date getDate() { return dateOfBirth; }

    public Set<Films> getFilmy() { return filmy; }

    public void setFilmy(Set<Films> filmy) { this.filmy = filmy; }

}
