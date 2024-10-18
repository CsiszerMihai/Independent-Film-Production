package com.film_production.demo.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "film_production")
public class FilmProduction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "status")
    private String status;
    @Column(name = "director")
    private String director;
    @Column(name = "budget")
    private Double budget;
    @Column(name = "crew_members")
    private List<CrewMember> crewMembers;
    @Column(name = "cast_members")
    private List<CastMember> castMembers;
    @Column(name = "script")
    private Script script;
}