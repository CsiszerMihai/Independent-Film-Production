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

    @PostMapping("/api/film_production")
    public ResponseEntity<FilmProductionDTO> createFilmProduction(@RequestBody FilmProductionDTO filmProductionDTO) {
        FilmProductionDTO createdFilmProduction = filmProductionService.createFilmProduction(filmProductionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFilmProduction);
    }

    @GetMapping("/api/film_production/{id}")
    public ResponseEntity<FilmProductionDTO> getFilmProductionById(@PathVariable Long id) {
        FilmProductionDTO filmProduction = filmProductionService.getFilmProductionById(id);
        return ResponseEntity.ok(filmProduction);
    }

    @GetMapping("/api/film_production")
    public ResponseEntity<List<FilmProductionDTO>> getAllFilmProductions() {
        List<FilmProductionDTO> filmProductions = filmProductionService.getAllFilmProductions();
        return ResponseEntity.ok(filmProductions);
    }

    @PutMapping("/api/film_production/{id}")
    public ResponseEntity<FilmProductionDTO> updateFilmProductionById(@PathVariable Long id, @RequestBody FilmProductionDTO filmProductionDTO) {
        FilmProductionDTO updatedFilmProduction = filmProductionService.updateFilmProductionById(id, filmProductionDTO);
        return ResponseEntity.ok(updatedFilmProduction);
    }

    @DeleteMapping("/api/film_production/{id}")
    public ResponseEntity<Void> deleteFilmProductionById(@PathVariable Long id) {
        filmProductionService.deleteFilmProductionById(id);
        return ResponseEntity.noContent().build();
    }
}