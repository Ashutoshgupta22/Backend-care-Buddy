package com.aspark.carebuddy.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenUserRepository extends JpaRepository<ConfirmationTokenUser,Long> {

        Optional<ConfirmationTokenUser> findByToken(String token);

        @Transactional
        @Modifying
        @Query("UPDATE ConfirmationTokenUser c " +
                "SET c.confirmedAt = ?2 " +
                "WHERE c.token = ?1")
        int updateConfirmedAtUser(String token,
                                  LocalDateTime confirmedAt);


}
