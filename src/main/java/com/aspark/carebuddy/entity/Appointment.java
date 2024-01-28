package com.aspark.carebuddy.entity;

import com.aspark.carebuddy.model.nurse.Nurse;
import com.aspark.carebuddy.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @JsonIgnore // do not include whole serviceProvider object in JSON response
    @ManyToOne
    @JoinColumn(name = "nurse_id")
    private Nurse nurse;

    @JsonIgnore // do not include whole serviceProvider object in JSON response
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime appointmentDateTime;
    private String appointmentStatus;
    private String purpose;

    // Create a custom field for serialization, include only ID without modifying the entity model.
    @JsonProperty("nurseId")
    private int getNurseId() {
        return nurse.getId();
    }

    @JsonProperty("userId")
    private int getUserId() {
        return user.getId();
    }
}

