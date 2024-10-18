package com.film_production.demo.repositories;


import com.film_production.demo.models.entities.Script;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScriptRepository extends JpaRepository<Script, Long> {

    List<Script> findByFilmProductionId(Long filmProductionId);
}