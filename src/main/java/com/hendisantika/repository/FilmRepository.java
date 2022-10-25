package com.hendisantika.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.hendisantika.entity.Film;


@CrossOrigin("http://localhost:4200")
@Repository
@RepositoryRestResource
public interface FilmRepository extends JpaRepository<Film, Long> {
	List<Film> findByOrderByAddedDateDesc();

}
