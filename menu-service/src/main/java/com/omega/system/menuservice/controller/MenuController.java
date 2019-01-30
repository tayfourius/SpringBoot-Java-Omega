package com.omega.system.menuservice.controller;


import com.omega.system.menuservice.model.MenuModel;
import com.omega.system.menuservice.model.UserModel;
import com.omega.system.menuservice.proxy.LoginServiceProxy;
import com.omega.system.menuservice.repository.MenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MenuController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    MenuRepository menuRepository;

    @Autowired
    LoginServiceProxy loginServiceProxy;

    @GetMapping("restaurant/{id}/menu")
    public @ResponseBody
    Iterable<MenuModel> getRestaurantMenu(@PathVariable("id") int id, @RequestHeader("username") String username, @RequestHeader("password") String password) {
        UserModel userModel = loginServiceProxy.retrieveUser(username, password);
        logger.info("{}", userModel);
        if (userModel == null) {
            return null;
        } else {

            return menuRepository.findByRestaurantId(id);
        }

    }

    @PostMapping("restaurant/menu")
    public @ResponseBody
    String addNewMenu(@RequestBody MenuModel menuModel, @RequestHeader("username") String username, @RequestHeader("password") String password) {
        UserModel userModel = loginServiceProxy.retrieveUser(username, password);
        logger.info("{}", userModel);
        if (userModel == null) {
            return "not user";
        } else {
            if ((userModel.getRole().equals("admin")) || (userModel.getRole().equals("restaurant"))) {
                menuRepository.save(menuModel);
                return "Saved";
            } else {
                return "not admin user";
            }

        }

    }

    @GetMapping("restaurant/menu/{id}")
    public @ResponseBody
    Optional<MenuModel> getMenu(@PathVariable("id") int id, @RequestHeader("username") String username, @RequestHeader("password") String password) {

        UserModel userModel = loginServiceProxy.retrieveUser(username, password);
        logger.info("{}", userModel);
        if (userModel == null) {
            return null;
        } else {
            return menuRepository.findById(id);
        }
    }

}
