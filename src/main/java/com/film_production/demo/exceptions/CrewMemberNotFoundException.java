package com.film_production.demo.exceptions;

public class CrewMemberNotFoundException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "Crew Member not found with id: ";

    public CrewMemberNotFoundException(Long id) {
        super(DEFAULT_MESSAGE + id);
    }
}