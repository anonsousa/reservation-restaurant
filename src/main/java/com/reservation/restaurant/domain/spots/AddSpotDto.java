package com.reservation.restaurant.domain.spots;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record AddSpotDto(

        @Positive(message = "Only positive numbers for capacity!")
        Integer capacity,

        @NotBlank(message = "Description field can't be blank!")
        String description
) { }
