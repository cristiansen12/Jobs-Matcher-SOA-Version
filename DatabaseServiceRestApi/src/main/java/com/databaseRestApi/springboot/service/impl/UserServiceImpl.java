package com.databaseRestApi.springboot.service.impl;

import java.util.List;

import com.databaseRestApi.springboot.model.User;
import com.databaseRestApi.springboot.repository.UserRepository;
import com.databaseRestApi.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
    UserRepository userRepository;

	@Override
	@Transactional
	public List<User> findAllUsers() {
		return userRepository.listAll();
	}

	@Override
	@Transactional
	public User findById(long id) {
		List<User> users = userRepository.listAll();
		for (User u:users){
			if (u.getId() == id)
				return u;
		}
		return null;
	}

	@Transactional
	public User findByName(String name) {
		List<User> users = userRepository.listAll();
		for (User u:users){
			if (u.getName().equals(name))
				return u;
		}
		return null;
	}

	@Transactional
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Transactional
	public void updateUser(User user) {
		userRepository.update(user);
	}

	@Transactional
	public void deleteUserById(long id) {
		userRepository.delete(this.findById(id));
	}

	@Transactional
	public boolean isUserExist(User user) {
		return findByName(user.getName())!=null;
	}

	@Transactional
	public void deleteAllUsers(){
		List<User> users = userRepository.listAll();
		for (User u:users){
			userRepository.delete(u);
		}
	}

}
