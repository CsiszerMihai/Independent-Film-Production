package com.film_production.demo.controllers;

import com.film_production.demo.models.dtos.CrewMemberDTO;
import com.film_production.demo.services.CrewMemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping("/api/crew-members")
@RestController
public class CrewMemberController {

    private final CrewMemberService crewMemberService;

    public CrewMemberController(CrewMemberService crewMemberService) {
        this.crewMemberService = crewMemberService;
    }

    @PostMapping
    public ResponseEntity<CrewMemberDTO> createCrewMember(@RequestBody CrewMemberDTO crewMemberDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(crewMemberService.createCrewMember(crewMemberDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CrewMemberDTO> getCrewMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(crewMemberService.getCrewMemberById(id));
    }

    @GetMapping
    public ResponseEntity<List<CrewMemberDTO>> getAllCrewMembers() {
        return ResponseEntity.ok(crewMemberService.getAllCrewMembers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CrewMemberDTO> updateCrewMemberById(@PathVariable Long id, @RequestBody CrewMemberDTO crewMemberDTO) {
        return ResponseEntity.ok(crewMemberService.updateCrewMemberById(id, crewMemberDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCrewMemberById(@PathVariable Long id) {
        crewMemberService.deleteCrewMemberById(id);
        return ResponseEntity.noContent().build();
    }
}