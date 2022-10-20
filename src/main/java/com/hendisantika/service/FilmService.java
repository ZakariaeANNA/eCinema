package com.hendisantika.service;


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
    	List<Personne> users = (List<Personne>) film.get().getActeurs().stream().filter(u -> u.getId() != idActor);    	
        film.get().setActeurs(users);
        filmRepository.save(film.get());
//    	group.getUsers().forEach(u -> u.getGroups().remove(group));
//        userRepository.saveAll(group.getUsers());
//        groupRepository.delete(group);
    }

}
