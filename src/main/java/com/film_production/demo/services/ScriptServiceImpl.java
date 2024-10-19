package com.film_production.demo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_production.demo.exceptions.ScheduleNotFoundException;
import com.film_production.demo.exceptions.ScriptNotFoundException;
import com.film_production.demo.models.dtos.ScriptDTO;
import com.film_production.demo.models.entities.Script;
import com.film_production.demo.repositories.ScriptRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ScriptServiceImpl implements ScriptService {

    private static final Logger log = LoggerFactory.getLogger(ScriptServiceImpl.class);
    private final ObjectMapper objectMapper;
    private final ScriptRepository scriptRepository;

    public ScriptServiceImpl(ObjectMapper objectMapper, ScriptRepository scriptRepository) {
        this.objectMapper = objectMapper;
        this.scriptRepository = scriptRepository;
    }

    @Override
    public ScriptDTO createScript(ScriptDTO scriptDTO) {
        Script scriptEntity = objectMapper.convertValue(scriptDTO, Script.class);
        Script scriptEntityResponse = scriptRepository.save(scriptEntity);
        log.info("Script with id {} was saved.", scriptEntityResponse.getId());

        return objectMapper.convertValue(scriptEntityResponse, ScriptDTO.class);
    }

    @Override
    public ScriptDTO getScriptById(Long id) {
        Script script = scriptRepository.findById(id)
                .orElseThrow(() -> new ScriptNotFoundException(id));

        return objectMapper.convertValue(script, ScriptDTO.class);
    }

    @Override
    public List<ScriptDTO> getAllScripts() {
        List<Script> scripts = scriptRepository.findAll();

        return scripts.stream()
                .map(script -> objectMapper.convertValue(script, ScriptDTO.class))
                .toList();
    }

    @Override
    public ScriptDTO updateScriptById(Long id, ScriptDTO scriptDTO) {
        if (id == null) {
            throw new IllegalArgumentException("Script ID cannot be null.");
        }
        Script existingScript = scriptRepository.findById(id)
                .orElseThrow(() -> new ScriptNotFoundException(id));

        updateExistingScript(existingScript, scriptDTO);
        Script updatedScript = scriptRepository.save(existingScript);
        log.info("Script with id {} was updated.", updatedScript.getId());

        return objectMapper.convertValue(updatedScript, ScriptDTO.class);
    }

    private void updateExistingScript(Script existingScript, ScriptDTO scriptDTO) {
        existingScript.setUpdatedAt(scriptDTO.getUpdatedAt());
        existingScript.setContent(scriptDTO.getContent());
        existingScript.setVersionNumber(scriptDTO.getVersionNumber());
        existingScript.setAuthor(scriptDTO.getAuthor());
    }

    @Override
    public void deleteScriptById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Script ID cannot be null.");
        }
        Script existingScript = scriptRepository.findById(id)
                .orElseThrow(() -> new ScriptNotFoundException(id));

        scriptRepository.delete(existingScript);
        log.info("Script with id {} was deleted.", id);
    }
}