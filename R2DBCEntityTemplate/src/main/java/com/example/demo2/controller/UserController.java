package com.example.demo2.controller;

import com.example.demo2.r2dbc.User;
import com.example.demo2.r2dbc.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    Flux<User> findAll() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{name}")
    public Mono<User> getUser(@PathVariable("name") String name) {
        return userRepository.findByName(name);
    }

    @PostMapping("/users")
    public Mono<User> saveUser(@RequestBody User user) {
        return  userRepository.save(user);
    }

    @PutMapping("/users/user/{name}")
    public Mono<Integer> updateUser(@PathVariable String name,
                                    @RequestParam String newName) {
        return userRepository.update(name, newName);
    }

    @DeleteMapping("/users/user/{name}")
    public Mono<Integer> removeUser(@PathVariable String name) {
        return userRepository.deleteByName(name);
    }
}
