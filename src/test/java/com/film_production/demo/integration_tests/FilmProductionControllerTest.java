package com.film_production.demo.integration_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_production.demo.models.dtos.FilmProductionDTO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    private FilmProductionDTO filmProductionDTO;

    @BeforeEach
    void setUp() {
        filmProductionDTO = new FilmProductionDTO();
        filmProductionDTO.setTitle("Test Title.");
        filmProductionDTO.setDescription("Test Description Lorem Ipsum");
        filmProductionDTO.setStartDate(new Date(2025 - 2 - 24));
        filmProductionDTO.setEndDate(new Date(2025 - 3 - 24));
        filmProductionDTO.setStatus("Test Status");
        filmProductionDTO.setDirector("Test Director");
        filmProductionDTO.setBudget(12000.00);
    }

    @Test
    void testCreateFilmProductionShouldReturnCreated() throws Exception {
        mockMvc.perform(post("/api/film-production")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmProductionDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetFilmProductionShouldReturnOk() throws Exception {
        mockMvc.perform(post("/api/film-production")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmProductionDTO)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/film-production")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateFilmProductionShouldReturnOk() throws Exception {
        mockMvc.perform(post("/api/film-production")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmProductionDTO)))
                .andExpect(status().isCreated());

        filmProductionDTO.setTitle("Updated Title");
        mockMvc.perform(put("/api/film-production/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmProductionDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteFilmProductionShouldReturnNoContent() throws Exception {
        mockMvc.perform(post("/api/film-production")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmProductionDTO)))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/api/film-production/{id}", 1))  // Replace '1' with the actual ID if needed
                .andExpect(status().isNoContent());
    }

    @Test
    void testSearchFilmProductionsShouldReturnOk() throws Exception {
        mockMvc.perform(post("/api/film-production")
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

        mockMvc.perform(post("/api/film-production")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(anotherFilmProduction)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/film-production/search-film")
                        .param("title", "New Test") // Set search parameters
                        .param("director", "Test Director")
                        .param("status", "Test")
                        .param("minBudget", "10000")
                        .param("maxBudget", "20000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}