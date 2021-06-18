package com.example.bank.repo;

import com.example.bank.domain.AccountTransaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class BankAccountRepoTest {

    @Autowired
    private AccountTransactionRepo accountTransactionRepo;

    @Test
    public void testTransactionList() throws Exception {
        List<AccountTransaction> transactions = accountTransactionRepo.findAllByBankAccountId(1L);
        assertEquals(transactions.size(), 3);
    }

}