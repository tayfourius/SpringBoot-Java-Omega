package com.omega.system.restaurantsservice.controller;


import com.omega.system.restaurantsservice.model.MenuModel;
import com.omega.system.restaurantsservice.model.RestaurantsModel;
import com.omega.system.restaurantsservice.model.UserModel;
import com.omega.system.restaurantsservice.proxy.LoginServiceProxy;
import com.omega.system.restaurantsservice.proxy.MenuServiceProxy;
import com.omega.system.restaurantsservice.repository.RestaurantsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RestaurantsController {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RestaurantsRepository restaurantsRepository;

    @Autowired
    LoginServiceProxy loginServiceProxy;

    @Autowired
    MenuServiceProxy menuServiceProxy;

    @GetMapping("restaurant")
    public @ResponseBody
    Iterable<RestaurantsModel> getAllRestaurant() {
        return restaurantsRepository.findAll();
    }

    @PostMapping("restaurant")
    public @ResponseBody
    String addNewRestaurant(@RequestBody RestaurantsModel restaurantsModel, @RequestHeader("username") String username, @RequestHeader("password") String password) {
        UserModel userModel = loginServiceProxy.retrieveUser(username, password);
        logger.info("{}", userModel);
        if (userModel == null) {
            return "not user";
        } else {
            if (userModel.getRole().equals("admin")) {
                restaurantsRepository.save(restaurantsModel);
                return "Saved";
            } else {
                return "not admin user";
            }
        }
    }

    @GetMapping("restaurant/{id}")
    public @ResponseBody
    Optional<RestaurantsModel> getRestaurant(@PathVariable("id") int id, @RequestHeader("username") String username, @RequestHeader("password") String password) {
        UserModel user = loginServiceProxy.retrieveUser(username, password);
        if (user == null) {
            return null;
        } else {
            return restaurantsRepository.findById(id);
        }
    }


    @GetMapping("/restaurant/{id}/menu/")
    public Iterable<MenuModel> menuModel(@PathVariable int id, @RequestHeader("username") String username, @RequestHeader("password") String password) {
        UserModel user = loginServiceProxy.retrieveUser(username, password);
        if (user == null) {
            return null;
        } else {
            Iterable<MenuModel> response = menuServiceProxy.retrieveMenu(id);
            logger.info("{}", response);
            return response;
        }
    }

}
