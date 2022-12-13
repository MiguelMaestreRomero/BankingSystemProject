package com.example.BankingSystem;

import com.example.BankingSystem.Repository.AccountHolderRepository;
import com.example.BankingSystem.models.Embedded.Address;
import com.example.BankingSystem.models.Users.AccountHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class BankingSystemApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BankingSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
