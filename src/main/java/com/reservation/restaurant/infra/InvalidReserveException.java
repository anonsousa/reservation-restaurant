package com.reservation.restaurant.infra;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidReserveException extends IllegalArgumentException{

    public InvalidReserveException(String mesage){
        super(mesage);
    }
}
