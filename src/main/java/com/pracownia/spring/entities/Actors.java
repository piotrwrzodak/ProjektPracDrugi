package com.pracownia.spring.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Actors
{
    @Id
    @GeneratedValue(generator = "gen")
    @SequenceGenerator(name = "gen", sequenceName = "seller_seq")
    @Column(name = "id")
    private int id;

    @Column
    private String name;

    @Column
    private String surname;

    @ElementCollection
    @CollectionTable(name = "films")
    @Column(name = "film_id")
    private List<String> films = new ArrayList<>();

    @ManyToMany(mappedBy = "actors")
    private List<Films> filmsOb;

    //required by Hibernate
    public Actors() {}

    public Actors(String name, String surname)
    {
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) { this.surname = surname; }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }
}

