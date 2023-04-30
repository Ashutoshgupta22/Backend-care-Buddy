package org.mitraz.MITRAz.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {

    private final String name;
    private final int age;
    private final String email;
    private final String password;
    private final String pincode;
    private final Double latitude;
    private final Double longitude;
}
