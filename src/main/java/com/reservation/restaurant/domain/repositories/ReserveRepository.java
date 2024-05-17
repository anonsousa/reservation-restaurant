package com.reservation.restaurant.domain.repositories;

import com.reservation.restaurant.domain.models.ReserveModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<ReserveModel, Long> {
}
