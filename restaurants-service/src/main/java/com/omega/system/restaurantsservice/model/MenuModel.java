package com.omega.system.restaurantsservice.model;

import javax.persistence.*;



public class MenuModel {


    private int id;
    String name;
    int restaurantId;
    int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public MenuModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public MenuModel(int id, String name, int restaurantId, int price) {
        super();
        this.id = id;
        this.name = name;
        this.restaurantId = restaurantId;
        this.price = price;
    }
}
