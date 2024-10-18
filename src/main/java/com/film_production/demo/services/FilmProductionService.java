package com.film_production.demo.services;

import com.film_production.demo.models.dtos.FilmProductionDTO;

import java.util.List;

public interface FilmProductionService {

    FilmProductionDTO saveOrUpdateFilmProduction(FilmProductionDTO filmProductionDTO);

    FilmProductionDTO getFilmProductionById(Long id);

    List<FilmProductionDTO> getAllFilmProductions();

    List<FilmProductionDTO> getFilmProductionsByStatus(String status);

    void deleteFilmProduction(Long id);
}