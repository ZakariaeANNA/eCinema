package com.hendisantika.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hendisantika.entity.Genre;
import com.hendisantika.repository.GenreRepository;

@Service
public class GenreService extends AbstractService<Genre, Long> {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    protected JpaRepository<Genre, Long> getRepository() {
        return genreRepository;
    }

}
