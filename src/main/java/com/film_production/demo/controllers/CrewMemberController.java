package com.film_production.demo.controllers;

import com.film_production.demo.models.dtos.CrewMemberDTO;
import com.film_production.demo.services.CrewMemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CrewMemberController {

    private final CrewMemberService crewMemberService;

    public CrewMemberController(CrewMemberService crewMemberService) {
        this.crewMemberService = crewMemberService;
    }

    @PostMapping("/api/crew-member")
    public ResponseEntity<CrewMemberDTO> createCrewMember(@RequestBody CrewMemberDTO crewMemberDTO) {
        CrewMemberDTO createdCrewMember = crewMemberService.createCrewMember(crewMemberDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCrewMember);
    }

    @GetMapping("/api/crew-member/{id}")
    public ResponseEntity<CrewMemberDTO> getCrewMemberById(@PathVariable Long id) {
        CrewMemberDTO crewMember = crewMemberService.getCrewMemberById(id);
        return ResponseEntity.ok(crewMember);
    }

    @GetMapping("/api/crew-member")
    public ResponseEntity<List<CrewMemberDTO>> getAllCrewMembers() {
        List<CrewMemberDTO> crewMembers = crewMemberService.getAllCrewMembers();
        return ResponseEntity.ok(crewMembers);
    }

    @PutMapping("/api/crew-member/{id}")
    public ResponseEntity<CrewMemberDTO> updateCrewMemberById(@PathVariable Long id, @RequestBody CrewMemberDTO crewMemberDTO) {
        CrewMemberDTO updatedCrewMember = crewMemberService.updateCrewMemberById(id, crewMemberDTO);
        return ResponseEntity.ok(updatedCrewMember);
    }

    @DeleteMapping("/api/crew-member/{id}")
    public ResponseEntity<Void> deleteCrewMemberById(@PathVariable Long id) {
        crewMemberService.deleteCrewMemberById(id);
        return ResponseEntity.noContent().build();
    }
}