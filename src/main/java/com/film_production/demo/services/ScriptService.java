package com.film_production.demo.services;

import com.film_production.demo.models.dtos.ScriptDTO;

import java.util.List;

public interface ScriptService {

    ScriptDTO createScript(ScriptDTO scriptDTO);

    ScriptDTO getScriptById(Long id);

    List<ScriptDTO> getAllScripts();

    ScriptDTO updateScriptById(Long id, ScriptDTO scriptDTO);

    void deleteScriptById(Long id);
}