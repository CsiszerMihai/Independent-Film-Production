package com.film_production.demo.models.dtos;

import com.film_production.demo.models.entities.CastMember;
import com.film_production.demo.models.entities.CrewMember;
import com.film_production.demo.models.entities.Script;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FilmProductionDTO {

    private Long id;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private String status;
    private String director;
    private Double budget;
    private List<CrewMember> crewMembers;
    private List<CastMember> castMembers;
    private Script script;
}