package com.omega.system.menuservice.model;

import javax.persistence.*;


@Entity
@Table(name = "menu")
public class MenuModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
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

    public int getPrice() {
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

    public MenuModel(String name, int restaurantId, int price) {
        super();
        this.name = name;
        this.restaurantId = restaurantId;
        this.price = price;
    }
}
