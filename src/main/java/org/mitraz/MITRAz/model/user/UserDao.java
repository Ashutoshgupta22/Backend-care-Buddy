package org.mitraz.MITRAz.model.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDao {

	//@Autowired
	private UserRepository repository;

	public User getUser() {

		assert repository.findById(0).isPresent();
		return repository.findById(0).get();
	}

	public User saveUser(User user) {
		return repository.save(user);
	}

//	public User findByEmail(String email) {
//
//		assert repository.findByEmail(email).isPresent();
//		return  repository.findByEmail(email).get();
//	}

}
