package com.film_production.demo.repositories;

import com.film_production.demo.models.entities.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {

    List<CrewMember> findByAvailability(Boolean availability);

    List<CrewMember> findByRole(String role);
}