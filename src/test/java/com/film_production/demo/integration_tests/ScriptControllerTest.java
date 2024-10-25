package com.film_production.demo.integration_tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_production.demo.models.dtos.ScriptDTO;
import com.film_production.demo.models.entities.Script;
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
public class ScriptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateScriptShouldReturnCreated() throws Exception {
        Script script = new Script();
        script.setContent("Sample script content.");
        script.setVersionNumber(1);
        script.setUpdatedAt(new Date());
        script.setAuthor("John Doe");

        ScriptDTO scriptDTO = new ScriptDTO();
        scriptDTO.setContent("Sample script content.");
        scriptDTO.setVersionNumber(1);
        scriptDTO.setUpdatedAt(new Date());
        scriptDTO.setAuthor("John Doe");

        mockMvc.perform(post("/api/scripts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(script)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetScriptShouldReturnOk() throws Exception {
        Script script = new Script();
        script.setContent("Sample script content.");
        script.setVersionNumber(1);
        script.setUpdatedAt(new Date());
        script.setAuthor("John Doe");

        ScriptDTO scriptDTO = new ScriptDTO();
        scriptDTO.setContent("Sample script content.");
        scriptDTO.setVersionNumber(1);
        scriptDTO.setUpdatedAt(new Date());
        scriptDTO.setAuthor("John Doe");

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
        Script script = new Script();
        script.setContent("Sample script content.");
        script.setVersionNumber(1);
        script.setUpdatedAt(new Date());
        script.setAuthor("John Doe");

        ScriptDTO scriptDTO = new ScriptDTO();
        scriptDTO.setContent("Sample script content.");
        scriptDTO.setVersionNumber(1);
        scriptDTO.setUpdatedAt(new Date());
        scriptDTO.setAuthor("John Doe");

        MvcResult result = mockMvc.perform(post("/api/scripts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(script)))
                .andExpect(status().isCreated())
                        .andReturn();

        String locationHeader = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(locationHeader);
        Long id = jsonNode.get("id").asLong();

        script.setContent("Updated script content.");
        mockMvc.perform(put("/api/scripts/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(script)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteScriptShouldReturnNoContent() throws Exception {
        Script script = new Script();
        script.setContent("Sample script content.");
        script.setVersionNumber(1);
        script.setUpdatedAt(new Date());
        script.setAuthor("John Doe");

        ScriptDTO scriptDTO = new ScriptDTO();
        scriptDTO.setContent("Sample script content.");
        scriptDTO.setVersionNumber(1);
        scriptDTO.setUpdatedAt(new Date());
        scriptDTO.setAuthor("John Doe");

        MvcResult result = mockMvc.perform(post("/api/scripts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(script)))
                .andExpect(status().isCreated())
                        .andReturn();

        String locationHeader = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(locationHeader);
        Long id = jsonNode.get("id").asLong();

        mockMvc.perform(delete("/api/scripts/{id}", id))
                .andExpect(status().isNoContent());
    }
}