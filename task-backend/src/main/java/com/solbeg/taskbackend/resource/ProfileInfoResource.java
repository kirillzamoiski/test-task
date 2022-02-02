package com.solbeg.taskbackend.resource;

import com.solbeg.taskbackend.dto.ProfileInfoRequest;
import com.solbeg.taskbackend.model.ProfileInfo;
import com.solbeg.taskbackend.service.ProfileInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/profile-info")
@CrossOrigin
public class ProfileInfoResource {

    private final ProfileInfoService service;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProfileInfo save(@RequestBody final ProfileInfoRequest request) {
        return service.save(request);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<ProfileInfo> update(@PathVariable final Long id, @RequestBody final ProfileInfoRequest request) {
        return ok(service.update(id, request));
    }
}
