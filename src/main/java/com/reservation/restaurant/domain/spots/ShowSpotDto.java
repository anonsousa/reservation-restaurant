package com.reservation.restaurant.domain.spots;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record ShowSpotDto(

        Long idTable,

        @Positive(message = "Only positive numbers for capacity!")
        Integer capacity,

        @NotBlank(message = "Description field can't be blank!")
        String description
) {
    public ShowSpotDto(SpotModel spotModel){
        this(
                spotModel.getIdTable(),
                spotModel.getCapacity(),
                spotModel.getDescription()
        );
    }
}
