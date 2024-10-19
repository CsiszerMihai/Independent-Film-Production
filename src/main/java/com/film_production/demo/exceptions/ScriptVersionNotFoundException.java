package com.film_production.demo.exceptions;

public class ScriptVersionNotFoundException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "Script Version not found with id: ";

    public ScriptVersionNotFoundException(Long id) {
        super(DEFAULT_MESSAGE + id);
    }
}