package org.mitraz.MITRAz.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenNurseRepository extends JpaRepository<ConfirmationTokenNurse,Long> {

    Optional<ConfirmationTokenNurse> findByToken(String token);


    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationTokenNurse c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAtNurse(String token,
                               LocalDateTime confirmedAt);
}
