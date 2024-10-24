package com.film_production.demo.controllers;

import com.film_production.demo.models.dtos.FilmProductionDTO;
import com.film_production.demo.services.FilmProductionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FilmProductionController {

    private final FilmProductionService filmProductionService;

    public FilmProductionController(FilmProductionService filmProductionService) {
        this.filmProductionService = filmProductionService;
    }

    @PostMapping("/api/film-production")
    public ResponseEntity<FilmProductionDTO> createFilmProduction(@RequestBody FilmProductionDTO filmProductionDTO) {
        FilmProductionDTO createdFilmProduction = filmProductionService.createFilmProduction(filmProductionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFilmProduction);
    }

    @GetMapping("/api/film-production/{id}")
    public ResponseEntity<FilmProductionDTO> getFilmProductionById(@PathVariable Long id) {
        FilmProductionDTO filmProduction = filmProductionService.getFilmProductionById(id);
        return ResponseEntity.ok(filmProduction);
    }

    @GetMapping("/api/film-production")
    public ResponseEntity<List<FilmProductionDTO>> getAllFilmProductions() {
        List<FilmProductionDTO> filmProductions = filmProductionService.getAllFilmProductions();
        return ResponseEntity.ok(filmProductions);
    }

    @PutMapping("/api/film-production/{id}")
    public ResponseEntity<FilmProductionDTO> updateFilmProductionById(@PathVariable Long id, @RequestBody FilmProductionDTO filmProductionDTO) {
        FilmProductionDTO updatedFilmProduction = filmProductionService.updateFilmProductionById(id, filmProductionDTO);
        return ResponseEntity.ok(updatedFilmProduction);
    }

    @DeleteMapping("/api/film-production/{id}")
    public ResponseEntity<Void> deleteFilmProductionById(@PathVariable Long id) {
        filmProductionService.deleteFilmProductionById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/film-production/search-film")
    public ResponseEntity<List<FilmProductionDTO>> searchFilmProductions(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "director", required = false) String director,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "minBudget", required = false) Double minBudget,
            @RequestParam(value = "maxBudget", required = false) Double maxBudget) {

        List<FilmProductionDTO> filmProductions = filmProductionService.findFilmProductionsByFilter(title, director, status, minBudget, maxBudget);
        return ResponseEntity.ok(filmProductions);
    }
}