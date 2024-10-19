package com.film_production.demo.exceptions;

public class CastMemberNotFoundException extends RuntimeException{

    public static final String DEFAULT_MESSAGE = "Cast Member not found with id: ";

    public CastMemberNotFoundException(Long id) {
        super(DEFAULT_MESSAGE + id);
    }
}