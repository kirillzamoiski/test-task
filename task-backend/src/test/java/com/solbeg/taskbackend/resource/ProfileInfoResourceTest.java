package com.solbeg.taskbackend.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solbeg.taskbackend.dto.ProfileInfoRequest;
import com.solbeg.taskbackend.model.ProfileInfo;
import com.solbeg.taskbackend.service.ProfileInfoService;
import com.solbeg.taskbackend.service.SectorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ProfileInfoResourceTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private ProfileInfoService service;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .dispatchOptions(true)
                .build();
    }

    @Test
    void saveTest() throws Exception {

        ProfileInfoRequest request = buildRequest();
        ProfileInfo profileInfo = buildProfileInfo();

        given(service.save(any(ProfileInfoRequest.class))).willReturn(profileInfo);

        this.mockMvc.perform(post("/api/profile-info")
                        .content(convertObjectToString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(profileInfo.getName()))
                .andExpect(jsonPath("$.sector").value(profileInfo.getSector()));
    }


    @Test
    void updateTest() throws Exception {

        ProfileInfoRequest request = buildRequest();
        ProfileInfo profileInfo = buildProfileInfo();

        when(service.update(anyLong(), any(ProfileInfoRequest.class))).thenReturn(profileInfo);

        this.mockMvc.perform(put("/api/profile-info/" + Long.valueOf(2L).toString())
                        .content(convertObjectToString(request))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value(profileInfo.getName()))
                .andExpect(jsonPath("$.sector").value(profileInfo.getSector()));

    }

    private String convertObjectToString(final Object obj) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
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
