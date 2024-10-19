package com.film_production.demo.exceptions;

public class ScriptNotFoundException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "Script not found with id: ";

    public ScriptNotFoundException(Long id) {
        super(DEFAULT_MESSAGE + id);
    }
}