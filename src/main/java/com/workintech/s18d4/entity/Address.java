package com.workintech.s18d4.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String street;

    @Column(name = "no")
    private Integer no;

    private String city;
    private String country;

    @Column(nullable = true)
    private String description;

    @OneToOne(mappedBy = "address")
    @ToString.Exclude
    private Customer customer;
}
