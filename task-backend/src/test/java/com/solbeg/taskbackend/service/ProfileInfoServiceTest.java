package com.solbeg.taskbackend.service;

import com.solbeg.taskbackend.dto.ProfileInfoRequest;
import com.solbeg.taskbackend.model.ProfileInfo;
import com.solbeg.taskbackend.repository.ProfileInfoRepository;
import com.solbeg.taskbackend.transformer.ProfileInfoTransformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProfileInfoServiceTest {

    @Mock
    private ProfileInfoRepository repository;

    @Mock
    private ProfileInfoTransformer transformer;

    @InjectMocks
    private ProfileInfoService service;

    @BeforeEach
    public void setUp() {
        Mockito.reset(repository, transformer);
    }

    @Test
    void findByIdTest() {

        ProfileInfo profileInfo = buildProfileInfo();

        when(repository.findById(profileInfo.getId())).thenReturn(Optional.of(profileInfo));

        ProfileInfo returnedProfileInfo = service.findByIdOrThrowException(profileInfo.getId());

        assertThat(returnedProfileInfo, equalTo(profileInfo));
    }

    @Test
    void saveTest() {

        ProfileInfoRequest request = buildRequest();
        ProfileInfo profileInfo = buildProfileInfo();

        when(transformer.transform(request)).thenReturn(profileInfo);
        when(repository.save(profileInfo)).thenReturn(profileInfo);

        ProfileInfo returnedProfileInfo = service.save(request);

        assertThat(returnedProfileInfo, equalTo(profileInfo));
    }

    @Test
    void updateTest() {

        ProfileInfoRequest request = buildRequest();
        ProfileInfo profileInfo = buildProfileInfo();

        when(repository.findById(profileInfo.getId())).thenReturn(Optional.of(profileInfo));
        when(transformer.transform(request)).thenReturn(profileInfo);
        when(repository.save(profileInfo)).thenReturn(profileInfo);

        ProfileInfo returnedProfileInfo = service.update(profileInfo.getId(), request);

        assertThat(returnedProfileInfo, equalTo(profileInfo));
    }


    private ProfileInfo buildProfileInfo() {
        return ProfileInfo.builder()
                .id(1L)
                .name("Alex")
                .sector("Wood")
                .agreeToTerms(true)
                .build();
    }

    private ProfileInfoRequest buildRequest() {
        return ProfileInfoRequest.builder()
                .name("Alex")
                .sector("Wood")
                .agreeToTerms(true)
                .build();
    }
}
