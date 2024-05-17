package com.reservation.restaurant.domain.dto;

import com.reservation.restaurant.domain.models.ReserveModel;
import com.reservation.restaurant.domain.models.ReserveStatus;
import com.reservation.restaurant.domain.models.SpotModel;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ShowReserveDto(
        Long idReserve,
        SpotModel spot,
        String reserveOwner,
        String reserveCpf,
        Integer peopleNumber,
        ReserveStatus reserveStatus,
        LocalDate reserveDate,
        LocalDateTime reserveEffectiveDate,
        String notes
) {
    public ShowReserveDto(ReserveModel reserveModel){
        this(
                reserveModel.getIdReserve(),
                reserveModel.getSpot(),
                reserveModel.getReserveOwner(),
                reserveModel.getReserveCpf(),
                reserveModel.getPeopleNumber(),
                reserveModel.getReserveStatus(),
                reserveModel.getReserveDate(),
                reserveModel.getReserveEffectiveDate(),
                reserveModel.getNotes()
        );
    }
}
