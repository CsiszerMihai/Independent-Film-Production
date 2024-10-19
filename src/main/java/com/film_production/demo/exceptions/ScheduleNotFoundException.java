package com.film_production.demo.exceptions;

public class ScheduleNotFoundException extends RuntimeException{

    public static final String DEFAULT_MESSAGE = "Schedule not found with id: ";

    public ScheduleNotFoundException(Long id) {
        super(DEFAULT_MESSAGE + id);
    }
}