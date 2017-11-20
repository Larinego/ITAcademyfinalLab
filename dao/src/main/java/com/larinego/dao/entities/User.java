package com.larinego.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;


/**
 * Class User
 *
 * Created by yslabko on 08/11/2017.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long userId;

    @Access(AccessType.PROPERTY)
    @Column
    @Pattern(regexp="^[A-Za-z]{2,25}$|^$",
            message="Name must be literal with no spaces from 2 to 25 characters")
    private String name;

    @Access(AccessType.PROPERTY)
    @Column
    @Pattern(regexp="^[A-Za-z]{2,25}$|^$",
            message="Surname must be literal with no spaces from 2 to 25 characters")
    private String surname;

    @Access(AccessType.PROPERTY)
    @Column
    @Pattern(regexp="^((375)|(80))?(29|33|44)[1-9]{1}[0-9]{6}$|^$",
            message="Phone number must be in (xxx)(xx)(xxxxxxx) format")
    private String phoneNumberFirst;

    @Access(AccessType.PROPERTY)
    @Column
    @Pattern(regexp="^((375)|(80))?(29|33|44)[1-9]{1}[0-9]{6}$|^$",
            message="Phone number must be in (xxx)(xx)(xxxxxxx) format")
    private String phoneNumberSecond;

    @Access(AccessType.PROPERTY)
    @Column
    @Pattern(regexp="^[A-Za-z0-9]+[A-Za-z0-9\\.\\-_]*[A-Za-z0-9]+@[A-Za-z0-9]+[A-Za-z0-9\\-]*[A-Za-z0-9]+\\.[A-Za-z0-9]+$|^$",
            message="E-mail is invalid")
    private String email;

    @Access(AccessType.PROPERTY)
    @Column(unique = true)
    @Pattern(regexp="^[A-Za-z0-9]{5,25}$",
            message="Login must be alphanumeric with no spaces  from 5 to 25 characters")
    private String login;

    @Access(AccessType.PROPERTY)
    @Column
    @Pattern(regexp="^[A-Za-z0-9]{5,25}$|^$",
            message="Pass must be alphanumeric with no spaces  from 5 to 25 characters")
    private String password;

    @Access(AccessType.PROPERTY)
    @Column
    private String status;

    @Access(AccessType.PROPERTY)
    @Column
    private String role;
}
