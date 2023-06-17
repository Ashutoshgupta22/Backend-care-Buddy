package com.aspark.carebuddy.model.nurse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Integer> {

    Optional<Nurse> findByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE nurse set latitude=?1, longitude=?2, pincode=?3 where email=?4",
    nativeQuery = true)
    int nurseSaveLocation(double latitude,double longitude,String pincode,String email);


    @Query(value = "SELECT * FROM nurse where pincode=?1",
            nativeQuery = true)
    ArrayList<Nurse> getNurseAtPincode(String pincode);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Nurse n set n.enabled = TRUE where n.email=?1")
    int enableNurse(String email);

    @Transactional
    @Modifying
    @Query(value = "update Nurse set firebase_token=?1 where email=?2", nativeQuery = true)
    boolean setFirebaseToken( String firebaseToken, String email);


}
