package org.hrd.spring.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import org.hrd.spring.entities.User;
import org.hrd.spring.repositories.RoleRepository;
import org.hrd.spring.repositories.UserRepository;
import org.hrd.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	UserRepository userRepository;
	RoleRepository roleRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findUserByUUID(String uuid) {
		return userRepository.findUserByUUID(uuid);
	}

	@Override
	public boolean deleteAllByUUID(String uuid) {
		return userRepository.deleteAllByUUID(uuid);
	}

	@Override
	public boolean addUserRole(User user) {
		boolean status = false;
		user.setUuid(UUID.randomUUID().toString());
		if (userRepository.addUser(user)) {
			status = true;
			System.out.println("User ID " + user.getId() + "has been inserted !");
			if (user.getRoles() != null) {
				if (roleRepository.addRole(user.getRoles(), user.getId())) {
					System.out.println("This user has " + user.getRoles().size() + "roles");
				}
			}
		} else {
			System.out.println("Cannot not insert User");
		}
		return status;
	}

}
