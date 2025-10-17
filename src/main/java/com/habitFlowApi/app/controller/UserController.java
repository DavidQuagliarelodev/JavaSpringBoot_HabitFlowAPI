package com.habitFlowApi.app.controller;

import com.habitFlowApi.app.config.Autenticator;
import com.habitFlowApi.app.model.User;
import com.habitFlowApi.app.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserController {

    @Autowired
    UserServiceImp userServiceImp;

    @Autowired
    Autenticator autenticator;

    @GetMapping("/users")
    public ResponseEntity<List<User>> findUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userServiceImp.findAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<User>> findUserById(@PathVariable long id){
        System.out.println("TOKEN: " + autenticator.getToken());
        return ResponseEntity.status(HttpStatus.OK).body(userServiceImp.findById(id));
    }

    @GetMapping("/user/name/{name}")
    public ResponseEntity<Optional<User>> findUserByName(@PathVariable String name){
//        autenticator.tokenGenerate(name, "4321");
//        System.out.println("TOKEN: " + autenticator.getToken() + " " + autenticator.getName() + " " + autenticator.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(userServiceImp.findByName(name));
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User user){
        userServiceImp.save(user);
        autenticator.tokenGenerate(user.getName(), user.getPassword(), user.getId());// Generated TokenUser
        System.out.println(autenticator.getToken());
        return ResponseEntity.status(HttpStatus.CREATED).body(String.valueOf(autenticator.getToken()));
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userServiceImp.save(user));
    }

    @DeleteMapping("/id")
    public ResponseEntity<?> deleteUserById(@PathVariable  long id){
        userServiceImp.deletebyId(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
