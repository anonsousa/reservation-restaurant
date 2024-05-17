package com.reservation.restaurant.controllers;


import com.reservation.restaurant.domain.dto.AddSpotDto;
import com.reservation.restaurant.domain.dto.ShowSpotDto;
import com.reservation.restaurant.domain.dto.UpdateSpotDto;
import com.reservation.restaurant.domain.services.SpotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SpotController {
    @Autowired
    private SpotService spotService;


    @PostMapping("/spot")
    public ResponseEntity save(@RequestBody @Valid AddSpotDto addSpotDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(spotService.save(addSpotDto));
    }

    @GetMapping("/spot")
    public ResponseEntity<Page<ShowSpotDto>> findAllSpots(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(spotService.findAll(pageable));
    }

    @GetMapping("/spot/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(spotService.findOneId(id));
    }

    @PutMapping("/spot")
    public ResponseEntity updateSpot(@RequestBody @Valid UpdateSpotDto updateSpotDto){
        return ResponseEntity.status(HttpStatus.OK).body(spotService.update(updateSpotDto));
    }

    @DeleteMapping("/spot/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        spotService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Spot deleted sucessfully!");
    }
}
