package com.film_production.demo.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CastMemberDTO {

    private Long id;
    @NotBlank(message = "This field is mandatory.")
    private String role;
    @NotBlank(message = "This field is mandatory.")
    private String firstName;
    @NotBlank(message = "This field is mandatory.")
    private String lastName;
    @NotBlank(message = "This field is mandatory.")
    private Boolean availability;
}