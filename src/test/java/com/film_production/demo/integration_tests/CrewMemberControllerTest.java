package com.film_production.demo.integration_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_production.demo.models.entities.CrewMember;
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
public class CrewMemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private CrewMember crewMember;

    @BeforeEach
    void setUp() {
        crewMember = new CrewMember();
        crewMember.setRole("Producer");
        crewMember.setFirstName("Alice");
        crewMember.setLastName("Smith");
        crewMember.setEmail("alice.smith@example.com");
        crewMember.setPhoneNumber("123-456-7890");
        crewMember.setAvailability(true);
    }

    @Test
    void testCreateCrewMemberShouldReturnCreated() throws Exception {
        mockMvc.perform(post("/api/crew-members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(crewMember)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetCrewMemberShouldReturnOk() throws Exception {
        mockMvc.perform(post("/api/crew-members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(crewMember)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/crew-members")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateCrewMemberShouldReturnOk() throws Exception {
        mockMvc.perform(post("/api/crew-members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(crewMember)))
                .andExpect(status().isCreated());

        crewMember.setFirstName("Bob");
        mockMvc.perform(put("/api/crew-members/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(crewMember)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteCrewMemberShouldReturnNoContent() throws Exception {
        mockMvc.perform(post("/api/crew-members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(crewMember)))
                .andExpect(status().isCreated());

        mockMvc.perform(delete("/api/crew-members/{id}", 1))
                .andExpect(status().isNoContent());
    }
}