package com.databaseRestApi.springboot.repository;

import com.databaseRestApi.springboot.model.User;

import java.util.List;

public interface UserRepository{

    List<User> listAll();
    void save(User user);
    void delete(User user);
    void update(User user);

}
