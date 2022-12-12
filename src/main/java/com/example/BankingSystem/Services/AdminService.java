package com.example.BankingSystem.Services;

import com.example.BankingSystem.DTOs.*;
import com.example.BankingSystem.Repository.*;
import com.example.BankingSystem.models.Accounts.*;
import com.example.BankingSystem.models.Users.AccountHolder;
import com.example.BankingSystem.models.Users.ThirdParty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

@Service
public class AdminService {

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ThirdPartyRepository thirdPartyRepository;


    public Account createCheckingAccount(CheckingDTO checkingDTO) {
        if (accountHolderRepository.findById(checkingDTO.getPrimaryOwner()).isPresent()) {
            AccountHolder primaryCheckingAccountHolder = accountHolderRepository.findById(checkingDTO.getPrimaryOwner()).get();
            AccountHolder secondaryCheckingAccountHolder = null;

            if (checkingDTO.getSecondaryOwner() != null) {
                if (accountHolderRepository.findById(checkingDTO.getSecondaryOwner().get()).isPresent()) {
                    secondaryCheckingAccountHolder = accountHolderRepository.findById(checkingDTO.getSecondaryOwner().get()).get();
                }
            }

            Integer age = Period.between(primaryCheckingAccountHolder.getDateOfBirth(), LocalDate.now()).getYears();
            if (age > 24) {
                Checking checking = new Checking(checkingDTO.getBalance(), primaryCheckingAccountHolder, secondaryCheckingAccountHolder, checkingDTO.getSecretKey());
                return checkingRepository.save(checking);
            } else {
                StudentChecking studentChecking = new StudentChecking(checkingDTO.getBalance(), primaryCheckingAccountHolder, secondaryCheckingAccountHolder, checkingDTO.getSecretKey());
                return studentCheckingRepository.save(studentChecking);
            }
        }
        throw new IllegalArgumentException("Create a Primary Account Holder before Checking Account");
    }


    public Savings createSavingsAccount(SavingsDTO savingsDTO) {
        if (accountHolderRepository.findById(savingsDTO.getPrimaryOwner()).isPresent()) {
            AccountHolder primarySavingAccountHolder = accountHolderRepository.findById(savingsDTO.getPrimaryOwner()).get();
            AccountHolder secondarySavingAccountHolder = null;

            if (savingsDTO.getSecondaryOwner() != null) {
                if (accountHolderRepository.findById(savingsDTO.getSecondaryOwner().get()).isPresent()) {
                    secondarySavingAccountHolder = accountHolderRepository.findById(savingsDTO.getSecondaryOwner().get()).get();
                }
            }
            Savings savings = savingsRepository.save(new Savings(savingsDTO.getBalance(), primarySavingAccountHolder, secondarySavingAccountHolder, savingsDTO.getSecretKey()));

        }
        throw new IllegalArgumentException("Create a Primary Account Holder before Saving Account");
    }

    public CreditCard createCreditCardAccount(CreditCardDTO creditCardDTO) {
        if (accountHolderRepository.findById(creditCardDTO.getPrimaryOwner()).isPresent()) {
            AccountHolder primaryCreditCardAccountHolder = accountHolderRepository.findById(creditCardDTO.getPrimaryOwner()).get();
            AccountHolder secondaryCreditCardAccountHolder = null;

            if (creditCardDTO.getSecondaryOwner() != null) {
                if (accountHolderRepository.findById(creditCardDTO.getSecondaryOwner().get()).isPresent()) {
                    secondaryCreditCardAccountHolder = accountHolderRepository.findById(creditCardDTO.getSecondaryOwner().get()).get();
                }
            }
            CreditCard creditCard = creditCardRepository.save(new CreditCard(creditCardDTO.getBalance(), primaryCreditCardAccountHolder, secondaryCreditCardAccountHolder));

        }
        throw new IllegalArgumentException("Create a Primary Account Holder before Credit Card Account");
    }

    public ThirdParty createThirdPartyUser (ThirdPartyDTO thirdPartyDTO){
        ThirdParty thirdParty = thirdPartyRepository.save(new ThirdParty(thirdPartyDTO.getName(), thirdPartyDTO.getPassword(), thirdPartyDTO.getKey()));
        return thirdParty;
    }

    public BigDecimal checkBalance(Long accountId) {

        return accountRepository.findById(accountId).get().getBalance();
    }

    public Account updateAccountBalance(AccountDTO accountDTO) {
        Account account = accountRepository.findById(accountDTO.getAccountId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        if(account instanceof Savings){
            savingsRepository.findById(accountDTO.getAccountId()).get().checkInterestSavingsBalance();
        }
        else if( account instanceof CreditCard ){
            creditCardRepository.findById(accountDTO.getAccountId()).get().checkInterestCreditCardBalance();
        }
        BigDecimal newBalance = accountDTO.getNewBalance();
        account.setBalance(newBalance);
        return accountRepository.save(account);
    }

    public void deleteAccount(Long accountId){
        accountRepository.delete(accountRepository.findById(accountId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found")));
    }


}
