package com.aspark.carebuddy.repository;

import com.aspark.carebuddy.api.response.NurseResponse;
import com.aspark.carebuddy.model.nurse.Nurse;
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
    Optional<Nurse> findById(int id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE nurse set latitude=?1, longitude=?2, pincode=?3 where email=?4",
    nativeQuery = true)
    int nurseSaveLocation(double latitude,double longitude,String pincode,String email);


    @Query(value = "SELECT * FROM nurse where pincode=?1",
            nativeQuery = true)
    ArrayList<Nurse> getNurseAtPincode(String pincode);

//    @Query(value = "SELECT * FROM nurse where pincode=?1 and rating >= 4.5",
    @Query(value = "SELECT * FROM nurse where pincode=?1",
            nativeQuery = true)
    ArrayList<Nurse> getTopNurses(String pincode);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Nurse n set n.enabled = TRUE where n.email=?1")
    int enableNurse(String email);

    @Transactional
    @Modifying
    @Query(value = "update Nurse set firebase_token=?2 where email=?1", nativeQuery = true)
    int setFirebaseToken( String email, String firebaseToken);

}
