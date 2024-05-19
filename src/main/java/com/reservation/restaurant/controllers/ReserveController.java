package com.reservation.restaurant.controllers;


import com.reservation.restaurant.domain.dto.AddReserveDto;
import com.reservation.restaurant.domain.dto.ShowReserveDto;
import com.reservation.restaurant.domain.services.ReserveService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReserveController {

    @Autowired
    private ReserveService reserveService;

    @PostMapping("/reserve")
    public ResponseEntity save(@RequestBody @Valid AddReserveDto addReserveDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(reserveService.save(addReserveDto));
    }

    @PostMapping("/reserve/start/{id}")
    public ResponseEntity startReserve(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(reserveService.confirmReserve(id));
    }

    @PostMapping("/reserve/cancel/{id}")
    public ResponseEntity cancelReserve(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(reserveService.cancelReserve(id));
    }

    @GetMapping("/reserve/{id}")
    public ResponseEntity findReserveById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(reserveService.findReserveById(id));
    }

    @GetMapping("/reserve")
    public ResponseEntity<Page<ShowReserveDto>> findAllReserves(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(reserveService.findAllReserves(pageable));
    }

    @GetMapping("/reserve/spot/{id}")
    public ResponseEntity<Page<ShowReserveDto>> findAllReserveBySpot(@PathVariable Long id, Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(reserveService.findAllReserveByIdSpot(id, pageable));
    }

    @GetMapping("/reserve/owner/{cpf}")
    public ResponseEntity<Page<ShowReserveDto>> findByReserveByCpf(@PathVariable String cpf, Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(reserveService.findReserveByOwner(cpf, pageable));
    }

    @DeleteMapping("/reserve/{id}")
    public ResponseEntity deleteReserveById(@PathVariable Long id){
        reserveService.deleteReserveById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Reserve deleted sucessfully");
    }
}
