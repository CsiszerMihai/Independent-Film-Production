package com.film_production.demo.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class CastMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "role")
    private String role;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "availability")
    private Boolean availability;
    @Column(name = "film_production")
    private FilmProduction filmProduction;
}