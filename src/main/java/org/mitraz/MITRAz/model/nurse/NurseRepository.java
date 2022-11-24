package org.mitraz.MITRAz.model.nurse;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NurseRepository extends CrudRepository<Nurse, Integer> {

}
