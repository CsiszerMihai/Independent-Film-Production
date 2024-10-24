package com.film_production.demo.unit_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_production.demo.exceptions.ScheduleNotFoundException;
import com.film_production.demo.models.dtos.ScheduleDTO;
import com.film_production.demo.models.entities.Schedule;
import com.film_production.demo.repositories.ScheduleRepository;
import com.film_production.demo.services.ScheduleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ScheduleServiceTest {

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    private ScheduleDTO scheduleDTO;
    private Schedule schedule;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(1L);
        scheduleDTO.setLocation("Test Location");

        schedule = new Schedule();
        schedule.setId(1L);
        schedule.setLocation("Test Location");
    }

    @Test
    void testCreateSchedule() {
        when(objectMapper.convertValue(scheduleDTO, Schedule.class)).thenReturn(schedule);
        when(scheduleRepository.save(schedule)).thenReturn(schedule);
        when(objectMapper.convertValue(schedule, ScheduleDTO.class)).thenReturn(scheduleDTO);

        ScheduleDTO createdSchedule = scheduleService.createSchedule(scheduleDTO);

        assertNotNull(createdSchedule);
        assertEquals(scheduleDTO.getId(), createdSchedule.getId());
        verify(scheduleRepository, times(1)).save(schedule);
    }

    @Test
    void testGetScheduleById_Success() {
        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));
        when(objectMapper.convertValue(schedule, ScheduleDTO.class)).thenReturn(scheduleDTO);

        ScheduleDTO result = scheduleService.getScheduleById(1L);

        assertNotNull(result);
        assertEquals("Test Location", result.getLocation());
        verify(scheduleRepository, times(1)).findById(1L);
    }

    @Test
    void testGetScheduleById_NotFound() {
        when(scheduleRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ScheduleNotFoundException.class, () -> scheduleService.getScheduleById(1L));
        verify(scheduleRepository, times(1)).findById(1L);
    }
}