package com.film_production.demo.models.dtos;

import com.film_production.demo.models.entities.Script;
import lombok.Data;

import java.util.Date;

@Data
public class ScriptVersionDTO {

    private Long id;
    private String content;
    private Integer versionNumber;
    private Date updatedAt;
    private Script script;
    private String author;
}