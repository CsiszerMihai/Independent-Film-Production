package com.film_production.demo.services;

import com.film_production.demo.models.dtos.ScriptVersionDTO;

import java.util.List;

public interface ScriptVersionService {

    ScriptVersionDTO createScriptVersion(ScriptVersionDTO scriptVersionDTO);

    ScriptVersionDTO getScriptVersionById(Long id);

    List<ScriptVersionDTO> getAllScriptVersions();

    ScriptVersionDTO updateScriptVersionById(Long id, ScriptVersionDTO scriptVersionDTO);

    void deleteScriptVersionById(Long id);
}
