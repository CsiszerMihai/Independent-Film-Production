package com.film_production.demo.models.dtos;

import com.film_production.demo.models.entities.FilmProduction;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class ScriptDTO {

    private Long id;
    @NotBlank(message = "This field is mandatory.")
    private String content;
    @NotBlank(message = "This field is mandatory.")
    private Integer versionNumber;
    @NotBlank(message = "This field is mandatory.")
    private Date updatedAt;
    @NotBlank(message = "This field is mandatory.")
    private String author;
    @NotBlank(message = "This field is mandatory.")
    private FilmProductionDTO filmProduction;
}