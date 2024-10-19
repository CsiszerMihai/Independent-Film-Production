package com.film_production.demo.services;

import com.film_production.demo.models.dtos.CrewMemberDTO;

import java.util.List;

public interface CrewMemberService {

    CrewMemberDTO createCrewMember(CrewMemberDTO crewMemberDTO);

    CrewMemberDTO getCrewMemberById(Long id);

    List<CrewMemberDTO> getAllCrewMembers();

    CrewMemberDTO updateCrewMemberById(Long id, CrewMemberDTO crewMemberDTO);

    void deleteCrewMemberById(Long id);
}
