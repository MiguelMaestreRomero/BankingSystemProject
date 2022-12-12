package com.example.BankingSystem.Repository;

import com.example.BankingSystem.models.Accounts.Account;
import com.example.BankingSystem.models.Users.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {

    Optional<AccountHolder> findByName(String name);

}
