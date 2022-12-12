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

    public Transaction checkTransaction(TransactionDTO transactionDTO) {
        Account destinyAccount = accountRepository.findById(transactionDTO.getDestinyAccountId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destiny Account not found"));
        AccountHolder destinyAccountOwner = accountHolderRepository.findByName(transactionDTO.getDestinyAccountHolderName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destiny Account Holder not found"));
        Account originAccount;
        Long accountId = transactionDTO.getOriginAccountId();
        if (accountRepository.findById(accountId).isPresent()) {
            originAccount = accountRepository.findById(accountId).get();
            if (originAccount instanceof Checking) {
                if (originAccount.getBalance().compareTo(((Checking) originAccount).getMinimumBalance()) < 0) {
                    originAccount.setBalance(originAccount.getBalance().subtract(originAccount.getPenaltyFee()));
                    System.err.println(originAccount.getBalance());
                    accountRepository.save(originAccount);
                }
            }
            if (originAccount instanceof Savings) {
                if (originAccount.getBalance().compareTo(((Savings) originAccount).getMinimumBalance()) < 0) {
                    originAccount.setBalance(originAccount.getBalance().subtract(originAccount.getPenaltyFee()));
                    accountRepository.save(originAccount);
                }
            }
            if (originAccount.getBalance().compareTo(transactionDTO.getAmount()) > 0) {
                originAccount.setBalance(originAccount.getBalance().subtract(transactionDTO.getAmount()));
                destinyAccount.setBalance(destinyAccount.getBalance().add(transactionDTO.getAmount()));
                accountRepository.save(originAccount);
                accountRepository.save(destinyAccount);
                Transaction transaction = new Transaction(transactionDTO.getAmount(), transactionDTO.getDestinyAccountHolderName(), originAccount, destinyAccount);
                transactionRepository.save(transaction);
                return transaction;

            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Not enough funds");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sending Account not found");
        }
    }
}









