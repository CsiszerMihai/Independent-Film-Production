package com.film_production.demo.services;

import com.film_production.demo.models.dtos.ScheduleDTO;

import java.util.List;

public interface ScheduleService {

    ScheduleDTO createSchedule(ScheduleDTO scheduleDTO);

    ScheduleDTO getScheduleById(Long id);

    List<ScheduleDTO> getAllSchedules();

    ScheduleDTO updateScheduleById(Long id, ScheduleDTO scheduleDTO);

    void deleteScheduleById(Long id);
}