package com.omega.system.loginservice.repository;

import com.omega.system.loginservice.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    //@Query("SELECT * WHERE username ")
    User findByUsernameAndPassword(String username, String password);
}