package com.film_production.demo.integration_tests;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_production.demo.models.dtos.ScheduleDTO;
import com.film_production.demo.models.entities.Schedule;
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

import java.time.LocalTime;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
@Transactional
@AutoConfigureTestDatabase
public class ScheduleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateScheduleShouldReturnCreated() throws Exception {
        Schedule schedule = new Schedule();
        schedule.setShootDate(new Date());
        schedule.setLocation("Studio A");
        schedule.setStartTime(LocalTime.of(9, 0));
        schedule.setEndTime(LocalTime.of(17, 0));

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setShootDate(new Date());
        scheduleDTO.setLocation("Studio A");
        scheduleDTO.setStartTime(LocalTime.of(9, 0));
        scheduleDTO.setEndTime(LocalTime.of(17, 0));

        mockMvc.perform(post("/api/schedules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(schedule)))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetScheduleShouldReturnOk() throws Exception {
        Schedule schedule = new Schedule();
        schedule.setShootDate(new Date());
        schedule.setLocation("Studio A");
        schedule.setStartTime(LocalTime.of(9, 0));
        schedule.setEndTime(LocalTime.of(17, 0));

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setShootDate(new Date());
        scheduleDTO.setLocation("Studio A");
        scheduleDTO.setStartTime(LocalTime.of(9, 0));
        scheduleDTO.setEndTime(LocalTime.of(17, 0));

        mockMvc.perform(post("/api/schedules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(schedule)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/api/schedules")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateScheduleShouldReturnOk() throws Exception {
        Schedule schedule = new Schedule();
        schedule.setShootDate(new Date());
        schedule.setLocation("Studio A");
        schedule.setStartTime(LocalTime.of(9, 0));
        schedule.setEndTime(LocalTime.of(17, 0));

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setShootDate(new Date());
        scheduleDTO.setLocation("Studio A");
        scheduleDTO.setStartTime(LocalTime.of(9, 0));
        scheduleDTO.setEndTime(LocalTime.of(17, 0));

        MvcResult result = mockMvc.perform(post("/api/schedules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(schedule)))
                .andExpect(status().isCreated())
                        .andReturn();

        String locationHeader = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(locationHeader);
        Long id = jsonNode.get("id").asLong();

        schedule.setLocation("Studio B");
        mockMvc.perform(put("/api/schedules/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(schedule)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteScheduleShouldReturnNoContent() throws Exception {
        Schedule schedule = new Schedule();
        schedule.setShootDate(new Date());
        schedule.setLocation("Studio A");
        schedule.setStartTime(LocalTime.of(9, 0));
        schedule.setEndTime(LocalTime.of(17, 0));

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setShootDate(new Date());
        scheduleDTO.setLocation("Studio A");
        scheduleDTO.setStartTime(LocalTime.of(9, 0));
        scheduleDTO.setEndTime(LocalTime.of(17, 0));

        MvcResult result = mockMvc.perform(post("/api/schedules")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(schedule)))
                .andExpect(status().isCreated())
                        .andReturn();

        String locationHeader = result.getResponse().getContentAsString();
        JsonNode jsonNode = objectMapper.readTree(locationHeader);
        Long id = jsonNode.get("id").asLong();

        mockMvc.perform(delete("/api/schedules/{id}", 1))
                .andExpect(status().isNoContent());
    }
}