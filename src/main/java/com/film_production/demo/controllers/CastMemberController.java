package com.film_production.demo.controllers;

import com.film_production.demo.models.dtos.CastMemberDTO;
import com.film_production.demo.services.CastMemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CastMemberController {

    private final CastMemberService castMemberService;

    public CastMemberController(CastMemberService castMemberService) {
        this.castMemberService = castMemberService;
    }

    @PostMapping("/api/cast_member")
    public ResponseEntity<CastMemberDTO> createCastMember(@RequestBody CastMemberDTO castMemberDTO) {
        CastMemberDTO createdCastMember = castMemberService.createCastMember(castMemberDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCastMember);
    }

    @GetMapping("/api/cast_member/{id}")
    public ResponseEntity<CastMemberDTO> getCastMemberById(@PathVariable Long id) {
        CastMemberDTO castMember = castMemberService.getCastMemberById(id);
        return ResponseEntity.ok(castMember);
    }

    @GetMapping("/api/cast_member")
    public ResponseEntity<List<CastMemberDTO>> getAllCastMembers() {
        List<CastMemberDTO> castMembers = castMemberService.getAllCastMembers();
        return ResponseEntity.ok(castMembers);
    }

    @PutMapping("/api/cast_member/{id}")
    public ResponseEntity<CastMemberDTO> updateCastMemberById(@PathVariable Long id, @RequestBody CastMemberDTO castMemberDTO) {
        CastMemberDTO updatedCastMember = castMemberService.updateCastMemberById(id, castMemberDTO);
        return ResponseEntity.ok(updatedCastMember);
    }

    @DeleteMapping("/api/cast_member/{id}")
    public ResponseEntity<Void> deleteCastMemberById(@PathVariable Long id) {
        castMemberService.deleteCastMemberById(id);
        return ResponseEntity.noContent().build();
    }
}