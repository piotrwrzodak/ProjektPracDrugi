package com.pracownia.spring.services;

import com.pracownia.spring.entities.Films;

import java.util.List;
import java.util.Optional;

public interface FilmService
{
    Films saveFilm(Films films);

    Iterable<Films> listAllFilms();

    Optional<Films> getFilmById(Integer id);



    void deleteFilm(Integer id);

    List<Films> getByName(String name);

    Integer getNumberOfFilms(Integer id);

}
