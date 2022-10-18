package com.hendisantika.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.hendisantika.entity.Genre;


@CrossOrigin("http://localhost:4200")
@Repository
@RepositoryRestResource
public interface GenreRepository extends JpaRepository<Genre, Long> {

}
