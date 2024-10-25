package com.film_production.demo.unit_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_production.demo.exceptions.FilmProductionNotFoundException;
import com.film_production.demo.models.dtos.FilmProductionDTO;
import com.film_production.demo.models.entities.FilmProduction;
import com.film_production.demo.repositories.FilmProductionRepository;
import com.film_production.demo.services.FilmProductionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FilmProductionServiceTest {

    @Mock
    private FilmProductionRepository filmProductionRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private FilmProductionServiceImpl filmProductionService;

    private FilmProductionDTO filmProductionDTO;
    private FilmProduction filmProduction;

    @Test
    void testCreateFilmProduction() {

        filmProductionDTO = new FilmProductionDTO();
        filmProductionDTO.setId(1L);
        filmProductionDTO.setTitle("Test Film");

        filmProduction = new FilmProduction();
        filmProduction.setId(1L);
        filmProduction.setTitle("Test Film");

        when(objectMapper.convertValue(filmProductionDTO, FilmProduction.class)).thenReturn(filmProduction);
        when(filmProductionRepository.save(filmProduction)).thenReturn(filmProduction);
        when(objectMapper.convertValue(filmProduction, FilmProductionDTO.class)).thenReturn(filmProductionDTO);

        FilmProductionDTO createdFilmProduction = filmProductionService.createFilmProduction(filmProductionDTO);

        assertNotNull(createdFilmProduction);
        assertEquals(filmProductionDTO.getId(), createdFilmProduction.getId());
        verify(filmProductionRepository, times(1)).save(filmProduction);
    }

    @Test
    void testGetFilmProductionById_Success() {

        filmProductionDTO = new FilmProductionDTO();
        filmProductionDTO.setId(1L);
        filmProductionDTO.setTitle("Test Film");

        filmProduction = new FilmProduction();
        filmProduction.setId(1L);
        filmProduction.setTitle("Test Film");

        when(filmProductionRepository.findById(1L)).thenReturn(Optional.of(filmProduction));
        when(objectMapper.convertValue(filmProduction, FilmProductionDTO.class)).thenReturn(filmProductionDTO);

        FilmProductionDTO result = filmProductionService.getFilmProductionById(1L);

        assertNotNull(result);
        assertEquals("Test Film", result.getTitle());
        verify(filmProductionRepository, times(1)).findById(1L);
    }

    @Test
    void testGetFilmProductionById_NotFound() {

        filmProductionDTO = new FilmProductionDTO();
        filmProductionDTO.setId(1L);
        filmProductionDTO.setTitle("Test Film");

        filmProduction = new FilmProduction();
        filmProduction.setId(1L);
        filmProduction.setTitle("Test Film");

        when(filmProductionRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(FilmProductionNotFoundException.class, () -> filmProductionService.getFilmProductionById(1L));
        verify(filmProductionRepository, times(1)).findById(1L);
    }
}