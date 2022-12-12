package com.example.BankingSystem.DTOs;

import com.example.BankingSystem.models.Accounts.Account;

import java.math.BigDecimal;

public class TransactionDTO {

    private String destinyAccountHolderName;

    private Long originAccountId;

    private Long destinyAccountId;

    private BigDecimal amount;

   // private BigDecimal balance;

   // private BigDecimal penaltyFee;

    public TransactionDTO( BigDecimal amount, String destinyAccountHolderName, Long originAccountId, Long destinyAccountId) {
        this.amount = amount;
        this.destinyAccountHolderName = destinyAccountHolderName;
        this.originAccountId = originAccountId;
        this.destinyAccountId = destinyAccountId;
    }

    public String getDestinyAccountHolderName() {
        return destinyAccountHolderName;
    }

    public void setDestinyAccountHolderName(String destinyAccountHolderName) {
        this.destinyAccountHolderName = destinyAccountHolderName;
    }

    public Long getOriginAccountId() {
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
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /*public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }*/
}
