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
import java.util.stream.Collectors;

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
        //In this part im creating a new reserveModel and adding the properties from addReserveModel to the new reserverModel
        ReserveModel reserveModel = new ReserveModel();
        BeanUtils.copyProperties(addReserveDto, reserveModel);

        //In this por im validating if the reserve are allowed to be done, checking if isnt monday and tuesday and also hours too
        reserveValidation.validateReserveDate(addReserveDto.reserveEffectiveDateStart());

        //Find if we have spots available by the number of people
        List<SpotModel> spotsAvailableByCapacity = spotRepository.findByCapacity(addReserveDto.peopleNumber());

        if (!spotsAvailableByCapacity.isEmpty()){

            //Creating a list with the return of the method findByCapacity, only picking the ids from spots
            List<Long> spotIds = spotsAvailableByCapacity.stream()
                    .map(SpotModel::getIdTable)
                    .collect(Collectors.toList());

            //Business Rule, each spot only can be schedulled for 2 hours.
            LocalDateTime reserveEffectiveDateEnd = addReserveDto.reserveEffectiveDateStart().plusHours(2);

            //This query will return wich spots are available on respective hour received from the frontend user
            List<SpotModel> spotsAvailable = spotRepository.findAvailableSpots(spotIds, addReserveDto.reserveEffectiveDateStart(), reserveEffectiveDateEnd);
            if (!spotsAvailable.isEmpty()){

                //Using the Setters for Add some necessary data
                reserveModel.setSpot(spotsAvailable.get(0));
                reserveModel.setReserveStatus(ReserveStatus.PENDING);
                reserveModel.setReserveDate(LocalDate.now());
                reserveModel.setReserveEffectiveDateEnd(reserveEffectiveDateEnd);

                return new ShowReserveDto(reserveRepository.save(reserveModel));
            } else {
                throw new InvalidReserveException("Sorry, we don't have any spot available on this hour, please try again later or with another hour!");
            }
        } else {
            throw new InvalidReserveException("Sorry, we don't have any spot available for this capacity");
        }
    }
}
