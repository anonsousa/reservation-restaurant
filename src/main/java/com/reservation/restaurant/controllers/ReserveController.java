package com.reservation.restaurant.controllers;


import com.reservation.restaurant.domain.dto.AddReserveDto;
import com.reservation.restaurant.domain.services.ReserveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @PostMapping("/reserve")
    public ResponseEntity save(@RequestBody @Valid AddReserveDto addReserveDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(reserveService.save(addReserveDto));
    }
}
