package com.film_production.demo.services;

import com.film_production.demo.models.dtos.FilmProductionDTO;

import java.util.List;

public interface FilmProductionService {

    FilmProductionDTO createFilmProduction(FilmProductionDTO filmProductionDTO);

    FilmProductionDTO getFilmProductionById(Long id);

    List<FilmProductionDTO> getAllFilmProductions();

    FilmProductionDTO updateFilmProductionById(Long id, FilmProductionDTO filmProductionDTO);

    void deleteFilmProductionById(Long id);
}