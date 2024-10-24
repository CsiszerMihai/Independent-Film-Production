package com.film_production.demo.models.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Date;

@Data
public class FilmProductionDTO {

    private Long id;
    @NotBlank(message = "Title field is mandatory.")
    private String title;
    @NotBlank(message = "Film Description field is mandatory.")
    private String description;
    private Date startDate;
    private Date endDate;
    @NotBlank(message = "Film Production Status field is mandatory.")
    private String status;
    @NotBlank(message = "Director field is mandatory.")
    private String director;
    @Min(value = 0, message = "Budget field must be higher than 0")
    private Double budget;
}