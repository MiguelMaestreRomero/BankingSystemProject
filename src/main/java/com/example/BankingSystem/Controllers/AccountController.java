package com.example.BankingSystem.Controllers;

import com.example.BankingSystem.DTOs.*;
import com.example.BankingSystem.Repository.AccountRepository;
import com.example.BankingSystem.Services.AccountHolderService;
import com.example.BankingSystem.Services.AdminService;
import com.example.BankingSystem.Services.ThirdPartyService;
import com.example.BankingSystem.models.Accounts.Account;
import com.example.BankingSystem.models.Transaction;
import com.example.BankingSystem.models.Users.AccountHolder;
import com.example.BankingSystem.models.Users.ThirdParty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class AccountController {

    @Autowired
    AccountHolderService accountHolderService;

    @Autowired
    AdminService adminService;

    @Autowired
    ThirdPartyService thirdPartyService;

    @Autowired
    AccountRepository accountRepository;


    @PostMapping("/create_account_holder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder createAccountHolder (@RequestBody AccountHolderDTO accountHolderDTO) {
        return accountHolderService.createAccountHolder(accountHolderDTO);
    }

    @GetMapping("/get_balance")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal checkBalance(@RequestParam Long accountId){
        return accountHolderService.checkBalance(accountId);
    }

    @PatchMapping("/update_balance")
    @ResponseStatus(HttpStatus.OK)
    public Account updateAccountBalance(@RequestBody AccountDTO accountDTO){
        return adminService.updateAccountBalance(accountDTO);
    }

    @PostMapping("/check_transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction checkTransaction(@RequestBody TransactionDTO transactionDTO) {
        return accountHolderService.checkTransaction(transactionDTO);
    }

    @PostMapping("/create_checking_account")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createCheckingAccount (@RequestBody CheckingDTO checkingDTO){
        return adminService.createCheckingAccount(checkingDTO);
    }

    @PostMapping("/create_saving_account")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createSavingsAccount (@RequestBody SavingsDTO savingsDTO){
        return adminService.createSavingsAccount(savingsDTO);
    }

    @PostMapping("/create_credit_card_account")
    @ResponseStatus(HttpStatus.CREATED)
    public Account createCreditCardAccount (@RequestBody CreditCardDTO creditCardDTO){
        return adminService.createCreditCardAccount(creditCardDTO);
    }

    @PostMapping("/create_third_party")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createThirdPartyUser(@RequestBody ThirdPartyDTO thirdPartyDTO) {
        return adminService.createThirdPartyUser(thirdPartyDTO);
    }

    @PatchMapping("/third_party_transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction thirdPartyTransaction(@RequestBody TransactionDTO transactionDTO) {
        return thirdPartyService.checkTransaction(transactionDTO);
    }

    @DeleteMapping("/delete_account/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@PathVariable Long accountId){
        adminService.deleteAccount(accountId);
    }

}
