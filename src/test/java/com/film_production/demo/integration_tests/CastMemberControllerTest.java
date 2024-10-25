package com.film_production.demo.integration_tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_production.demo.models.dtos.CastMemberDTO;
import com.film_production.demo.models.entities.CastMember;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    void testCreateCastMemberShouldReturnCreated() throws Exception {
        CastMember castMember = new CastMember();
        castMember.setRole("Lead Actor");
        castMember.setFirstName("John");
        castMember.setLastName("Doe");
        castMember.setAvailability(true);

        CastMemberDTO castMemberDTO = new CastMemberDTO();
        castMemberDTO.setRole("Lead Actor");
        castMemberDTO.setFirstName("John");
        castMemberDTO.setLastName("Doe");
        castMemberDTO.setAvailability(true);

        mockMvc.perform(post("/api/cast-members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(castMember)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetCastMemberShouldReturnOk() throws Exception {
        CastMember castMember = new CastMember();
        castMember.setRole("Lead Actor");
        castMember.setFirstName("John");
        castMember.setLastName("Doe");
        castMember.setAvailability(true);

        CastMemberDTO castMemberDTO = new CastMemberDTO();
        castMemberDTO.setRole("Lead Actor");
        castMemberDTO.setFirstName("John");
        castMemberDTO.setLastName("Doe");
        castMemberDTO.setAvailability(true);

        mockMvc.perform(post("/api/cast-members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(castMember)))
                .andExpect(status().isCreated());

        castMember.setId(1L);

        mockMvc.perform(get("/api/cast-members")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateCastMemberShouldReturnOk() throws Exception {
        CastMember castMember = new CastMember();
        castMember.setRole("Lead Actor");
        castMember.setFirstName("John");
        castMember.setLastName("Doe");
        castMember.setAvailability(true);

        CastMemberDTO castMemberDTO = new CastMemberDTO();
        castMemberDTO.setRole("Lead Actor");
        castMemberDTO.setFirstName("John");
        castMemberDTO.setLastName("Doe");
        castMemberDTO.setAvailability(true);

        MvcResult result = mockMvc.perform(post("/api/cast-members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(castMember)))
                .andExpect(status().isCreated())
                        .andReturn();

        String locationHeader = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(locationHeader);
        Long id = jsonNode.get("id").asLong();

        castMember.setFirstName("Jane");
        mockMvc.perform(put("/api/cast-members/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(castMember)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteCastMemberShouldReturnNoContent() throws Exception {
        CastMember castMember = new CastMember();
        castMember.setRole("Lead Actor");
        castMember.setFirstName("John");
        castMember.setLastName("Doe");
        castMember.setAvailability(true);

        CastMemberDTO castMemberDTO = new CastMemberDTO();
        castMemberDTO.setRole("Lead Actor");
        castMemberDTO.setFirstName("John");
        castMemberDTO.setLastName("Doe");
        castMemberDTO.setAvailability(true);

        MvcResult result = mockMvc.perform(post("/api/cast-members")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(castMember)))
                .andExpect(status().isCreated())
                        .andReturn();

        String locationHeader = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(locationHeader);
        Long id = jsonNode.get("id").asLong();

        mockMvc.perform(delete("/api/cast-members/{id}", id))
                .andExpect(status().isNoContent());
    }
}