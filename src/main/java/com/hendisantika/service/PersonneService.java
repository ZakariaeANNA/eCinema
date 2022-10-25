package com.hendisantika.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hendisantika.entity.Film;
import com.hendisantika.entity.Personne;
import com.hendisantika.entity.Personne.TypePersonne;
import com.hendisantika.repository.FilmRepository;
import com.hendisantika.repository.PersonneRepository;

@Service
public class PersonneService extends AbstractService<Personne, Long> {

	@Autowired
	private PersonneRepository personneRepository;
	@Autowired
	private FilmRepository filmRepository;

	public JpaRepository<Film, Long> getFilmRepository() {
		return filmRepository;
	}

	@Override
	protected JpaRepository<Personne, Long> getRepository() {
		return personneRepository;
	}

	public List<Personne> getRealisateurs() {
		return personneRepository.findByTypePersonneIs(TypePersonne.REALISATEUR);
	}

	public List<Personne> getActeurs() {
		return personneRepository.findByTypePersonneIs(TypePersonne.ACTEUR);
	}

	public void deleteFilmPersonne(long id, long idperson) {
		Optional<Personne> personne = personneRepository.findById(idperson);
		Optional<Film> film = filmRepository.findById(id);
		List<Personne> personnes = new ArrayList<Personne>();
		if (personne.get().getTypePersonne().equals(TypePersonne.ACTEUR)) {
			film.get().getActeurs().stream().filter(u -> u.getId() != idperson).forEach(user -> {
				personnes.add(user);
			});
			film.get().setActeurs(personnes);
			filmRepository.save(film.get());
		}
	}

}
