package org.mitraz.MITRAz.login.user;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class UserLoginRequest {

    private final String email;
    private final String password;
}
