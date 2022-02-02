package com.solbeg.taskbackend.resource;

import com.solbeg.taskbackend.model.Sector;
import com.solbeg.taskbackend.service.SectorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.*;

@RestController
@RequestMapping(path = "/api/sectors")
@RequiredArgsConstructor
@CrossOrigin
public class SectorResource {

    private final SectorService service;

    @GetMapping
    public ResponseEntity<List<Sector>> findAll() {
        return ok(service.findAll());
    }
}
