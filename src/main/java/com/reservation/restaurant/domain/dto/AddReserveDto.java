package com.reservation.restaurant.domain.dto;

import com.reservation.restaurant.domain.models.ReserveModel;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record AddReserveDto(

        @NotBlank(message = "Owner can't be blank!")
        @Size(min = 3, max = 90, message = "Min owner name size = 3, max = 90")
        String reserveOwner,

        @NotBlank(message = "Cpf can't be blank!")
        @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "CPF deve estar no formato 123.456.789-09")
        String reserveCpf,

        @NotNull(message = "People quantity can't be null!")
        @Positive
        Integer peopleNumber,

        @Future(message = "This field only accept future dates!")
        LocalDateTime reserveEffectiveDate
        ) {
}
