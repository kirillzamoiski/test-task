package com.solbeg.taskbackend.service;

import com.solbeg.taskbackend.dto.ProfileInfoRequest;
import com.solbeg.taskbackend.exception.ResourceNotFoundException;
import com.solbeg.taskbackend.model.ProfileInfo;
import com.solbeg.taskbackend.repository.ProfileInfoRepository;
import com.solbeg.taskbackend.transformer.ProfileInfoTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileInfoService {

    private final ProfileInfoRepository repository;
    private final ProfileInfoTransformer transformer;

    public ProfileInfo save(ProfileInfoRequest request) {

        ProfileInfo profileInfo = transformer.transform(request);
        return repository.save(profileInfo);
    }

    public ProfileInfo update(Long id, ProfileInfoRequest request) {

        ProfileInfo profileInfo = findByIdOrThrowException(id);
        transformer.transform(request, profileInfo);

        return repository.save(profileInfo);
    }

    public ProfileInfo findByIdOrThrowException(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString()));
    }
}
