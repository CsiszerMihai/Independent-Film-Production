package com.film_production.demo.integration_tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_production.demo.models.dtos.FilmProductionDTO;
import com.film_production.demo.models.entities.FilmProduction;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@Transactional
@AutoConfigureTestDatabase
public class FilmProductionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateFilmProductionShouldReturnCreated() throws Exception {
        FilmProductionDTO filmProductionDTO;
        FilmProduction filmProduction;

        filmProductionDTO = new FilmProductionDTO();
        filmProductionDTO.setTitle("Test Title.");
        filmProductionDTO.setDescription("Test Description Lorem Ipsum");
        filmProductionDTO.setStartDate(new Date(2025 - 2 - 24));
        filmProductionDTO.setEndDate(new Date(2025 - 3 - 24));
        filmProductionDTO.setStatus("Test Status");
        filmProductionDTO.setDirector("Test Director");
        filmProductionDTO.setBudget(12000.00);

        filmProduction = new FilmProduction();
        filmProduction.setTitle("Test Title.");
        filmProduction.setDescription("Test Description Lorem Ipsum");
        filmProduction.setStartDate(new Date(2025 - 2 - 24));
        filmProduction.setEndDate(new Date(2025 - 3 - 24));
        filmProduction.setStatus("Test Status");
        filmProduction.setDirector("Test Director");
        filmProduction.setBudget(12000.00);

        mockMvc.perform(post("/api/film-productions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmProductionDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetFilmProductionShouldReturnOk() throws Exception {
        FilmProductionDTO filmProductionDTO;
        FilmProduction filmProduction;

        filmProductionDTO = new FilmProductionDTO();
        filmProductionDTO.setTitle("Test Title.");
        filmProductionDTO.setDescription("Test Description Lorem Ipsum");
        filmProductionDTO.setStartDate(new Date(2025 - 2 - 24));
        filmProductionDTO.setEndDate(new Date(2025 - 3 - 24));
        filmProductionDTO.setStatus("Test Status");
        filmProductionDTO.setDirector("Test Director");
        filmProductionDTO.setBudget(12000.00);

        filmProduction = new FilmProduction();
        filmProduction.setTitle("Test Title.");
        filmProduction.setDescription("Test Description Lorem Ipsum");
        filmProduction.setStartDate(new Date(2025 - 2 - 24));
        filmProduction.setEndDate(new Date(2025 - 3 - 24));
        filmProduction.setStatus("Test Status");
        filmProduction.setDirector("Test Director");
        filmProduction.setBudget(12000.00);

        mockMvc.perform(post("/api/film-productions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmProductionDTO)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/film-productions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateFilmProductionShouldReturnOk() throws Exception {
        FilmProductionDTO filmProductionDTO = new FilmProductionDTO();
        filmProductionDTO.setTitle("Test Title.");
        filmProductionDTO.setDescription("Test Description Lorem Ipsum");
        filmProductionDTO.setStartDate(new Date(2025 - 2 - 24));
        filmProductionDTO.setEndDate(new Date(2025 - 3 - 24));
        filmProductionDTO.setStatus("Test Status");
        filmProductionDTO.setDirector("Test Director");
        filmProductionDTO.setBudget(12000.00);

        FilmProduction filmProduction = new FilmProduction();
        filmProduction.setId(1L);
        filmProduction.setTitle("Test Title.");
        filmProduction.setDescription("Test Description Lorem Ipsum");
        filmProduction.setStartDate(new Date(2025 - 2 - 24));
        filmProduction.setEndDate(new Date(2025 - 3 - 24));
        filmProduction.setStatus("Test Status");
        filmProduction.setDirector("Test Director");
        filmProduction.setBudget(12000.00);

        MvcResult result = mockMvc.perform(post("/api/film-productions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmProductionDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        String locationHeader = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(locationHeader);
        Long id = jsonNode.get("id").asLong();

        filmProductionDTO.setTitle("Updated Title");
        mockMvc.perform(put("/api/film-productions/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmProductionDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteFilmProductionShouldReturnNoContent() throws Exception {
        FilmProductionDTO filmProductionDTO;
        FilmProduction filmProduction;

        filmProductionDTO = new FilmProductionDTO();
        filmProductionDTO.setId(1L);
        filmProductionDTO.setTitle("Test Title.");
        filmProductionDTO.setDescription("Test Description Lorem Ipsum");
        filmProductionDTO.setStartDate(new Date(2025 - 2 - 24));
        filmProductionDTO.setEndDate(new Date(2025 - 3 - 24));
        filmProductionDTO.setStatus("Test Status");
        filmProductionDTO.setDirector("Test Director");
        filmProductionDTO.setBudget(12000.00);

        filmProduction = new FilmProduction();
        filmProduction.setId(1L);
        filmProduction.setTitle("Test Title.");
        filmProduction.setDescription("Test Description Lorem Ipsum");
        filmProduction.setStartDate(new Date(2025 - 2 - 24));
        filmProduction.setEndDate(new Date(2025 - 3 - 24));
        filmProduction.setStatus("Test Status");
        filmProduction.setDirector("Test Director");
        filmProduction.setBudget(12000.00);

        MvcResult result = mockMvc.perform(post("/api/film-productions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmProductionDTO)))
                .andExpect(status().isCreated())
                        .andReturn();

        String locationHeader = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(locationHeader);
        Long id = jsonNode.get("id").asLong();

        mockMvc.perform(delete("/api/film-productions/{id}", id))
                .andExpect(status().isNoContent());
    }

    @Test
    void testSearchFilmProductionsShouldReturnOk() throws Exception {
        FilmProductionDTO filmProductionDTO;
        FilmProduction filmProduction;

        filmProductionDTO = new FilmProductionDTO();
        filmProductionDTO.setTitle("Test Title.");
        filmProductionDTO.setDescription("Test Description Lorem Ipsum");
        filmProductionDTO.setStartDate(new Date(2025 - 2 - 24));
        filmProductionDTO.setEndDate(new Date(2025 - 3 - 24));
        filmProductionDTO.setStatus("Test Status");
        filmProductionDTO.setDirector("Test Director");
        filmProductionDTO.setBudget(12000.00);

        filmProduction = new FilmProduction();
        filmProduction.setTitle("Test Title.");
        filmProduction.setDescription("Test Description Lorem Ipsum");
        filmProduction.setStartDate(new Date(2025 - 2 - 24));
        filmProduction.setEndDate(new Date(2025 - 3 - 24));
        filmProduction.setStatus("Test Status");
        filmProduction.setDirector("Test Director");
        filmProduction.setBudget(12000.00);

        mockMvc.perform(post("/api/film-productions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmProductionDTO)))
                .andExpect(status().isCreated());

        FilmProductionDTO anotherFilmProduction = new FilmProductionDTO();
        anotherFilmProduction.setTitle("New Test");
        anotherFilmProduction.setDescription("New Test Description");
        anotherFilmProduction.setStartDate(new Date(2024 - 2 - 24));
        anotherFilmProduction.setEndDate(new Date(2024 - 3 - 24));
        anotherFilmProduction.setStatus("Test");
        anotherFilmProduction.setDirector("Test Director");
        anotherFilmProduction.setBudget(16000.00);

        mockMvc.perform(post("/api/film-productions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(anotherFilmProduction)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/film-productions/search")
                        .param("title", "New Test") // Set search parameters
                        .param("director", "Test Director")
                        .param("status", "Test")
                        .param("minBudget", "10000")
                        .param("maxBudget", "20000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}