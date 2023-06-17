package com.aspark.carebuddy.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ConfirmationTokenService {

    private ConfirmationTokenUserRepository tokenUserRepository;
    private ConfirmationTokenNurseRepository tokenNurseRepository;

    public void saveConfirmationUserToken(ConfirmationTokenUser token) {

       tokenUserRepository.save(token);
    }

    public void saveConfirmationNurseToken(ConfirmationTokenNurse tokenNurse) {

        tokenNurseRepository.save(tokenNurse);
    }

    public Optional<ConfirmationTokenUser> getUserToken(String token) {
        return tokenUserRepository.findByToken(token);
    }

    public Optional<ConfirmationTokenNurse> getNurseToken(String nurseToken) {
        return tokenNurseRepository.findByToken(nurseToken);
    }

    public int setUserConfirmedAt(String token) {
        return tokenUserRepository.updateConfirmedAtUser(token, LocalDateTime.now());
    }

    public int setNurseConfirmedAt(String nurseToken) {

        return tokenNurseRepository.updateConfirmedAtNurse(nurseToken,LocalDateTime.now());
    }
}
