package com.example.BankingSystem;

import com.example.BankingSystem.DTOs.TransactionDTO;
import com.example.BankingSystem.Repository.AccountHolderRepository;
import com.example.BankingSystem.Repository.AccountRepository;
import com.example.BankingSystem.Repository.TransactionRepository;
import com.example.BankingSystem.models.Accounts.Account;
import com.example.BankingSystem.models.Accounts.Checking;
import com.example.BankingSystem.models.Embedded.Address;
import com.example.BankingSystem.models.Transaction;
import com.example.BankingSystem.models.Users.AccountHolder;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AccountTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    Address address;
    AccountHolder accountHolder1;
    AccountHolder accountHolder2;
    AccountHolder accountHolder3;
    AccountHolder accountHolder4;

    Checking checkingAccount1;
    Checking checkingAccount2;

    TransactionDTO transactionDTO;

    Account originAccount;
    Account destinyAccount;


    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());


    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        Address address1 = new Address("Diputacion", "Barcelona", "Barcelona", "08013", "Spain");
        Address address2 = new Address("Sicilia", "Barcelona", "Barcelona", "08013", "Spain");
        Address address3 = new Address("Parlament", "Barcelona", "Barcelona", "08013", "Spain");
        Address address4 = new Address("Aragon", "Barcelona", "Barcelona", "08013", "Spain");


        accountHolder1 = new AccountHolder("Miguel", "123", LocalDate.of(1994, 8, 16), address1, "miguel@gmail.com");
        accountHolder2 = new AccountHolder("Maria", "234", LocalDate.of(1995, 10, 14), address1, "maria@gmail.com");
        accountHolder3 = new AccountHolder("Marta", "567", LocalDate.of(1992, 4, 13), address1, "marta@gmail.com");
        accountHolder4 = new AccountHolder("Irene", "678", LocalDate.of(2000, 2, 6), address1, "irene@gmail.com");
        accountHolderRepository.saveAll(List.of(accountHolder1, accountHolder2, accountHolder3, accountHolder4));

        checkingAccount1 = new Checking(new BigDecimal(1000), accountHolder1, 123);
        checkingAccount2 = new Checking(new BigDecimal(2000), accountHolder2, 234);
        accountRepository.saveAll(List.of(checkingAccount1,checkingAccount2));

    }

    @AfterEach
    void tearDown(){
        //accountRepository.deleteAll();
        //accountHolderRepository.deleteAll();

    }

    /*@Test
    void createAccountHolder() {
        AccountHolder accountTest = accountHolderRepository.findById(accountHolder1.getId()).get();
        assertEquals("Miguel", accountTest.getName());
    }*/


   /* @Test
    void shouldAddNewTransference() {
        originAccount = accountRepository.findById(1L).get();
        destinyAccount = accountRepository.findById(2L).get();
        transactionRepository.save(new Transaction(new BigDecimal(100), "Maria", originAccount, destinyAccount));
        assertEquals(1, transactionRepository.findAll().size());
    }*/

    @Test
    void transfer_works() throws Exception {
        originAccount = accountRepository.findById(1L).get();
        destinyAccount = accountRepository.findById(2L).get();
        //System.err.println(originAccount.getBalance());
        //System.err.println(destinyAccount.getBalance());
        transactionDTO = new TransactionDTO(new BigDecimal(10),"Maria", 1L, 2L);
        String body = objectMapper.writeValueAsString(transactionDTO);
        MvcResult mvcResult = mockMvc.perform(post("/check_transaction").content(body).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andReturn();
        originAccount = accountRepository.findById(1L).get();
        destinyAccount = accountRepository.findById(2L).get();
        System.err.println(originAccount.getBalance());
        System.err.println(destinyAccount.getBalance());
        assertTrue(mvcResult.getResponse().getContentAsString().contains("Maria"));
        assertEquals(new BigDecimal("2010.00"), destinyAccount.getBalance());
    }

}
