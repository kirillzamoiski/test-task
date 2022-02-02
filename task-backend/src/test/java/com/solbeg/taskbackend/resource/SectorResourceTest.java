package com.solbeg.taskbackend.resource;

import com.solbeg.taskbackend.model.Sector;
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

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class SectorResourceTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private SectorService service;

    private List<Sector> sectors;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .dispatchOptions(true)
                .build();

        this.sectors = new ArrayList<>();
        this.sectors = buildSectorsList();
    }

    @Test
    void findAllTest() throws Exception {

        given(service.findAll()).willReturn(sectors);

        this.mockMvc.perform(get("/api/sectors")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(sectors.size())));
    }

    private List<Sector> buildSectorsList() {

        List<Sector> sectorList = new ArrayList<>();

        sectorList.add(Sector.builder()
                .id(1L)
                .name("Wood")
                .parentId(2L)
                .build());

        return  sectorList;
    }
}
