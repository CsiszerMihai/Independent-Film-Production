package com.film_production.demo.repositories;

import com.film_production.demo.models.entities.ScriptVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScriptVersionRepository extends JpaRepository<ScriptVersion, Long> {

    List<ScriptVersion> findByScriptIdOrderByVersionNumberDesc (Long scriptId);
}