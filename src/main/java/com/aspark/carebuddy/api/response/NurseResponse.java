package com.aspark.carebuddy.api.response;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class NurseResponse {

    private String firstName;
    private String lastName;
    private int age;
    private String imageUrl;
    private String biography;
    private String qualifications;
    private ArrayList<String> specialities;
    private int Experience;
    private double rating;
    private int patientNo;
    private String email;
    private String password;
    private String pincode;
    private Double latitude;
    private Double longitude;
    private String firebaseToken;

}
