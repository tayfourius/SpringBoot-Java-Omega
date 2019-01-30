package com.omega.system.bankservice.model;

import javax.persistence.*;


@Entity
@Table(name = "bank")
public class BankModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
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


    public BankModel(String username, String account, int ammout) {
        super();
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
