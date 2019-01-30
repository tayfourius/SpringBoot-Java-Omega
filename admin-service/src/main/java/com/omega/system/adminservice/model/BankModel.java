package com.omega.system.adminservice.model;


public class BankModel {


    private int id;
    String username;
    String account;
    int ammout;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public BankModel(int id, String username, String account, int ammout) {
        this.id = id;
        this.username = username;
        this.account = account;
        this.ammout = ammout;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getAmmout() {
        return ammout;
    }

    public void setAmmout(int ammout) {
        this.ammout = ammout;
    }

    public BankModel() {
    }

}
