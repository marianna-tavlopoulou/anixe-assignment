package com.anixe.assignment.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

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
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Booking> bookings = new HashSet<>();

    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setHotel(this);
    }

}
