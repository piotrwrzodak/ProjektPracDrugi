package com.pracownia.spring.entities;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="films_table")
public class Films
{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String filmId;

    @Column(name="title")
    private String title;

    @Column(name="year")
    private Integer year;

    @Column(name="runtime")
    private Integer runtime;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Actors> actors = new HashSet<>();


    public Films(){}

    public Films(String filmId, String title, Integer year, Integer runtime)
    {
        this.filmId = filmId;
        this.title = title;
        this.year = year;
        this.runtime = runtime;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return title;
    }

    public void setName(String title)
    {
        this.title = title;
    }

    public Integer getYear()
    {
        return year;
    }

    public void setYear(Integer year)
    {
        this.year = year;
    }

    public Integer getRuntime()
    {
        return runtime;
    }

    public void setRuntime(Integer runtime)
    {
        this.runtime = runtime;
    }

    public Set<Actors> getActors() { return actors; }

    public void setActors(Set<Actors> actors) { this.actors = actors; }

    public String getFilmId() { return filmId; }

    public void setFilmId(String filmId) { this.filmId = filmId; }
}

