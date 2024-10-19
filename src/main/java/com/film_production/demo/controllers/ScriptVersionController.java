package com.film_production.demo.controllers;

import com.film_production.demo.models.dtos.ScriptVersionDTO;
import com.film_production.demo.services.ScriptVersionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScriptVersionController {

    private final ScriptVersionService scriptVersionService;

    public ScriptVersionController(ScriptVersionService scriptVersionService) {
        this.scriptVersionService = scriptVersionService;
    }

    @PostMapping("/api/script-version")
    public ResponseEntity<ScriptVersionDTO> createScriptVersion(@RequestBody ScriptVersionDTO scriptVersionDTO) {
        ScriptVersionDTO createdScriptVersion = scriptVersionService.createScriptVersion(scriptVersionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdScriptVersion);
    }

    @GetMapping("/api/script-version/{id}")
    public ResponseEntity<ScriptVersionDTO> getScriptVersionById(@PathVariable Long id) {
        ScriptVersionDTO scriptVersion = scriptVersionService.getScriptVersionById(id);
        return ResponseEntity.ok(scriptVersion);
    }

    @GetMapping("/api-version/script")
    public ResponseEntity<List<ScriptVersionDTO>> getAllScriptVersions() {
        List<ScriptVersionDTO> scriptVersions = scriptVersionService.getAllScriptVersions();
        return ResponseEntity.ok(scriptVersions);
    }

    @PutMapping("/api/script-version/{id}")
    public ResponseEntity<ScriptVersionDTO> updateScriptVersionById(@PathVariable Long id, @RequestBody ScriptVersionDTO scriptVersionDTO) {
        ScriptVersionDTO updatedScriptVersion = scriptVersionService.updateScriptVersionById(id, scriptVersionDTO);
        return ResponseEntity.ok(updatedScriptVersion);
    }

    @DeleteMapping("/api/script-version/{id}")
    public ResponseEntity<Void> deleteScriptVersionById(@PathVariable Long id) {
        scriptVersionService.deleteScriptVersionById(id);
        return ResponseEntity.noContent().build();
    }
}