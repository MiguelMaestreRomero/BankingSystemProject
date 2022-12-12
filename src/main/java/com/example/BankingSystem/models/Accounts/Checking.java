package com.example.BankingSystem.models.Accounts;

import com.example.BankingSystem.models.Enums.Status;
import com.example.BankingSystem.models.Users.AccountHolder;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Checking extends Account{

    private  int secretKey;

    private BigDecimal minimumBalance;

    private BigDecimal monthlyMaintenanceFee;

    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Checking() {
    }

    public Checking(BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, int secretKey) {
        super(balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.minimumBalance = new BigDecimal(250);
        this.monthlyMaintenanceFee = new BigDecimal(12);
        this.creationDate = LocalDate.now();
        this.status = Status.ACTIVE;
    }

    public Checking(BigDecimal balance, AccountHolder primaryOwner, int secretKey) {
        super(balance, primaryOwner);
        this.secretKey = secretKey;
        this.minimumBalance = new BigDecimal(250);
        this.monthlyMaintenanceFee = new BigDecimal(12);
        this.creationDate = LocalDate.now();
        this.status = Status.ACTIVE;
    }

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

    public BigDecimal getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public void setMonthlyMaintenanceFee(BigDecimal monthlyMaintenanceFee) {
        this.monthlyMaintenanceFee = monthlyMaintenanceFee;
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
}
