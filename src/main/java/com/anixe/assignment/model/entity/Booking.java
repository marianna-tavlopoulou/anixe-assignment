package com.anixe.assignment.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true, exclude="hotel")
public class Booking extends AbstractEntity {

    @JoinColumn(name = "HOTEL_ID")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @NotNull
    private Hotel hotel;

    @Column(name = "CUSTOMER_NAME")
    private String customerName;

    @Column(name = "CUSTOMER_SURNAME")
    private String customerSurname;

    @Column(name = "PAX_NUMBER")
    private String paxNumber;

    @Column(name = "PRICE_AMOUNT")
    private BigDecimal amount;

    @Column(name = "PRICE_CURRENCY")
    private String currency;

}
