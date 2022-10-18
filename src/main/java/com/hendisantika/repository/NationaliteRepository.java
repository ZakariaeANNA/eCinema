package com.hendisantika.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import com.hendisantika.entity.Nationalite;

@CrossOrigin("http://localhost:4200")
@Repository
public interface NationaliteRepository extends JpaRepository<Nationalite, Long> {
	Page<Nationalite> findByLibelleStartsWith(@RequestParam("libelle") String libelle, Pageable pageable);
}
