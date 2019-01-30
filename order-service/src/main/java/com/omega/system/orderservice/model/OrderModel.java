package com.omega.system.orderservice.model;


import java.util.List;


public class OrderModel {

    private String id;
    public  String statusOrder;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int restaurantId;
    private List <MenuModel> menu;

    public OrderModel() {
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }


    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public OrderModel(String id, String statusOrder, int userId, int restaurantId, List<MenuModel> menu) {
        this.id = id;
        this.statusOrder = statusOrder;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.menu = menu;
    }

    public List<MenuModel> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuModel> menu) {
        this.menu = menu;
    }
}
