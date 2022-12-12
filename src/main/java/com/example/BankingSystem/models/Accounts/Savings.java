package com.example.BankingSystem.models.Accounts;

import com.example.BankingSystem.models.Enums.Status;
import com.example.BankingSystem.models.Users.AccountHolder;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@Entity
public class Savings extends Account{

    private int secretKey;

    @DecimalMax(value = "1000")
    @DecimalMin(value = "100")
    private BigDecimal minimumBalance;

    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    @DecimalMax(value = "0.5")
    private BigDecimal interestRate;

    private LocalDate lastInterest = LocalDate.now();

    public Savings() {
    }

    public Savings(BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, int secretKey) {
        super(balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.minimumBalance = new BigDecimal(1000);
        this.creationDate = LocalDate.now();
        this.status = Status.ACTIVE;
        this.interestRate = new BigDecimal(0.0025);
    }

    public Savings(BigDecimal balance, AccountHolder primaryOwner, int secretKey) {
        super(balance, primaryOwner);
        this.secretKey = secretKey;
        this.minimumBalance = new BigDecimal(1000);
        this.creationDate = LocalDate.now();
        this.status = Status.ACTIVE;
        this.interestRate = new BigDecimal(0.0025);
    }

    public void checkInterestSavingsBalance (){
        int elapsedYears = Period.between(lastInterest, LocalDate.now()).getYears();
        if(elapsedYears>=1){
            super.setBalance(super.getBalance().multiply(interestRate).multiply(new BigDecimal(elapsedYears)));
            setLastInterest(LocalDate.now());
        }
    }

    public LocalDate getLastInterest() {
        return lastInterest;
    }

    public void setLastInterest(LocalDate lastInterest) {
        this.lastInterest = lastInterest;
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
}
