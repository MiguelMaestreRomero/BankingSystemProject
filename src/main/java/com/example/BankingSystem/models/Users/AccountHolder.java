package com.example.BankingSystem.models.Users;

import com.example.BankingSystem.models.Accounts.Account;
import com.example.BankingSystem.models.Embedded.Address;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.*;

@Entity
public class AccountHolder extends User{


    private LocalDate dateOfBirth;

    @Embedded
    private Address primaryAddress;

    private String mailingAddress;

    @OneToMany (mappedBy = "primaryOwner", cascade = CascadeType.ALL)
    @JsonIgnore //Ponemos el ignore ya que si no entra en un bucle de mostrar el AccountHolder y la Acoount
    private List<Account> primaryOwnerAccounts;
    @OneToMany (mappedBy = "secondaryOwner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Account> secondaryOwnerAccounts;

    public AccountHolder() {
    }

    public AccountHolder(String name, String password, LocalDate dateOfBirth, Address primaryAddress, String mailingAddress) {
        super(name,password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
        this.primaryOwnerAccounts = new ArrayList<>();
        this.secondaryOwnerAccounts = new ArrayList<>();
    }


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

    public List<Account> getPrimaryOwnerAccounts() {
        return primaryOwnerAccounts;
    }

    public void setPrimaryOwnerAccounts(List<Account> primaryOwnerAccounts) {
        this.primaryOwnerAccounts = primaryOwnerAccounts;
    }

    public List<Account> getSecondaryOwnerAccounts() {
        return secondaryOwnerAccounts;
    }

    public void setSecondaryOwnerAccounts(List<Account> secondaryOwnerAccounts) {
        this.secondaryOwnerAccounts = secondaryOwnerAccounts;
    }

    @Override
    public String toString() {
        return "AccountHolder{" +
                "dateOfBirth=" + dateOfBirth +
                ", primaryAddress=" + primaryAddress +
                ", mailingAddress='" + mailingAddress + '\'' +
                ", primaryOwnerAccounts=" + primaryOwnerAccounts +
                ", secondaryOwnerAccounts=" + secondaryOwnerAccounts +
                '}';
    }
}
