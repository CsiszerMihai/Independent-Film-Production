package com.film_production.demo.models.dtos;

import com.film_production.demo.models.entities.FilmProduction;
import lombok.Data;

@Data
public class CrewMemberDTO {

    private Long id;
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Boolean availability;
    private FilmProduction filmProduction;
}