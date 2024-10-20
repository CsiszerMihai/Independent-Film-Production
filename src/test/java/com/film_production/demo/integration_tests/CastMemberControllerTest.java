package com.film_production.demo.integration_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_production.demo.models.entities.CastMember;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@Transactional
@AutoConfigureTestDatabase
public class CastMemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private CastMember castMember;

    @BeforeEach
    void setUp() {
        castMember = new CastMember();
        castMember.setRole("Lead Actor");
        castMember.setFirstName("John");
        castMember.setLastName("Doe");
        castMember.setAvailability(true);
    }

    @Test
    void testCreateCastMemberShouldReturnCreated() throws Exception {
        mockMvc.perform(post("/api/cast-members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(castMember)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetCastMemberShouldReturnOk() throws Exception {
        mockMvc.perform(post("/api/cast-members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(castMember)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/cast-members")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateCastMemberShouldReturnOk() throws Exception {
        mockMvc.perform(post("/api/cast-members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(castMember)))
                .andExpect(status().isCreated());

        castMember.setFirstName("Jane");
        mockMvc.perform(put("/api/cast-members/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(castMember)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteCastMemberShouldReturnNoContent() throws Exception {
        mockMvc.perform(post("/api/cast-members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(castMember)))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/api/cast-members/{id}", 1))
                .andExpect(status().isNoContent());
    }
}