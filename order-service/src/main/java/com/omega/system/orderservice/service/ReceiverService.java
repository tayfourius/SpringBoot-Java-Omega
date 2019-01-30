package com.omega.system.orderservice.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.omega.system.orderservice.model.MenuModel;
import com.omega.system.orderservice.model.OrderModel;
import com.omega.system.orderservice.model.OrderMysqlModel;
import com.omega.system.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReceiverService {

    @Autowired
    OrderRepository orderRepository;

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void receiveMessage(String order) throws IOException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(order);
        int userId = Integer.parseInt(String.valueOf(jsonNode.get("userId")));
        String orderId = String.valueOf(jsonNode.get("orderId"));
        int restaurantId = Integer.parseInt(String.valueOf(jsonNode.get("restaurantId")));
        //String status = String.valueOf(jsonNode.get("statusOrder"));
        ArrayNode menu = (ArrayNode) jsonNode.get("menu");
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setRestaurantId(restaurantId);
        List<MenuModel> list = new ArrayList<>();
        String menuOrder = "";
        for (JsonNode node : menu) {
            String name = node.get("name").toString();
            int quantity = Integer.parseInt(String.valueOf(node.get("quantity")));
            menuOrder = menuOrder + name + "," + quantity +";";
            MenuModel menuModel = new MenuModel();
            menuModel.setName(name);
            menuModel.setQuantity(quantity);
            list.add(menuModel);
        }
        menuOrder = menuOrder.substring(0, menuOrder.length() - 1);
        OrderMysqlModel orderMysqlModel = new OrderMysqlModel();
        orderMysqlModel.setId(orderId);
        orderMysqlModel.setUserId(orderModel.getUserId());
        orderMysqlModel.setRestaurantId(orderModel.getRestaurantId());
        orderMysqlModel.setOrderMenu(menuOrder);
        orderMysqlModel.setStatusOrder("Received");
        Thread.sleep(3000);
        orderRepository.save(orderMysqlModel);
        orderModel.setMenu(list);
        System.out.println("Received <" + orderId + ">");
        System.out.println("restaurant_id <" + restaurantId + ">");
        System.out.println("user_id <" + userId + ">");
        System.out.println("menu <" + menu + ">");
        Thread.sleep(3000);
        orderMysqlModel.setStatusOrder("Finished");
        orderRepository.save(orderMysqlModel);
    }
}
