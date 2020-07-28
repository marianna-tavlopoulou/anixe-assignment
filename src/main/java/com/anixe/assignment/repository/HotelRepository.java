package com.anixe.assignment.repository;

import com.anixe.assignment.model.entity.Hotel;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, String> {

    @Query("select h from Booking b inner join b.hotel h where b.customerSurname = upper(:surname)")
    Set<Hotel> getHotelsBookedByCustomer(@Param("surname") String surname);

//    Optional<Hotel> getByNameAndAddress(String name, String address);

    Optional<Hotel> getById(String id);

    @Query("select case when count(h)> 0 then true else false end from Hotel h where h.name = :name  and h.address = :address ")
    boolean hotelExists(@Param("name")String name, @Param("address") String address);
}
