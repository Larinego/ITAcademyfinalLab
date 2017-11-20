package com.larinego.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Class Item
 *
 * Created by yslabko on 07/01/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @Access(AccessType.FIELD)
    @ManyToOne
    private Product product;

    @Access(AccessType.PROPERTY)
    @Column
    private int quantity;

    @Access(AccessType.PROPERTY)
    @Column
    private double selledPrice;

    @Access(AccessType.FIELD)
    @ManyToOne
    private UserOrder userOrder;

}
