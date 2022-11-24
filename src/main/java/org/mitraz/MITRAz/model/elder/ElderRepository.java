package org.mitraz.MITRAz.model.elder;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElderRepository extends CrudRepository<Elder, Integer> {

	
}
