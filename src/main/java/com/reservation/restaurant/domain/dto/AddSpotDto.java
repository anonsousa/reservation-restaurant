package com.reservation.restaurant.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AddSpotDto(

        @NotNull
        @Positive(message = "Only positive numbers for capacity!")
        Integer capacity,

        @NotBlank(message = "Description field can't be blank!")
        String description
) { }
