package com.aspark.carebuddy.entity;

import com.aspark.carebuddy.model.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String city;
    private String state;
    private String pincode;
    private String address;
}
