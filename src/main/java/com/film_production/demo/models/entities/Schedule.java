package com.film_production.demo.models.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "shoot_date")
    private Date shootDate;
    @Column(name = "location")
    private String location;
    @Column(name = "start_time")
    private LocalTime startTime;
    @Column(name = "end_time")
    private LocalTime endTime;
    @Column(name = "cast_members")
    private List<CastMember> castMembers;
    @Column(name = "crew_members")
    private List<CrewMember> crewMembers;
    @Column(name = "film_production")
    private FilmProduction filmProduction;
}