package com.solbeg.taskbackend.service;

import com.solbeg.taskbackend.model.Sector;
import com.solbeg.taskbackend.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectorService {

    private final SectorRepository repository;

    public List<Sector> findAll() {
        return repository.findAll();
    }
}
