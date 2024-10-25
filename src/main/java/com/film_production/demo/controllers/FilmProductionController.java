package com.film_production.demo.controllers;

import com.film_production.demo.models.dtos.FilmProductionDTO;
import com.film_production.demo.services.FilmProductionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping("/api/film-productions")
@RestController
public class FilmProductionController {

    private final FilmProductionService filmProductionService;

    public FilmProductionController(FilmProductionService filmProductionService) {
        this.filmProductionService = filmProductionService;
    }

    @PostMapping
    public ResponseEntity<FilmProductionDTO> createFilmProduction(@Valid @RequestBody FilmProductionDTO filmProductionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(filmProductionService.createFilmProduction(filmProductionDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmProductionDTO> getFilmProductionById(@PathVariable Long id) {
        return ResponseEntity.ok(filmProductionService.getFilmProductionById(id));
    }

    @GetMapping
    public ResponseEntity<List<FilmProductionDTO>> getAllFilmProductions() {
        return ResponseEntity.ok(filmProductionService.getAllFilmProductions());
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmProductionDTO> updateFilmProductionById(@PathVariable Long id,@Valid @RequestBody FilmProductionDTO filmProductionDTO) {
        return ResponseEntity.ok(filmProductionService.updateFilmProductionById(id, filmProductionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilmProductionById(@PathVariable Long id) {
        filmProductionService.deleteFilmProductionById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<FilmProductionDTO>> searchFilmProductions(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "director", required = false) String director,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "minBudget", required = false) Double minBudget,
            @RequestParam(value = "maxBudget", required = false) Double maxBudget) {

        return ResponseEntity.ok(filmProductionService.findFilmProductionsByFilter(title, director, status, minBudget, maxBudget));
    }
}