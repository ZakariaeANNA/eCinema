package com.hendisantika.repository;

import java.util.Date;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.hendisantika.entity.Seance;

@CrossOrigin("http://localhost:4200")
@Repository
public interface SeanceRepository extends JpaRepository<Seance, Long> {

	Seance findByDateProjectionAndHeureDebutBetween(Date projectionDate,Date heureDebut,Date heureFin);
}
