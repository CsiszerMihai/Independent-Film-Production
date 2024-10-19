package com.film_production.demo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_production.demo.exceptions.ScheduleNotFoundException;
import com.film_production.demo.models.dtos.ScheduleDTO;
import com.film_production.demo.models.entities.Schedule;
import com.film_production.demo.repositories.ScheduleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ScheduleServiceImpl implements ScheduleService {

    private static final Logger log = LoggerFactory.getLogger(ScheduleServiceImpl.class);
    private final ObjectMapper objectMapper;
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ObjectMapper objectMapper, ScheduleRepository scheduleRepository) {
        this.objectMapper = objectMapper;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {
        Schedule scheduleEntity = objectMapper.convertValue(scheduleDTO, Schedule.class);
        Schedule scheduleEntityResponse = scheduleRepository.save(scheduleEntity);
        log.info("Schedule with id {} was saved.", scheduleEntityResponse.getId());

        return objectMapper.convertValue(scheduleEntityResponse, ScheduleDTO.class);
    }

    @Override
    public ScheduleDTO getScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException(id));

        return objectMapper.convertValue(schedule, ScheduleDTO.class);
    }

    @Override
    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();

        return schedules.stream()
                .map(schedule -> objectMapper.convertValue(schedule, ScheduleDTO.class))
                .toList();
    }

    @Override
    public ScheduleDTO updateScheduleById(Long id, ScheduleDTO scheduleDTO) {
        if (id == null) {
            throw new IllegalArgumentException("Schedule ID cannot be null.");
        }
        Schedule existingSchedule = scheduleRepository.findById(id).
                orElseThrow(() -> new ScheduleNotFoundException(id));

        updateExistingSchedule(existingSchedule, scheduleDTO);
        Schedule updatedSchedule = scheduleRepository.save(existingSchedule);
        log.info("Schedule with id {} was updated", updatedSchedule.getId());

        return objectMapper.convertValue(updatedSchedule, ScheduleDTO.class);
    }

    private void updateExistingSchedule(Schedule existingSchedule, ScheduleDTO scheduleDTO) {
        existingSchedule.setShootDate(scheduleDTO.getShootDate());
        existingSchedule.setLocation(scheduleDTO.getLocation());
        existingSchedule.setStartTime(scheduleDTO.getStartTime());
        existingSchedule.setEndTime(scheduleDTO.getEndTime());
    }

    @Override
    public void deleteScheduleById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Schedule ID cannot be null.");
        }
        Schedule existingSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException(id));

        scheduleRepository.delete(existingSchedule);
        log.info("Schedule with id {} was deleted", id);
    }
}