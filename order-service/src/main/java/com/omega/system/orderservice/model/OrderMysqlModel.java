package com.omega.system.orderservice.model;

import javax.persistence.*;

@Entity
@Table(name = "orderlist")
public class OrderMysqlModel {

    @Id
    private String id;
    public String statusOrder;
    public int userId;
    public int restaurantId;
    public String orderMenu;

    public OrderMysqlModel(String id, String statusOrder, int userId, int restaurantId, String orderManu) {
        this.id = id;
        this.statusOrder = statusOrder;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.orderMenu = orderManu;
    }

    public String getOrderMenu() {
        return orderMenu;
    }

    public void setOrderMenu(String orderMenu) {
        this.orderMenu = orderMenu;
    }

    public OrderMysqlModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }



}
