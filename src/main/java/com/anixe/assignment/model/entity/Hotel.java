package com.anixe.assignment.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Hotel extends AbstractEntity {

    @Column(name = "NAME")
    private String name;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "STAR_RATING")
    private int starRating;
    @OneToMany(targetEntity = Booking.class, fetch = FetchType.LAZY, mappedBy = "hotel")
    @Builder.Default
    private Set<Booking> bookings = new HashSet<>();

    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setHotel(this);
    }

    public void removeBooking(Booking booking){
        bookings.remove(booking);
        booking.setHotel(null);
    }

}
