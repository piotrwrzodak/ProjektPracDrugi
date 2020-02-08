package com.pracownia.spring.controllers;

import com.pracownia.spring.entities.Films;
import com.pracownia.spring.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FilmController
{
    @Autowired
    private FilmService filmService;

    @RequestMapping(value = "/films", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Films> list(Films films) {
        return filmService.listAllFilms();
    }

    // Only for redirect!
    @ApiIgnore
    @RequestMapping(value = "/films", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Films> redirect(Films films) {
        return filmService.listAllFilms();
    }

    @RequestMapping(value = "/films/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_ATOM_XML_VALUE)
    @ResponseBody
    public Optional<Films> getByPublicId(@PathVariable("id") Integer publicId) {
        return filmService.getFilmById(publicId);
    }

    @RequestMapping(value = "/film", method = RequestMethod.POST)
    public ResponseEntity<Films> create(@RequestBody @Valid @NotNull Films films) {
        filmService.saveFilm(films);
        return ResponseEntity.ok().body(films);
    }

    @RequestMapping(value = "/film", method = RequestMethod.PUT)
    public ResponseEntity<Void> edit(@RequestBody @Valid @NotNull Films films) {
        Optional<Films> sellerFromData = filmService.getFilmById(films.getId());
        if(Objects.nonNull(sellerFromData)) {
            filmService.saveFilm(films);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/film/{id}", method = RequestMethod.DELETE)
    public RedirectView delete(HttpServletResponse response, @PathVariable Integer id) {
        filmService.deleteFilm(id);
        return new RedirectView("/api/sellers", true);
    }

    @RequestMapping(value = "/film/{name}", method = RequestMethod.GET)
    public List<Films> getByName(@PathVariable String name) {
        return filmService.getByName(name);
    }
}
