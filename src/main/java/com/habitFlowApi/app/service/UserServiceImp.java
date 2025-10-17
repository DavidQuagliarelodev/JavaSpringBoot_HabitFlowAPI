package com.habitFlowApi.app.service;

import com.habitFlowApi.app.model.User;
import com.habitFlowApi.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
       return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deletebyId(long id) {
        userRepository.deleteById(id);
    }
}
