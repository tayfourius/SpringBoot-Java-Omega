package com.omega.system.adminservice.model;



public class RestaurantsModel {


    private int id;
    String name;
    String location;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RestaurantsModel(int id, String name, String location) {
        super();
        this.id = id;
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
