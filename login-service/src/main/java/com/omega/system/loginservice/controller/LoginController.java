package com.omega.system.loginservice.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.omega.system.loginservice.model.User;
import com.omega.system.loginservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("user")
    //@HystrixCommand(fallbackMethod = "fallbackRetrieve")
    public @ResponseBody
    Iterable<User> getAllUsers(@RequestHeader("username") String username, @RequestHeader("password") String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user == null) {
            return null;
        } else {
            if (user.getRole().equals("admin")) {
                // This returns a JSON or XML with the users
                return userRepository.findAll();

            } else {
                return null;
            }
        }
    }

    Iterable<User> fallbackRetrive(@RequestHeader("username") String username, @RequestHeader("password") String password) {
       return null;
    }


    @PostMapping("user")
    //@HystrixCommand(fallbackMethod = "fallbackRetrieve")
    public @ResponseBody
    String addNewUser(@RequestBody User user) {
        userRepository.save(user);
        return "Saved";
    }

    @GetMapping("user/{id}")
    //@HystrixCommand(fallbackMethod = "fallbackRetrieve")
    public @ResponseBody
    Optional<User> getUser(@PathVariable("id") int id) {
        return userRepository.findById(id);
    }

    @GetMapping("user/{username}/password/{password}")
    public User isUserExists(@PathVariable("username") String username, @PathVariable("password") String password) {
        //boolean found = false;
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            return user;
        }
        return null;
    }
}
