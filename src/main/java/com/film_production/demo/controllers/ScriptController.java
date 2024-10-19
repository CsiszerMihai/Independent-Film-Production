package com.film_production.demo.controllers;

import com.film_production.demo.models.dtos.ScriptDTO;
import com.film_production.demo.services.ScriptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScriptController {

    private final ScriptService scriptService;

    public ScriptController(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    @PostMapping("/api/script")
    public ResponseEntity<ScriptDTO> createScript(@RequestBody ScriptDTO scriptDTO) {
        ScriptDTO createdScript = scriptService.createScript(scriptDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdScript);
    }

    @GetMapping("/api/script/{id}")
    public ResponseEntity<ScriptDTO> getScriptById(@PathVariable Long id) {
        ScriptDTO script = scriptService.getScriptById(id);
        return ResponseEntity.ok(script);
    }

    @GetMapping("/api/script")
    public ResponseEntity<List<ScriptDTO>> getAllScripts() {
        List<ScriptDTO> scripts = scriptService.getAllScripts();
        return ResponseEntity.ok(scripts);
    }

    @PutMapping("/api/script/{id}")
    public ResponseEntity<ScriptDTO> updateScriptById(@PathVariable Long id, @RequestBody ScriptDTO scriptDTO) {
        ScriptDTO updatedScript = scriptService.updateScriptById(id, scriptDTO);
        return ResponseEntity.ok(updatedScript);
    }

    @DeleteMapping("/api/script/{id}")
    public ResponseEntity<Void> deleteScriptById(@PathVariable Long id) {
        scriptService.deleteScriptById(id);
        return ResponseEntity.noContent().build();
    }
}