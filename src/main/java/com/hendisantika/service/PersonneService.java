package com.hendisantika.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hendisantika.entity.Personne;
import com.hendisantika.entity.Personne.TypePersonne;
import com.hendisantika.repository.PersonneRepository;

@Service
public class PersonneService extends AbstractService<Personne, Long> {

    @Autowired
    private PersonneRepository personneRepository;

    @Override
    protected JpaRepository<Personne, Long> getRepository() {
        return personneRepository;
    }
    
    public List<Personne> getRealisateurs(){
    	return personneRepository.findByTypePersonneIs(TypePersonne.REALISATEUR);
    }
    
    public List<Personne> getActeurs(){
    	return personneRepository.findByTypePersonneIs(TypePersonne.ACTEUR);
    }

}
