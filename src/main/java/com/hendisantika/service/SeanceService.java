package com.hendisantika.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hendisantika.entity.Seance;
import com.hendisantika.repository.SeanceRepository;

@Service
public class SeanceService extends AbstractService<Seance, Long> {

    @Autowired
    private SeanceRepository seanceRepository;

    @Override
    protected JpaRepository<Seance, Long> getRepository() {
        return seanceRepository;
    }

}
