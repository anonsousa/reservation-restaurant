package com.reservation.restaurant.domain.repositories;

import com.reservation.restaurant.domain.models.SpotModel;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpotRepository extends JpaRepository<SpotModel, Long> {

    @Query("SELECT s FROM SpotModel s WHERE s.capacity = :capacity")
    List<SpotModel> findByCapacity(Integer capacity);
}
