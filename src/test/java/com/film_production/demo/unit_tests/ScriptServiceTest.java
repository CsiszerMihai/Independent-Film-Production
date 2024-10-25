package com.film_production.demo.unit_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_production.demo.exceptions.ScriptNotFoundException;
import com.film_production.demo.models.dtos.ScriptDTO;
import com.film_production.demo.models.entities.Script;
import com.film_production.demo.repositories.ScriptRepository;
import com.film_production.demo.services.ScriptServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ScriptServiceTest {

    @Mock
    private ScriptRepository scriptRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private ScriptServiceImpl scriptService;

    private ScriptDTO scriptDTO;
    private Script script;

    @Test
    void testCreateScript() {
        scriptDTO = new ScriptDTO();
        scriptDTO.setId(1L);
        scriptDTO.setContent("Test Content");

        script = new Script();
        script.setId(1L);
        script.setContent("Test Content");

        when(objectMapper.convertValue(scriptDTO, Script.class)).thenReturn(script);
        when(scriptRepository.save(script)).thenReturn(script);
        when(objectMapper.convertValue(script, ScriptDTO.class)).thenReturn(scriptDTO);

        ScriptDTO createdScript = scriptService.createScript(scriptDTO);

        assertNotNull(createdScript);
        assertEquals(scriptDTO.getId(), createdScript.getId());
        verify(scriptRepository, times(1)).save(script);
    }

    @Test
    void testGetScriptById_Success() {
        scriptDTO = new ScriptDTO();
        scriptDTO.setId(1L);
        scriptDTO.setContent("Test Content");

        script = new Script();
        script.setId(1L);
        script.setContent("Test Content");

        when(scriptRepository.findById(1L)).thenReturn(Optional.of(script));
        when(objectMapper.convertValue(script, ScriptDTO.class)).thenReturn(scriptDTO);

        ScriptDTO result = scriptService.getScriptById(1L);

        assertNotNull(result);
        assertEquals("Test Content", result.getContent());
        verify(scriptRepository, times(1)).findById(1L);
    }

    @Test
    void testGetScriptById_NotFound() {
        scriptDTO = new ScriptDTO();
        scriptDTO.setId(1L);
        scriptDTO.setContent("Test Content");

        script = new Script();
        script.setId(1L);
        script.setContent("Test Content");

        when(scriptRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ScriptNotFoundException.class, () -> scriptService.getScriptById(1L));
        verify(scriptRepository, times(1)).findById(1L);
    }
}