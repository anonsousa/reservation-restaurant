package com.reservation.restaurant.domain.services;

import com.reservation.restaurant.domain.dto.AddReserveDto;
import com.reservation.restaurant.domain.dto.ShowReserveDto;
import com.reservation.restaurant.domain.models.ReserveModel;
import com.reservation.restaurant.domain.models.ReserveStatus;
import com.reservation.restaurant.domain.models.SpotModel;
import com.reservation.restaurant.domain.repositories.ReserveRepository;
import com.reservation.restaurant.domain.repositories.SpotRepository;
import com.reservation.restaurant.domain.validations.ReserveValidation;
import com.reservation.restaurant.infra.InvalidReserveException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReserveService {
    @Autowired
    private ReserveRepository reserveRepository;

    @Autowired
    private SpotRepository spotRepository;

    @Autowired
    private ReserveValidation reserveValidation;

    @Transactional
    public ShowReserveDto save(AddReserveDto addReserveDto){

        ReserveModel reserveModel = new ReserveModel();
        BeanUtils.copyProperties(addReserveDto, reserveModel);

        reserveValidation.validateReserveDate(addReserveDto.reserveEffectiveDate());

        List<SpotModel> findReserve = reserveValidation.findAvailableSpots(addReserveDto.peopleNumber(), addReserveDto.reserveEffectiveDate());

        reserveModel.setSpot(findReserve.get(0));
        reserveModel.setReserveStatus(ReserveStatus.PENDING);
        reserveModel.setReserveDate(LocalDate.now());

        return new ShowReserveDto(reserveRepository.save(reserveModel));

    }
}
