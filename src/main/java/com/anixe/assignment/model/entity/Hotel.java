package com.anixe.assignment.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false)
public class Hotel  extends AbstractEntity {

    @Column(name = "NAME")
    private String name;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "STAR_RATING")
    private int starRating;
}
