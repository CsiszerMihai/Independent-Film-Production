package com.film_production.demo.models.dtos;

import com.film_production.demo.models.entities.CastMember;
import com.film_production.demo.models.entities.CrewMember;
import com.film_production.demo.models.entities.FilmProduction;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
public class ScheduleDTO {

    private Long id;
    private Date shootDate;
    private String location;
    private LocalTime startTime;
    private LocalTime endTime;
    private List<CastMember> castMembers;
    private List<CrewMember> crewMembers;
    private FilmProduction filmProduction;
}