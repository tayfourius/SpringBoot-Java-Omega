package com.omega.system.restaurantsservice.model;

import javax.persistence.*;


@Entity
@Table(name = "restaurant")
public class RestaurantsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    String name;
    String location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RestaurantsModel(String name, String location) {
        super();

        this.name = name;
        this.location = location;
    }

    public RestaurantsModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
