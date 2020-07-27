package com.anixe.assignment.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class Booking {

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
