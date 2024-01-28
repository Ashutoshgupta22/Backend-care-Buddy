package com.aspark.carebuddy.api.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Setter
@Getter
public class NurseResponse implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String imageUrl;
    private String biography;
    private String qualifications;
    private ArrayList<String> specialities;
    private int experience;
    private double rating;
    private int patientNo;
    private String email;
    private String pincode;
    private Double latitude;
    private Double longitude;
    private String firebaseToken;

}
