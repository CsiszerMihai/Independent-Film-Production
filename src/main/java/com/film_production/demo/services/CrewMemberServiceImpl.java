package com.film_production.demo.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.film_production.demo.exceptions.CrewMemberNotFoundException;
import com.film_production.demo.exceptions.FilmProductionNotFoundException;
import com.film_production.demo.models.dtos.CrewMemberDTO;
import com.film_production.demo.models.dtos.FilmProductionDTO;
import com.film_production.demo.models.entities.CrewMember;
import com.film_production.demo.models.entities.FilmProduction;
import com.film_production.demo.repositories.CrewMemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrewMemberServiceImpl implements CrewMemberService {

    private static final Logger log = LoggerFactory.getLogger(CrewMemberServiceImpl.class);
    private final ObjectMapper objectMapper;
    private final CrewMemberRepository crewMemberRepository;

    public CrewMemberServiceImpl(ObjectMapper objectMapper, CrewMemberRepository crewMemberRepository) {
        this.objectMapper = objectMapper;
        this.crewMemberRepository = crewMemberRepository;
    }

    @Override
    public CrewMemberDTO createCrewMember(CrewMemberDTO crewMemberDTO) {
        CrewMember crewMemberEntity = objectMapper.convertValue(crewMemberDTO, CrewMember.class);
        CrewMember crewMemberEntityResponse = crewMemberRepository.save(crewMemberEntity);
        log.info("Crew Member with id {} was saved.", crewMemberEntityResponse.getId());

        return objectMapper.convertValue(crewMemberEntityResponse, CrewMemberDTO.class);
    }

    @Override
    public CrewMemberDTO getCrewMemberById(Long id) {
        CrewMember crewMember = crewMemberRepository.findById(id)
                .orElseThrow(() -> new CrewMemberNotFoundException(id));

        return objectMapper.convertValue(crewMember, CrewMemberDTO.class);
    }

    @Override
    public List<CrewMemberDTO> getAllCrewMembers() {
        List<CrewMember> crewMembers = crewMemberRepository.findAll();

        return crewMembers.stream()
                .map(crewMember -> objectMapper.convertValue(crewMember, CrewMemberDTO.class))
                .toList();
    }

    @Override
    public CrewMemberDTO updateCrewMemberById(Long id, CrewMemberDTO crewMemberDTO) {
        if (id == null) {
            throw new IllegalArgumentException("Crew Member ID cannot be null.");
        }
        CrewMember existingCrewMember = crewMemberRepository.findById(id).
                orElseThrow(() -> new CrewMemberNotFoundException(id));

        updateExistingCrewMember(existingCrewMember, crewMemberDTO);
        CrewMember updatedCrewMember = crewMemberRepository.save(existingCrewMember);
        log.info("Crew Member with id {} was updated", updatedCrewMember.getId());

        return objectMapper.convertValue(updatedCrewMember, CrewMemberDTO.class);
    }

    private void updateExistingCrewMember(CrewMember existingCrewMember, CrewMemberDTO crewMemberDTO) {
        existingCrewMember.setRole(crewMemberDTO.getRole());
        existingCrewMember.setFirstName(crewMemberDTO.getFirstName());
        existingCrewMember.setLastName(crewMemberDTO.getLastName());
        existingCrewMember.setEmail(crewMemberDTO.getEmail());
        existingCrewMember.setPhoneNumber(crewMemberDTO.getPhoneNumber());
        existingCrewMember.setAvailability(crewMemberDTO.getAvailability());
    }

    @Override
    public void deleteCrewMemberById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Crew Member ID cannot be null.");
        }
        CrewMember existingCrewMember = crewMemberRepository.findById(id)
                .orElseThrow(() -> new CrewMemberNotFoundException(id));

        crewMemberRepository.delete(existingCrewMember);
        log.info("Crew Member with id {} was deleted", id);
    }
}