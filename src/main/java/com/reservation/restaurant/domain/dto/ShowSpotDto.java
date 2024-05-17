package com.reservation.restaurant.domain.dto;

import com.reservation.restaurant.domain.models.SpotModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ShowSpotDto(

        Long idTable,

        @NotNull
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
