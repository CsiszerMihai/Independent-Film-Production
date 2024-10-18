package com.film_production.demo.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table
public class ScriptVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "content")
    private String content;
    @Column(name = "version_number")
    private Integer versionNumber;
    @Column(name = "updated_at")
    private Date updatedAt;
    @Column(name = "script")
    private Script script;
    @Column(name = "author")
    private String author;
}