package com.pracownia.spring.services;

import com.pracownia.spring.entities.Films;
import com.pracownia.spring.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService
{
    @Autowired
    private FilmRepository filmRepository;



    @Override
    public Iterable<Films> listAllFilms() {
        return filmRepository.findAll();
    }

    @Override
    public Optional<Films> getFilmById(Integer id) {
        return filmRepository.findById(id);
    }

    @Override
    public Films saveFilm(Films films) {
        return filmRepository.save(films);
    }

    @Override
    public void deleteFilm(Integer id) {
        filmRepository.deleteById(id);
    }

    @Override
    public List<Films> getByName(String title) {
        return filmRepository.findByTitle(title);
    }

    @Override
    public Integer getNumberOfFilms(Integer id) {
        return filmRepository.countFilmsById(id);
    }

}
