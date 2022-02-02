package com.solbeg.taskbackend.service;

import com.solbeg.taskbackend.model.Sector;
import com.solbeg.taskbackend.repository.SectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SectorServiceTest {

    @Mock
    private SectorRepository repository;

    @InjectMocks
    private SectorService service;

    @BeforeEach
    public void setUp() {
        Mockito.reset(repository);
    }

    @Test
    void findAllTest() {

        Sector sector = buildSector();

        List<Sector> sectors = new ArrayList<>();
        sectors.add(sector);

        when(repository.findAll()).thenReturn(sectors);

        List<Sector> returnedSectors = service.findAll();

        assertThat(returnedSectors, equalTo(sectors));
    }

    private Sector buildSector() {
        return Sector.builder()
                .id(1L)
                .name("Wood")
                .parentId(2L)
                .build();
    }
}
