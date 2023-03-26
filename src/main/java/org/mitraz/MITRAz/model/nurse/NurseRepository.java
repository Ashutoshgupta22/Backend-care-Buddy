package org.mitraz.MITRAz.model.nurse;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
public interface NurseRepository extends CrudRepository<Nurse, Integer> {


    @Transactional
    @Modifying
    @Query(value = "UPDATE nurse set latitude=?1, longitude=?2 where email=?3",
    nativeQuery = true)
    int nurseSaveLocation(double latitude,double longitude,String email);


    @Query(value = "SELECT latitude FROM nurse order by latitude",
            nativeQuery = true)
    ArrayList<Double> getLatitude();

    @Query(value = "SELECT longitude FROM nurse order by longitude",
            nativeQuery = true)
    ArrayList<Double> getLongitude();

}
