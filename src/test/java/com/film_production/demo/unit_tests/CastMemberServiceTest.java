package com.film_production.demo.unit_tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_production.demo.exceptions.CastMemberNotFoundException;
import com.film_production.demo.models.dtos.CastMemberDTO;
import com.film_production.demo.models.entities.CastMember;
import com.film_production.demo.repositories.CastMemberRepository;
import com.film_production.demo.services.CastMemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CastMemberServiceTest {

    @Mock
    private CastMemberRepository castMemberRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private CastMemberServiceImpl castMemberService;

    private CastMemberDTO castMemberDTO;
    private CastMember castMember;

    @Test
    void testCreateCastMember() {
        castMemberDTO = new CastMemberDTO();
        castMemberDTO.setId(1L);
        castMemberDTO.setRole("Test Role");

        castMember = new CastMember();
        castMember.setId(1L);
        castMember.setRole("Test Role");

        when(objectMapper.convertValue(castMemberDTO, CastMember.class)).thenReturn(castMember);
        when(castMemberRepository.save(castMember)).thenReturn(castMember);
        when(objectMapper.convertValue(castMember, CastMemberDTO.class)).thenReturn(castMemberDTO);

        CastMemberDTO createdCastMember = castMemberService.createCastMember(castMemberDTO);

        assertNotNull(createdCastMember);
        assertEquals(castMemberDTO.getId(), createdCastMember.getId());
        verify(castMemberRepository, times(1)).save(castMember);
    }

    @Test
    void testGetCastMemberById_Success() {
        castMemberDTO = new CastMemberDTO();
        castMemberDTO.setId(1L);
        castMemberDTO.setRole("Test Role");

        castMember = new CastMember();
        castMember.setId(1L);
        castMember.setRole("Test Role");

        when(castMemberRepository.findById(1L)).thenReturn(Optional.of(castMember));
        when(objectMapper.convertValue(castMember, CastMemberDTO.class)).thenReturn(castMemberDTO);

        CastMemberDTO result = castMemberService.getCastMemberById(1L);

        assertNotNull(result);
        assertEquals("Test Role", result.getRole());
        verify(castMemberRepository, times(1)).findById(1L);
    }

    @Test
    void testGetCastMemberById_NotFound() {
        castMemberDTO = new CastMemberDTO();
        castMemberDTO.setId(1L);
        castMemberDTO.setRole("Test Role");

        castMember = new CastMember();
        castMember.setId(1L);
        castMember.setRole("Test Role");

        when(castMemberRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(CastMemberNotFoundException.class, () -> castMemberService.getCastMemberById(1L));
        verify(castMemberRepository, times(1)).findById(1L);
    }
}