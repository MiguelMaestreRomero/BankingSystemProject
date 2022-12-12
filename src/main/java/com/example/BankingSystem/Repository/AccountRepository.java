package com.example.BankingSystem.Repository;

import com.example.BankingSystem.models.Accounts.Account;
import com.example.BankingSystem.models.Users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByIdAndPrimaryOwnerName(Long accountId, AccountHolder accountHolder);
    Optional<Account> findByIdAndSecondaryOwnerId(Long accountId, Long accountHolderId);
}
