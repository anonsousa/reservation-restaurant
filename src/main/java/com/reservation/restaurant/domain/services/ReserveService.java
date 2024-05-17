package com.reservation.restaurant.domain.services;

import com.reservation.restaurant.domain.dto.AddReserveDto;
import com.reservation.restaurant.domain.dto.ShowReserveDto;
import com.reservation.restaurant.domain.models.ReserveModel;
import com.reservation.restaurant.domain.models.ReserveStatus;
import com.reservation.restaurant.domain.repositories.ReserveRepository;
import com.reservation.restaurant.domain.repositories.SpotRepository;
import com.reservation.restaurant.domain.validations.ReserveValidation;
import com.reservation.restaurant.infra.InvalidReserveException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReserveService {
    @Autowired
    private ReserveRepository reserveRepository;

    @Autowired
    private SpotRepository spotRepository;

    @Autowired
    private ReserveValidation reserveValidation;

    private ShowReserveDto save(AddReserveDto addReserveDto){
        ReserveModel reserveModel = new ReserveModel();
        BeanUtils.copyProperties(addReserveDto, reserveModel);
        reserveValidation.validateReserveDate(addReserveDto.reserveEffectiveDate());
        var capacity = spotRepository.findByCapacity(addReserveDto.peopleNumber());
        if (capacity.isEmpty()){
            throw new InvalidReserveException("Sorry, we dont have any table for the number of people!");
        } else {
            reserveModel.setSpot(capacity.get(0));
            reserveModel.setReserveStatus(ReserveStatus.PENDING);
            reserveModel.setReserveDate(LocalDate.now());
            return new ShowReserveDto(reserveRepository.save(reserveModel));
        }
    }
}
