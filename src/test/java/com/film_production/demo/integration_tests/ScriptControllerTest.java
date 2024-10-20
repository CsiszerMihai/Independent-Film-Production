package com.film_production.demo.integration_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_production.demo.models.entities.Script;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@Transactional
@AutoConfigureTestDatabase
public class ScriptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Script script;

    @BeforeEach
    void setUp() {
        script = new Script();
        script.setContent("Sample script content.");
        script.setVersionNumber(1);
        script.setUpdatedAt(new Date());
        script.setAuthor("John Doe");
    }

    @Test
    void testCreateScriptShouldReturnCreated() throws Exception {
        mockMvc.perform(post("/api/scripts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(script)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetScriptShouldReturnOk() throws Exception {
        mockMvc.perform(post("/api/scripts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(script)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/scripts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateScriptShouldReturnOk() throws Exception {
        mockMvc.perform(post("/api/scripts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(script)))
                .andExpect(status().isCreated());

        script.setContent("Updated script content.");
        mockMvc.perform(put("/api/scripts/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(script)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteScriptShouldReturnNoContent() throws Exception {
        mockMvc.perform(post("/api/scripts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(script)))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/api/scripts/{id}", 1))
                .andExpect(status().isNoContent());
    }
}