package com.habitFlowApi.app.repository;

import com.habitFlowApi.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName (String name);
    Optional<User> findByPassword (String password);
}
