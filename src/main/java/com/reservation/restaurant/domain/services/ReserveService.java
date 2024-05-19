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
import java.time.LocalDateTime;
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

        reserveValidation.validateReserveDate(addReserveDto.reserveEffectiveDateStart());

        LocalDateTime reserveEffectiveDateEnd = addReserveDto.reserveEffectiveDateStart().plusHours(2);
        List<SpotModel> spotsAvailable = spotRepository.findAvailableSpots(addReserveDto.reserveEffectiveDateStart(), reserveEffectiveDateEnd);
        if (!spotsAvailable.isEmpty()){

            reserveModel.setSpot(spotsAvailable.get(0));
            reserveModel.setReserveStatus(ReserveStatus.PENDING);
            reserveModel.setReserveDate(LocalDate.now());
            reserveModel.setReserveEffectiveDateEnd(reserveEffectiveDateEnd);
            return new ShowReserveDto(reserveRepository.save(reserveModel));
        } else {
            throw new InvalidReserveException("Sorry, we don't have any spot available on this hour, please try again later or with another hour!");
        }
    }
}
