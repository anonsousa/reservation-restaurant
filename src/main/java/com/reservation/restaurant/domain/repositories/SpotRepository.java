package com.reservation.restaurant.domain.repositories;

import com.reservation.restaurant.domain.models.SpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotRepository extends JpaRepository<SpotModel, Long> {
}
