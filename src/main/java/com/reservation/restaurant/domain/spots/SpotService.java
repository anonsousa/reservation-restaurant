package com.reservation.restaurant.domain.spots;

import com.reservation.restaurant.infra.ItemNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpotService {
    @Autowired
    SpotRepository spotRepository;


    @Transactional
    public ShowSpotDto save(AddSpotDto addSpotDto){
        SpotModel spotModel = new SpotModel();
        BeanUtils.copyProperties(addSpotDto, spotModel);
        return new ShowSpotDto(spotRepository.save(spotModel));
    }

    public ShowSpotDto findOneId(Long id){
        Optional<SpotModel> spotModelOptional = spotRepository.findById(id);
        if (spotModelOptional.isPresent()){
            return new ShowSpotDto(spotModelOptional.get());
        } else {
            throw new ItemNotFoundException("Spot not found.");
        }
    }

    public Page<ShowSpotDto> findAll(Pageable pageable){
        Page<SpotModel> spotModelPage = spotRepository.findAll(pageable);
        return spotModelPage.map(ShowSpotDto::new);
    }
}
