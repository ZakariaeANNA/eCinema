package com.hendisantika.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hendisantika.entity.Film;
import com.hendisantika.entity.Personne;
import com.hendisantika.repository.FilmRepository;

@Service
public class FilmService extends AbstractService<Film, Long> {

    @Autowired
    private FilmRepository filmRepository;

    @Override
    protected JpaRepository<Film, Long> getRepository() {
        return filmRepository;
    }
    
    public void deleteActorFromFilm(long idActor,long idFilm) {
    	Optional<Film> film = filmRepository.findById(idFilm);
    	List<Personne> users = new ArrayList<Personne>();
    	film.get().getActeurs().stream()
    		.filter(u -> u.getId() != idActor)
    		.forEach(user ->{
    			users.add(user);
    		});
        film.get().setActeurs(users);
        filmRepository.save(film.get());
    }
    public List<Film> getBytitre() {
        return filmRepository.findByOrderByAddedDateDesc();
    }

}
