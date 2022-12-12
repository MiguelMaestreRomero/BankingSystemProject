package com.example.BankingSystem.models.Users;

import jakarta.persistence.Entity;

@Entity
public class ThirdParty extends User{

    private String key;

    public ThirdParty() {
    }

    public ThirdParty(String name, String password, String key) {
        super(name,password);
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
