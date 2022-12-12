package com.example.BankingSystem.DTOs;

import java.math.BigDecimal;

public class AccountDTO {
        private Long accountId;
        private BigDecimal newBalance;

        public AccountDTO(Long accountId, BigDecimal newBalance) {
            this.accountId = accountId;
            this.newBalance = newBalance;
        }

        public Long getAccountId() {
            return accountId;
        }

        public void setAccountId(Long accountId) {
            this.accountId = accountId;
        }

        public BigDecimal getNewBalance() {
            return newBalance;
        }

        public void setNewBalance(BigDecimal newBalance) {
            this.newBalance = newBalance;
        }
}
