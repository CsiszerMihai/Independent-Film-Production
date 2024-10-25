package com.film_production.demo.models.dtos;

import com.film_production.demo.models.entities.CastMember;
import com.film_production.demo.models.entities.CrewMember;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
public class ScheduleDTO {

    private Long id;
    @NotBlank(message = "This field is mandatory.")
    private Date shootDate;
    @NotBlank(message = "This field is mandatory.")
    private String location;
    @NotBlank(message = "This field is mandatory.")
    private LocalTime startTime;
    @NotBlank(message = "This field is mandatory.")
    private LocalTime endTime;
    @NotBlank(message = "This field is mandatory.")
    private List<CastMember> castMembers;
    @NotBlank(message = "This field is mandatory.")
    private List<CrewMember> crewMembers;
}