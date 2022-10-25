package com.hendisantika.repository;


import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.hendisantika.entity.Personne;
import com.hendisantika.entity.Personne.TypePersonne;


@CrossOrigin("http://localhost:4200")
@Repository
///@RepositoryRestResource(excerptProjection = InlinePersonne.class)
public interface PersonneRepository extends JpaRepository<Personne, Long> {
	Page<Personne> findByTypePersonne(@RequestParam("typePersonne") TypePersonne typePersonne, Pageable pageable);
	Page<Personne> findByDateNaissanceGreaterThanEqual(@RequestParam("dateNs") Date dateNs, Pageable pageable);
	Page<Personne> findByNomContainingOrPrenomContaining(@RequestParam("nom") String nom,@RequestParam("prenom") String prenom, Pageable pageable);
	List<Personne> findByTypePersonneIs(TypePersonne typePersonne);
}
