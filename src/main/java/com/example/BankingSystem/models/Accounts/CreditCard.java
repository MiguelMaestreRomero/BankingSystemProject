package com.example.BankingSystem.models.Accounts;

import com.example.BankingSystem.models.Users.AccountHolder;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Entity
public class CreditCard extends Account{

    @DecimalMax(value = "100000")
    @DecimalMin(value = "100")
    private BigDecimal creditLimit;
    @DecimalMax(value = "0.2")
    @DecimalMin(value = "0.1")
    private BigDecimal interestRate;

    private LocalDate lastInterest = LocalDate.now();


    public CreditCard() {
    }

    public CreditCard(BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner) {
        super(balance, primaryOwner, secondaryOwner);
        this.creditLimit = new BigDecimal(100000);
        this.interestRate = new BigDecimal(0.2);
    }

    public CreditCard(BigDecimal balance, AccountHolder primaryOwner) {
        super(balance, primaryOwner);
        this.creditLimit = new BigDecimal(100000);
        this.interestRate = new BigDecimal(0.2);
    }

    public void checkInterestCreditCardBalance (){
        int elapsedMonth = Period.between(lastInterest, LocalDate.now()).getMonths();
        if(elapsedMonth>=1){
            super.setBalance(super.getBalance().multiply(interestRate).multiply(new BigDecimal(elapsedMonth)));
            setLastInterest(LocalDate.now());
        }
    }

    public LocalDate getLastInterest() {
        return lastInterest;
    }

    public void setLastInterest(LocalDate lastInterest) {
        this.lastInterest = lastInterest;
    }

    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
