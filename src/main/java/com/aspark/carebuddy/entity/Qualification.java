package com.aspark.carebuddy.entity;

import com.aspark.carebuddy.model.nurse.Nurse;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qualificationId;

    @JoinColumn(name="nurse_id")
    @ManyToOne
    private Nurse nurse;

    private String degree;
    private String university;
    private String gradYear;
}