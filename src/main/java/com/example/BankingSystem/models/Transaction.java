package com.example.BankingSystem.models;

import com.example.BankingSystem.models.Accounts.Account;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String destinyAccountHolderName;

   // private Long originAccountId;

    //private Long destinyAccountId;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "origin_account_id")
    private Account originAccount;
    @ManyToOne
    @JoinColumn(name = "destination_account_id")
    private Account destinationAccount;

    public Transaction() {
    }

    public Transaction(BigDecimal amount, String destinyAccountHolderName, Account originAccount, Account destinationAccount) {
        this.amount = amount;
        this.destinyAccountHolderName = destinyAccountHolderName;
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
    }

    public String getDestinyAccountHolderName() {
        return destinyAccountHolderName;
    }

    public void setDestinyAccountHolderName(String destinyAccountHolderName) {
        this.destinyAccountHolderName = destinyAccountHolderName;
    }

  /*  public Long getOriginAccountId() {
        return originAccountId;
    }

    public void setOriginAccountId(Long originAccountId) {
        this.originAccountId = originAccountId;
    }

    public Long getDestinyAccountId() {
        return destinyAccountId;
    }

    public void setDestinyAccountId(Long destinyAccountId) {
        this.destinyAccountId = destinyAccountId;
    }*/

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Account getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(Account originAccount) {
        this.originAccount = originAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }
}
