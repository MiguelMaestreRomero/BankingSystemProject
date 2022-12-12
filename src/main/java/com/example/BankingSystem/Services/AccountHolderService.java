package com.example.BankingSystem.Services;

import com.example.BankingSystem.DTOs.AccountHolderDTO;
import com.example.BankingSystem.DTOs.TransactionDTO;
import com.example.BankingSystem.Repository.*;
import com.example.BankingSystem.models.Accounts.Account;
import com.example.BankingSystem.models.Accounts.Checking;
import com.example.BankingSystem.models.Accounts.Savings;
import com.example.BankingSystem.models.Transaction;
import com.example.BankingSystem.models.Users.AccountHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class AccountHolderService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CheckingRepository checkingRepository;

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    TransactionRepository transactionRepository;


    public AccountHolder createAccountHolder(AccountHolderDTO accountHolderDTO) {
        AccountHolder accountHolder = new AccountHolder(accountHolderDTO.getName(), accountHolderDTO.getPassword(), accountHolderDTO.getDateOfBirth(), accountHolderDTO.getPrimaryAddress(), accountHolderDTO.getMailingAddress());
        return accountHolderRepository.save(accountHolder);
    }

    public BigDecimal checkBalance(Long accountId) {

        return accountRepository.findById(accountId).get().getBalance();
    }

 /*   public Transaction checkTransaction(TransactionDTO transactionDTO) {
        Account destinyAccount = accountRepository.findById(transactionDTO.getDestinyAccountId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destiny Account not found"));
        AccountHolder destinyAccountOwner = accountHolderRepository.findByName(transactionDTO.getDestinyAccountHolderName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destiny Account Holder not found"));
        Transaction transaction;
        Account originAccount;
        Long accountId = transactionDTO.getOriginAccountId();
        BigDecimal balance;
       if (accountRepository.findById(accountId).isPresent()) {
            originAccount = accountRepository.findById(accountId).get();
            balance = originAccount.getBalance();
            if (originAccount instanceof Checking) {
                Checking checking = (Checking) originAccount;
                if (originAccount.getBalance().compareTo(((Checking) originAccount).getMinimumBalance()) < 0) {
                    originAccount.setBalance(originAccount.getBalance().subtract(originAccount.getPenaltyFee()));
                    accountRepository.save(originAccount);
                }
            }
            if (originAccount instanceof Savings) {
                Savings savings = (Savings) originAccount;
                if (originAccount.getBalance().compareTo(((Savings) originAccount).getMinimumBalance()) < 0) {
                    originAccount.setBalance(originAccount.getBalance().subtract(originAccount.getPenaltyFee()));
                    accountRepository.save(originAccount);
                }
            }
            if (originAccount.getBalance().compareTo(transactionDTO.getAmount()) < 0) {
                originAccount.setBalance(originAccount.getBalance().subtract(transactionDTO.getAmount()));
                destinyAccount.setBalance(destinyAccount.getBalance().add(transactionDTO.getAmount()));
                accountRepository.save(originAccount);
                accountRepository.save(destinyAccount);
                transaction = new Transaction(transactionDTO.getAmount(), transactionDTO.getDestinyAccountHolderName(), originAccount, destinyAccount);
                return transactionRepository.save(transaction);

            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Not enough funds");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sending Account not found");
        }
    }*/

    public Transaction checkTransaction(TransactionDTO transactionDTO) {
        Account originAccount = accountRepository.findById(transactionDTO.getOriginAccountId()).get();
        Account destinyAccount = accountRepository.findById(transactionDTO.getDestinyAccountId()).get();
        //System.err.println(originAccount.getBalance());
        //System.err.println(destinyAccount.getBalance());
        originAccount.setBalance(originAccount.getBalance().subtract(transactionDTO.getAmount()));
        destinyAccount.setBalance(destinyAccount.getBalance().add(transactionDTO.getAmount()));
        //System.err.println(originAccount.getBalance());
        //System.err.println(destinyAccount.getBalance());
        if (originAccount instanceof Checking) {
            System.err.println("he entrado en checking");
            //Checking checking = (Checking) originAccount;
            if (originAccount.getBalance().compareTo(((Checking) originAccount).getMinimumBalance()) < 0){
                System.err.println("he entrado en penaltyfee");
                originAccount.setBalance(originAccount.getBalance().subtract(originAccount.getPenaltyFee()));
                System.err.println(originAccount.getBalance());
                accountRepository.save(originAccount);
            }
        }
        if (originAccount instanceof Savings) {
            //Savings savings = (Savings) originAccount;
            System.err.println("he entrado en savings");
            if (originAccount.getBalance().compareTo(((Savings) originAccount).getMinimumBalance()) < 0){
                originAccount.setBalance(originAccount.getBalance().subtract(originAccount.getPenaltyFee()));
                accountRepository.save(originAccount);
            }
        }

        accountRepository.save(originAccount);
        accountRepository.save(destinyAccount);
        System.err.println(originAccount.getBalance());
        System.err.println(destinyAccount.getBalance());
        Transaction transaction = new Transaction(transactionDTO.getAmount(), transactionDTO.getDestinyAccountHolderName(), originAccount, destinyAccount);
        transactionRepository.save(transaction);
        return transaction;
    }
}









