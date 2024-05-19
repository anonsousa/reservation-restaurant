package com.reservation.restaurant.domain.validations;

import com.reservation.restaurant.domain.models.SpotModel;
import com.reservation.restaurant.domain.repositories.SpotRepository;
import com.reservation.restaurant.infra.InvalidReserveException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReserveValidation {
    @Autowired
    private SpotRepository spotRepository;

    public void validateReserveDate(LocalDateTime reserveEffectiveDate){
        validateDayOfWeek(reserveEffectiveDate.getDayOfWeek());
        validateTimeOfDay(reserveEffectiveDate.toLocalTime());

    }

    private void validateDayOfWeek(DayOfWeek dayOfWeek) {
        if (dayOfWeek == DayOfWeek.MONDAY || dayOfWeek == DayOfWeek.TUESDAY) {
            throw new InvalidReserveException("Reservations cannot be scheduled on Monday or Tuesday.");
        }
    }

    private void validateTimeOfDay(LocalTime reserveTime) {
        LocalTime startTime = LocalTime.of(18, 0); // 18:00
        LocalTime endTime = LocalTime.of(22, 0); // 22:00

        if (reserveTime.isBefore(startTime) || reserveTime.isAfter(endTime)) {
            throw new InvalidReserveException("Reservations can only be scheduled between 18:00 and 22:00.");
        }
    }


}
