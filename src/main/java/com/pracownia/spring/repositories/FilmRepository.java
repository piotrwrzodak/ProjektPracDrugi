package com.pracownia.spring.repositories;

import com.pracownia.spring.entities.Films;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FilmRepository extends CrudRepository<Films, Integer>
{
    List<Films> findByTitle(String title);

    @Query("select count(*) from Films p where p.id = ?1")
    Integer countFilmsById(Integer id);


}
