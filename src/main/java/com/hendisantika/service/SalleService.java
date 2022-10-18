package com.hendisantika.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.hendisantika.entity.Salle;
import com.hendisantika.repository.SalleRepository;

@Service
public class SalleService extends AbstractService<Salle, Long> {

    @Autowired
    private SalleRepository salleRepository;

    @Override
    protected JpaRepository<Salle, Long> getRepository() {
        return salleRepository;
    }
    
    public Page<Salle> findPaginated(int page, int size) {
        return salleRepository.findAll(PageRequest.of(page, size, Sort.by("capacite", "numero").descending()));
    }

}
