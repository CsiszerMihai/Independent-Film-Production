package com.film_production.demo.models.dtos;

import com.film_production.demo.models.entities.FilmProduction;
import lombok.Data;

import java.util.Date;

@Data
public class ScriptDTO {

    private Long id;
    private String content;
    private Integer versionNumber;
    private Date updatedAt;
    private String author;
    private FilmProductionDTO filmProduction;
}