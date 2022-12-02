package org.mitraz.MITRAz.model.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDao {
	
	@Autowired
	private UserRepository repository;

	public User getUser() {

		assert repository.findById(0).isPresent();
		return repository.findById(0).get();
	}
	public User saveUser(User user) {
		
		return repository.save(user);
	}
	
	public void deleteUser(User user) {
		
		repository.delete(user);
	}

}
