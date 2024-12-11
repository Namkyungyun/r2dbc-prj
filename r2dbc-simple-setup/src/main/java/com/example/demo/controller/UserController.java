package com.example.demo.controller;

import com.example.demo.r2dbc.User;
import com.example.demo.r2dbc.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public Flux<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/user/{name}")
    public Mono<User> getUser(@PathVariable("name") String name) {
        return userRepository.findByName(name);
    }


    @PostMapping("/users/user")
    public Mono<User> saveUser(@RequestBody User user) {
        return userRepository.save(user);
    }


    @DeleteMapping("/users/user/{name}")
    public Mono<Void> removeMember(@PathVariable String name) {
        return Mono.just(name)
                .flatMap(userRepository::findByName)
                .flatMap(userRepository::deleteById);
    }

    @PutMapping("/users/user/{name}")
    public Mono<User> updateUser(@PathVariable String name, @RequestParam String newName) {
        return Mono.just(name)
                .flatMap(userRepository::findFirstByName)
                .map(id -> User.updateUser(Long.valueOf(id), newName))
                .flatMap(userRepository::save);
    }


}


