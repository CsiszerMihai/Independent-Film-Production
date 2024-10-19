package com.film_production.demo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_production.demo.exceptions.ScriptVersionNotFoundException;
import com.film_production.demo.models.dtos.ScriptVersionDTO;
import com.film_production.demo.models.entities.ScriptVersion;
import com.film_production.demo.repositories.ScriptVersionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ScriptVersionServiceImpl implements ScriptVersionService {

    private static final Logger log = LoggerFactory.getLogger(ScriptVersionServiceImpl.class);
    private final ObjectMapper objectMapper;
    private final ScriptVersionRepository scriptVersionRepository;

    public ScriptVersionServiceImpl(ObjectMapper objectMapper, ScriptVersionRepository scriptVersionRepository) {
        this.objectMapper = objectMapper;
        this.scriptVersionRepository = scriptVersionRepository;
    }

    @Override
    public ScriptVersionDTO createScriptVersion(ScriptVersionDTO scriptVersionDTO) {
        ScriptVersion scriptVersionEntity = objectMapper.convertValue(scriptVersionDTO, ScriptVersion.class);
        ScriptVersion scriptVersionEntityResponse = scriptVersionRepository.save(scriptVersionEntity);
        log.info("Script Version with id {} was saved.", scriptVersionEntityResponse.getId());

        return objectMapper.convertValue(scriptVersionEntityResponse, ScriptVersionDTO.class);
    }

    @Override
    public ScriptVersionDTO getScriptVersionById(Long id) {
        ScriptVersion scriptVersion = scriptVersionRepository.findById(id)
                .orElseThrow(() -> new ScriptVersionNotFoundException(id));

        return objectMapper.convertValue(scriptVersion, ScriptVersionDTO.class);
    }

    @Override
    public List<ScriptVersionDTO> getAllScriptVersions() {
        List<ScriptVersion> scriptVersions = scriptVersionRepository.findAll();

        return scriptVersions.stream()
                .map(scriptVersion -> objectMapper.convertValue(scriptVersion, ScriptVersionDTO.class))
                .toList();
    }

    @Override
    public ScriptVersionDTO updateScriptVersionById(Long id, ScriptVersionDTO scriptVersionDTO) {
        if (id == null) {
            throw new IllegalArgumentException("Script Version ID cannot be null.");
        }
        ScriptVersion existingScriptVersion = scriptVersionRepository.findById(id)
                .orElseThrow(() -> new ScriptVersionNotFoundException(id));

        updateExistingScriptVersion(existingScriptVersion, scriptVersionDTO);
        ScriptVersion updatedScriptVersion = scriptVersionRepository.save(existingScriptVersion);
        log.info("Script Version with id {} was updated.", updatedScriptVersion.getId());

        return objectMapper.convertValue(updatedScriptVersion, ScriptVersionDTO.class);
    }

    private void updateExistingScriptVersion(ScriptVersion existingScriptVersion, ScriptVersionDTO scriptVersionDTO) {
        existingScriptVersion.setUpdatedAt(scriptVersionDTO.getUpdatedAt());
        existingScriptVersion.setContent(scriptVersionDTO.getContent());
        existingScriptVersion.setVersionNumber(scriptVersionDTO.getVersionNumber());
        existingScriptVersion.setAuthor(scriptVersionDTO.getAuthor());
    }

    @Override
    public void deleteScriptVersionById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Script Version ID cannot be null.");
        }
        ScriptVersion existingScriptVersion = scriptVersionRepository.findById(id)
                .orElseThrow(() -> new ScriptVersionNotFoundException(id));

        scriptVersionRepository.delete(existingScriptVersion);
        log.info("Script Version with id {} was deleted.", id);
    }
}
