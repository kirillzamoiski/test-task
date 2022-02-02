package com.solbeg.taskbackend.transformer;

import com.solbeg.taskbackend.dto.ProfileInfoRequest;
import com.solbeg.taskbackend.model.ProfileInfo;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProfileInfoTransformer {

    public ProfileInfo transform(final ProfileInfoRequest request) {
        return ProfileInfo.builder()
                .name(request.getName())
                .sector(request.getSector())
                .agreeToTerms(request.getAgreeToTerms())
                .build();
    }

    public void transform(final ProfileInfoRequest source, final ProfileInfo destination) {
        Optional.ofNullable(source.getName()).ifPresent(destination::setName);
        Optional.ofNullable(source.getSector()).ifPresent(destination::setSector);
        Optional.ofNullable(source.getAgreeToTerms()).ifPresent(destination::setAgreeToTerms);
    }
}
