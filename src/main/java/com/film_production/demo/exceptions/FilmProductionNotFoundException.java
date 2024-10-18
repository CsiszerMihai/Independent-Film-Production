package com.film_production.demo.exceptions;

public class FilmProductionNotFoundException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "Film Production not found with id: ";

    public FilmProductionNotFoundException(Long id) {
        super(DEFAULT_MESSAGE + id);
    }
}