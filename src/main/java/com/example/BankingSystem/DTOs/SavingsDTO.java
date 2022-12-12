package com.example.BankingSystem.DTOs;

import com.example.BankingSystem.models.Enums.Status;
import com.example.BankingSystem.models.Users.AccountHolder;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class SavingsDTO {

    private int secretKey;

    private BigDecimal minimumBalance;

    private LocalDate creationDate;

    private Status status;

    private BigDecimal interestRate;

    private LocalDate lastInterest;

    private BigDecimal balance;

    private Long primaryOwner;

    private Optional<Long> secondaryOwner;

    private BigDecimal penaltyFee;

    public int getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(int secretKey) {
        this.secretKey = secretKey;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public LocalDate getLastInterest() {
        return lastInterest;
    }

    public void setLastInterest(LocalDate lastInterest) {
        this.lastInterest = lastInterest;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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

    public BigDecimal getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(BigDecimal penaltyFee) {
        this.penaltyFee = penaltyFee;
    }
}
