package com.anixe.assignment.repository;

import com.anixe.assignment.model.entity.Booking;
import com.anixe.assignment.model.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {

    @Query("select sum(b.amount) from Booking b where b.hotel = :hotel")
    BigDecimal getSumOfPRiceAmountForGivenHotel(@Param("hotel") Hotel hotel);
}
