package com.habitFlowApi.app.service;

import com.habitFlowApi.app.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(long id);
    Optional<User> findByName(String name);
    User update(User user);
    void deletebyId(long id);
}
