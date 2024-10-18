package com.film_production.demo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_production.demo.exceptions.FilmProductionNotFoundException;
import com.film_production.demo.models.dtos.FilmProductionDTO;
import com.film_production.demo.models.entities.FilmProduction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.film_production.demo.repositories.FilmProductionRepository;

import java.util.List;

@Service
public class FilmProductionServiceImpl implements FilmProductionService {

    private static final Logger log = LoggerFactory.getLogger(FilmProductionServiceImpl.class);
    private final ObjectMapper objectMapper;
    private final FilmProductionRepository filmProductionRepository;

    public FilmProductionServiceImpl(ObjectMapper objectMapper, FilmProductionRepository filmProductionRepository) {
        this.objectMapper = objectMapper;
        this.filmProductionRepository = filmProductionRepository;
    }

    @Override
    public FilmProductionDTO createFilmProduction(FilmProductionDTO filmProductionDTO) {
        FilmProduction filmProductionEntity = objectMapper.convertValue(filmProductionDTO, FilmProduction.class);
        FilmProduction filmProductionEntityResponse = filmProductionRepository.save(filmProductionEntity);
        log.info("Film Production with id {} was saved", filmProductionEntityResponse.getId());

        return objectMapper.convertValue(filmProductionEntityResponse, FilmProductionDTO.class);
    }

    @Override
    public FilmProductionDTO getFilmProductionById(Long id) {
        FilmProduction filmProduction = filmProductionRepository.findById(id)
                .orElseThrow(() -> new FilmProductionNotFoundException(id));

        return objectMapper.convertValue(filmProduction, FilmProductionDTO.class);
    }

    @Override
    public List<FilmProductionDTO> getAllFilmProductions() {
        List<FilmProduction> filmProductions = filmProductionRepository.findAll();

        return filmProductions.stream()
                .map(filmProduction -> objectMapper.convertValue(filmProduction, FilmProductionDTO.class))
                .toList();
    }

    @Override
    public FilmProductionDTO updateFilmProductionById(Long id, FilmProductionDTO filmProductionDTO) {
        if (id == null) {
            throw new IllegalArgumentException("Film Production ID cannot be null.");
        }
        FilmProduction existingFilmProduction = filmProductionRepository.findById(id).
                orElseThrow(() -> new FilmProductionNotFoundException(id));

        updateExistingFilmProduction(existingFilmProduction, filmProductionDTO);
        FilmProduction updatedFilmProduction = filmProductionRepository.save(existingFilmProduction);
        log.info("Film Production with id {} was updated", updatedFilmProduction.getId());

        return objectMapper.convertValue(updatedFilmProduction, FilmProductionDTO.class);
    }

    private void updateExistingFilmProduction(FilmProduction existingFilmProduction, FilmProductionDTO filmProductionDTO) {
        existingFilmProduction.setTitle(filmProductionDTO.getTitle());
        existingFilmProduction.setDescription(filmProductionDTO.getDescription());
        existingFilmProduction.setStartDate(filmProductionDTO.getStartDate());
        existingFilmProduction.setEndDate(filmProductionDTO.getEndDate());
        existingFilmProduction.setStatus(filmProductionDTO.getStatus());
        existingFilmProduction.setDirector(filmProductionDTO.getDirector());
        existingFilmProduction.setBudget(filmProductionDTO.getBudget());
        existingFilmProduction.setCrewMembers(filmProductionDTO.getCrewMembers());
        existingFilmProduction.setCastMembers(filmProductionDTO.getCastMembers());
        existingFilmProduction.setScript(filmProductionDTO.getScript());
    }

    @Override
    public void deleteFilmProduction(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Film Production ID cannot be null.");
        }
        FilmProduction existingFilmProduction = filmProductionRepository.findById(id)
                .orElseThrow(() -> new FilmProductionNotFoundException(id));

        filmProductionRepository.delete(existingFilmProduction);
        log.info("Film Production with id {} was deleted", id);
    }
}