package com.omega.system.webappservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller

public class HelloWorldController {
    @Value("${application.message:Hello World}")
    private String helloMessage;

    @GetMapping("/")
    public String welcome(Map model) {

        model.put("message", helloMessage);

        return "welcome";
    }
}
