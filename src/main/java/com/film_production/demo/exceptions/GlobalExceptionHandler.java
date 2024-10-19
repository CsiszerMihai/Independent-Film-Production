package com.film_production.demo.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private final ObjectMapper objectMapper;

    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(FilmProductionNotFoundException.class)
    public ResponseEntity<String> filmProductionNotFoundException(FilmProductionNotFoundException filmProductionNotFoundException) {
        return new ResponseEntity<>(objectToString(Map.of("message", filmProductionNotFoundException.getMessage())), NOT_FOUND);
    }

    @ExceptionHandler(CrewMemberNotFoundException.class)
    public ResponseEntity<String> crewMemberNotFoundException(CrewMemberNotFoundException crewMemberNotFoundException) {
        return new ResponseEntity<>(objectToString(Map.of("message", crewMemberNotFoundException.getMessage())), NOT_FOUND);
    }

    @ExceptionHandler(CastMemberNotFoundException.class)
    public ResponseEntity<String> castMemberNotFoundException(CastMemberNotFoundException castMemberNotFoundException) {
        return  new ResponseEntity<>(objectToString(Map.of("message", castMemberNotFoundException.getMessage())), NOT_FOUND);
    }

    @ExceptionHandler(ScheduleNotFoundException.class)
    public ResponseEntity<String> ScheduleNotFoundException(ScheduleNotFoundException scheduleNotFoundException) {
        return new ResponseEntity<>(objectToString(Map.of("message", scheduleNotFoundException.getMessage())), NOT_FOUND);
    }

    @ExceptionHandler(ScriptNotFoundException.class)
    public ResponseEntity<String> ScriptNotFoundException(ScriptNotFoundException scriptNotFoundException) {
        return new ResponseEntity<>(objectToString(Map.of("message", scriptNotFoundException.getMessage())), NOT_FOUND);
    }

    @ExceptionHandler(ScriptVersionNotFoundException.class)
    public ResponseEntity<String> ScriptVersionNotFoundException(ScriptVersionNotFoundException scriptVersionNotFoundException) {
        return new ResponseEntity<>(objectToString(Map.of("message", scriptVersionNotFoundException.getMessage())), NOT_FOUND);
    }

    private String objectToString(Object response) {
        try {
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            log.error("Error processing response to string");
            return "Internal error";
        }
    }
}