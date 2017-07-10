package org.hrd.spring.service;

import java.util.List;

import org.hrd.spring.entities.User;
import org.springframework.stereotype.Service;

public interface UserService {
	public List<User> findAll();
	public User findUserByUUID(String uuid);
	public boolean deleteAllByUUID(String uuid);
	public boolean addUserRole(User user);
}
