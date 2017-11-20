package com.larinego.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Access(AccessType.PROPERTY)
    @Column
    private String supplier;

    @Access(AccessType.PROPERTY)
    @Column
    private String model;

    @Access(AccessType.PROPERTY)
    @Column
    private Double price;
}
