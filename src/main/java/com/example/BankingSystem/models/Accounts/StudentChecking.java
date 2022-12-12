package com.example.BankingSystem.models.Accounts;

import com.example.BankingSystem.models.Enums.Status;
import com.example.BankingSystem.models.Users.AccountHolder;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class StudentChecking extends Account{

    private  int secretKey;
    private LocalDate creationDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    public StudentChecking() {
    }

    public StudentChecking(BigDecimal balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, int secretKey) {
        super(balance, primaryOwner, secondaryOwner);
        this.secretKey = secretKey;
        this.creationDate = LocalDate.now();
        this.status = Status.ACTIVE;
    }

    public StudentChecking(BigDecimal balance, AccountHolder primaryOwner, int secretKey) {
        super(balance, primaryOwner);
        this.secretKey = secretKey;
        this.creationDate = LocalDate.now();
        this.status = Status.ACTIVE;
    }

}
