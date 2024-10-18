package com.film_production.demo.repositories;

import com.film_production.demo.models.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

//    List<Schedule> findByFilmProductionId(Long filmProductionId);
//
//    List<Schedule> findByShootDate(Date shootDate);
}