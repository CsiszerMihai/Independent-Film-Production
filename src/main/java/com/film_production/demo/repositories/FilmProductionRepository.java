package com.film_production.demo.repositories;

import com.film_production.demo.models.entities.FilmProduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmProductionRepository extends JpaRepository<FilmProduction, Long> {
    //List<FilmProduction> findByStatus(String status);
}