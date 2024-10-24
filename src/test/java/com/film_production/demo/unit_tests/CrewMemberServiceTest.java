package com.film_production.demo.unit_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_production.demo.exceptions.CrewMemberNotFoundException;
import com.film_production.demo.models.dtos.CrewMemberDTO;
import com.film_production.demo.models.entities.CrewMember;
import com.film_production.demo.repositories.CrewMemberRepository;
import com.film_production.demo.services.CrewMemberServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CrewMemberServiceTest {

    @Mock
    private CrewMemberRepository crewMemberRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private CrewMemberServiceImpl crewMemberService;

    private CrewMemberDTO crewMemberDTO;
    private CrewMember crewMember;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        crewMemberDTO = new CrewMemberDTO();
        crewMemberDTO.setId(1L);
        crewMemberDTO.setRole("Test Role");

        crewMember = new CrewMember();
        crewMember.setId(1L);
        crewMember.setRole("Test Role");
    }

    @Test
    void testCreateCrewMember() {
        when(objectMapper.convertValue(crewMemberDTO, CrewMember.class)).thenReturn(crewMember);
        when(crewMemberRepository.save(crewMember)).thenReturn(crewMember);
        when(objectMapper.convertValue(crewMember, CrewMemberDTO.class)).thenReturn(crewMemberDTO);

        CrewMemberDTO createdCrewMember = crewMemberService.createCrewMember(crewMemberDTO);

        assertNotNull(createdCrewMember);
        assertEquals(crewMemberDTO.getId(), createdCrewMember.getId());
        verify(crewMemberRepository, times(1)).save(crewMember);
    }

    @Test
    void testGetCrewMemberById_Success() {
        when(crewMemberRepository.findById(1L)).thenReturn(Optional.of(crewMember));
        when(objectMapper.convertValue(crewMember, CrewMemberDTO.class)).thenReturn(crewMemberDTO);

        CrewMemberDTO result = crewMemberService.getCrewMemberById(1L);

        assertNotNull(result);
        assertEquals("Test Role", result.getRole());
        verify(crewMemberRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCrewMemberById_NotFound() {
        when(crewMemberRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CrewMemberNotFoundException.class, () -> crewMemberService.getCrewMemberById(1L));
        verify(crewMemberRepository, times(1)).findById(1L);
    }
}