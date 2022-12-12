package com.example.BankingSystem.Services;

import com.example.BankingSystem.DTOs.TransactionDTO;
import com.example.BankingSystem.Repository.AccountHolderRepository;
import com.example.BankingSystem.Repository.AccountRepository;
import com.example.BankingSystem.Repository.TransactionRepository;
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
public class ThirdPartyService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountHolderRepository accountHolderRepository;

    public Transaction checkTransaction(TransactionDTO transactionDTO) {
        Account destinyAccount = accountRepository.findById(transactionDTO.getDestinyAccountId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destiny Account not found"));
        AccountHolder destinyAccountOwner = accountHolderRepository.findByName(transactionDTO.getDestinyAccountHolderName()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Destiny Account Holder not found"));
        Transaction transaction;
        Account originAccount;
        Long accountId = transactionDTO.getOriginAccountId();
        BigDecimal balance;
        if (accountRepository.findByIdAndPrimaryOwnerName(accountId, destinyAccountOwner).isPresent()) {
            originAccount = accountRepository.findByIdAndPrimaryOwnerName(accountId, destinyAccountOwner).get();
            balance = originAccount.getBalance();
            if (originAccount instanceof Checking) {
                //Checking checking = (Checking) originAccount;
                if (balance.compareTo(((Checking) originAccount).getMinimumBalance()) < 0) {
                    originAccount.setBalance(originAccount.getBalance().subtract(originAccount.getPenaltyFee()));
                    accountRepository.save(originAccount);
                }
            }
            if (originAccount instanceof Savings) {
                //Savings savings = (Savings) originAccount;
                if (balance.compareTo(((Savings) originAccount).getMinimumBalance()) < 0) {
                    originAccount.setBalance(originAccount.getBalance().subtract(originAccount.getPenaltyFee()));
                    accountRepository.save(originAccount);
                }
            }
            if (balance.compareTo(transactionDTO.getAmount()) == 1) {
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
    }
}
