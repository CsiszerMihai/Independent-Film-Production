package com.film_production.demo.repositories;

import com.film_production.demo.models.entities.CastMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CastMemberRepository extends JpaRepository<CastMember, Long> {

//    List<CastMember> findByAvailability(Boolean availability);
//
//    List<CastMember> findByRole(String role);
}