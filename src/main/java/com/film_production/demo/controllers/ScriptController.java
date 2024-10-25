package com.film_production.demo.controllers;

import com.film_production.demo.models.dtos.ScriptDTO;
import com.film_production.demo.services.ScriptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping("/api/scripts")
@RestController
public class ScriptController {

    private final ScriptService scriptService;

    public ScriptController(ScriptService scriptService) {
        this.scriptService = scriptService;
    }

    @PostMapping
    public ResponseEntity<ScriptDTO> createScript(@RequestBody ScriptDTO scriptDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scriptService.createScript(scriptDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScriptDTO> getScriptById(@PathVariable Long id) {
        return ResponseEntity.ok(scriptService.getScriptById(id));
    }

    @GetMapping
    public ResponseEntity<List<ScriptDTO>> getAllScripts() {
        return ResponseEntity.ok(scriptService.getAllScripts());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScriptDTO> updateScriptById(@PathVariable Long id, @RequestBody ScriptDTO scriptDTO) {
        return ResponseEntity.ok(scriptService.updateScriptById(id, scriptDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScriptById(@PathVariable Long id) {
        scriptService.deleteScriptById(id);
        return ResponseEntity.noContent().build();
    }
}