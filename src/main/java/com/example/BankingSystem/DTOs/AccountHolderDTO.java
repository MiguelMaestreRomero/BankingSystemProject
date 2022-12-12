package com.example.BankingSystem.DTOs;

import com.example.BankingSystem.models.Embedded.Address;

import java.time.LocalDate;
import java.util.Optional;

public class AccountHolderDTO {

    private LocalDate dateOfBirth;

    private Address primaryAddress;

    private String mailingAddress;

    private String name;

    private Long primaryOwner;

    private Optional<Long> secondaryOwner;

    private String password;

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrimaryOwner() {
        return primaryOwner;
    }

    public void setPrimaryOwner(Long primaryOwner) {
        this.primaryOwner = primaryOwner;
    }

    public Optional<Long> getSecondaryOwner() {
        return secondaryOwner;
    }

    public void setSecondaryOwner(Optional<Long> secondaryOwner) {
        this.secondaryOwner = secondaryOwner;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
