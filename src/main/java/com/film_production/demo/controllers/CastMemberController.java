package com.film_production.demo.controllers;

import com.film_production.demo.models.dtos.CastMemberDTO;
import com.film_production.demo.services.CastMemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping("/api/cast-members")
@RestController
public class CastMemberController {

    private final CastMemberService castMemberService;

    public CastMemberController(CastMemberService castMemberService) {
        this.castMemberService = castMemberService;
    }

    @PostMapping
    public ResponseEntity<CastMemberDTO> createCastMember(@RequestBody CastMemberDTO castMemberDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(castMemberService.createCastMember(castMemberDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CastMemberDTO> getCastMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(castMemberService.getCastMemberById(id));
    }

    @GetMapping
    public ResponseEntity<List<CastMemberDTO>> getAllCastMembers() {
        return ResponseEntity.ok(castMemberService.getAllCastMembers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CastMemberDTO> updateExistingCastMemberById(@PathVariable Long id, @RequestBody CastMemberDTO castMemberDTO) {
        return ResponseEntity.ok(castMemberService.updateCastMemberById(id, castMemberDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCastMemberById(@PathVariable Long id) {
        castMemberService.deleteCastMemberById(id);
        return ResponseEntity.noContent().build();
    }
}