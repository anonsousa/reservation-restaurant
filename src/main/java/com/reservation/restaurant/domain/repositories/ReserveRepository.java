package com.reservation.restaurant.domain.repositories;

import com.reservation.restaurant.domain.models.ReserveModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<ReserveModel, Long> {

    @Query("SELECT r FROM ReserveModel r WHERE r.spot.idTable = :spotId")
    Page<ReserveModel> findBySpotId(@Param("spotId") Long spotId, Pageable pageable);

    @Query("SELECT r FROM ReserveModel r WHERE r.reserveCpf = :cpf")
    Page<ReserveModel> findByReserveCpf(@Param("cpf") String cpf, Pageable pageable);
}