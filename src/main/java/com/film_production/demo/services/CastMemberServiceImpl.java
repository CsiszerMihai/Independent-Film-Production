package com.film_production.demo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_production.demo.exceptions.CastMemberNotFoundException;
import com.film_production.demo.models.dtos.CastMemberDTO;
import com.film_production.demo.models.entities.CastMember;
import com.film_production.demo.repositories.CastMemberRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CastMemberServiceImpl implements CastMemberService {

    private static final Logger log = LoggerFactory.getLogger(CastMemberServiceImpl.class);
    private final ObjectMapper objectMapper;
    private final CastMemberRepository castMemberRepository;

    public CastMemberServiceImpl(ObjectMapper objectMapper, CastMemberRepository castMemberRepository) {
        this.objectMapper = objectMapper;
        this.castMemberRepository = castMemberRepository;
    }

    @Override
    public CastMemberDTO createCastMember(CastMemberDTO castMemberDTO) {
        CastMember castMemberEntity = objectMapper.convertValue(castMemberDTO, CastMember.class);
        CastMember castMemberEntityResponse = castMemberRepository.save(castMemberEntity);
        log.info("Cast Member with id {} was saved.", castMemberEntityResponse.getId());

        return objectMapper.convertValue(castMemberEntityResponse, CastMemberDTO.class);
    }

    @Override
    public CastMemberDTO getCastMemberById(Long id) {
        CastMember castMember = castMemberRepository.findById(id)
                .orElseThrow(() -> new CastMemberNotFoundException(id));

        return objectMapper.convertValue(castMember, CastMemberDTO.class);
    }

    @Override
    public List<CastMemberDTO> getAllCastMembers() {
        List<CastMember> castMembers = castMemberRepository.findAll();

        return castMembers.stream()
                .map(castMember -> objectMapper.convertValue(castMember, CastMemberDTO.class))
                .toList();
    }

    @Override
    public CastMemberDTO updateCastMemberById(Long id, CastMemberDTO castMemberDTO) {
        if (id == null) {
            throw new IllegalArgumentException("Cast Member ID cannot be null.");
        }
        CastMember existingCastMember = castMemberRepository.findById(id).
                orElseThrow(() -> new CastMemberNotFoundException(id));

        updateExistingCastMember(existingCastMember, castMemberDTO);
        CastMember updatedCastMember = castMemberRepository.save(existingCastMember);
        log.info("CastMember with id {} was updated", updatedCastMember.getId());

        return objectMapper.convertValue(updatedCastMember, CastMemberDTO.class);
    }

    private void updateExistingCastMember(CastMember existingCastMember, CastMemberDTO castMemberDTO) {
        existingCastMember.setRole(castMemberDTO.getRole());
        existingCastMember.setFirstName(castMemberDTO.getFirstName());
        existingCastMember.setLastName(castMemberDTO.getLastName());
        existingCastMember.setAvailability(castMemberDTO.getAvailability());
    }

    @Override
    public void deleteCastMemberById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Cast Member ID cannot be null.");
        }
        CastMember existingCastMember = castMemberRepository.findById(id)
                .orElseThrow(() -> new CastMemberNotFoundException(id));

        castMemberRepository.delete(existingCastMember);
        log.info("Cast Member with id {} was deleted", id);
    }
}
