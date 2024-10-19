package com.film_production.demo.services;

import com.film_production.demo.models.dtos.CastMemberDTO;

import java.util.List;

public interface CastMemberService {

    CastMemberDTO createCastMember(CastMemberDTO castMemberDTO);

    CastMemberDTO getCastMemberById(Long id);

    List<CastMemberDTO> getAllCastMembers();

    CastMemberDTO updateCastMemberById(Long id, CastMemberDTO castMemberDTO);

    void deleteCastMemberById(Long id);
}