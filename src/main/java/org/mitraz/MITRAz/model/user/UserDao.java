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

	public User registerUser(User user) {

		System.out.println("UserDao registering user email "+user.getEmail());
		return repository.save(user);
	}
	
//	public void deleteUser(User user) {
//
//		repository.delete(user);
//	}



}
