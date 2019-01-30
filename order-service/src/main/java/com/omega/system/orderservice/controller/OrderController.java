package com.omega.system.orderservice.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.omega.system.orderservice.model.MenuModel;
import com.omega.system.orderservice.model.OrderModel;
import com.omega.system.orderservice.model.OrderMysqlModel;
import com.omega.system.orderservice.model.UserModel;
import com.omega.system.orderservice.proxy.LoginServiceProxy;
import com.omega.system.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
public class OrderController {

    @Autowired
    LoginServiceProxy loginServiceProxy;


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    JmsTemplate jmsTemplate;


    @GetMapping("order/restaurant/{id}")
    public @ResponseBody
    Iterable<OrderMysqlModel> findResturentById(@PathVariable("id") int id, @RequestHeader("username") String username, @RequestHeader("password") String password) {
        UserModel userModel = loginServiceProxy.retrieveUser(username, password);
        if (userModel == null) {
            return null;
        } else {
            if (userModel.getRole().equals("restaurant")  ) {
                return orderRepository.findByRestaurantId(id);
            } else {
                return null;
            }
        }
    }

    @GetMapping("order/user/{id}")
    public @ResponseBody
    Iterable<OrderMysqlModel> findUserById(@PathVariable("id") int id, @RequestHeader("username") String username, @RequestHeader("password") String password) {
        UserModel userModel = loginServiceProxy.retrieveUser(username, password);
        if (userModel == null) {
            return null;
        } else {
            if (userModel.getRole().equals( "user")) {
                return orderRepository.findByUserId(id);
            } else {
                return null;
            }
        }
    }


    @PostMapping("order")
    public String sendOrder(@RequestBody String message, @RequestHeader("username") String username, @RequestHeader("password") String password) throws IOException {
        UserModel userModel = loginServiceProxy.retrieveUser(username, password);
        if (userModel == null) {
            return null;
        } else {
            if (userModel.getRole().equals( "user")) {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(message);
                int userId = Integer.parseInt(String.valueOf(jsonNode.get("userId")));
                int restaurantId = Integer.parseInt(String.valueOf(jsonNode.get("restaurantId")));
                String orderId = String.valueOf(jsonNode.get("orderId"));
                ArrayNode menu = (ArrayNode) jsonNode.get("menu");
                OrderModel orderModel = new OrderModel();
                orderModel.setUserId(userId);
                orderModel.setRestaurantId(restaurantId);
                List<MenuModel> list = new ArrayList<>();
                String menuOrder = "";
                for (JsonNode node : menu) {
                    String name = node.get("name").toString();
                    int quantity = Integer.parseInt(String.valueOf(node.get("quantity")));

                    MenuModel menuModel = new MenuModel();
                    menuModel.setName(name);
                    menuModel.setQuantity(quantity);
                    menuOrder = menuOrder + name + "," + quantity + ";";
                    list.add(menuModel);
                }
                menuOrder = menuOrder.substring(0, menuOrder.length() - 1);
                orderModel.setMenu(list);
                OrderMysqlModel orderMysqlModel = new OrderMysqlModel();
                orderMysqlModel.setId(orderId);
                orderMysqlModel.setUserId(orderModel.getUserId());
                orderMysqlModel.setRestaurantId(orderModel.getRestaurantId());
                //String s = orderModel.getMenu().stream().map(e -> e.toString()).reduce("", String::concat);

                orderMysqlModel.setOrderMenu(menuOrder);
                orderMysqlModel.setStatusOrder("Send");
                orderRepository.save(orderMysqlModel);
                //orderModel.setId(orderMysqlModel.getId());
                jmsTemplate.convertAndSend("mailbox", message);
                return "Send";
            } else {
                return null;
            }
        }

    }
}
