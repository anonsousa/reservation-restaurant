package com.reservation.restaurant.domain.repositories;

import com.reservation.restaurant.domain.models.SpotModel;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SpotRepository extends JpaRepository<SpotModel, Long> {

    @Query("SELECT s FROM SpotModel s WHERE s.capacity = :capacity")
    List<SpotModel> findByCapacity(Integer capacity);

    @Query("SELECT s FROM SpotModel s WHERE s.id IN :spotIds AND s.id NOT IN " +
            "(SELECT r.spot.id FROM ReserveModel r WHERE " +
            "(r.reserveEffectiveDateStart <= :endDate AND r.reserveEffectiveDateEnd >= :startDate))")
    List<SpotModel> findAvailableSpots(@Param("spotIds") List<Long> spotIds,
                                       @Param("startDate") LocalDateTime startDate,
                                       @Param("endDate") LocalDateTime endDate);



}
