package com.larinego.dao.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(exclude = "items")
@ToString(exclude = "items")
public class UserOrder {

    @Id
    @GeneratedValue
    @Column
    private Long userOrderId;

    @Access(AccessType.PROPERTY)
    @Column
    @CreationTimestamp
    private Date date;

    @Access(AccessType.PROPERTY)
    @Column
    private double total;

    @Access(AccessType.FIELD)
    @OneToMany(mappedBy = "userOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @Fetch(value = FetchMode.JOIN)
    private List<Item> items = new ArrayList<>(0);

    @Access(AccessType.FIELD)
    @ManyToOne
    private User user;

}
