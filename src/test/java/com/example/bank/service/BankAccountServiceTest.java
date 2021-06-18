package com.example.bank.service;

import com.example.bank.domain.BankAccount;
import com.example.bank.repo.AccountTransactionRepo;
import com.example.bank.repo.BankAccountRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@TestPropertySource("/application-test.properties")
@Sql(value = {"/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class BankAccountServiceTest {

    @Autowired
    private BankAccountService bankAccountService;
    @Autowired
    private AccountTransactionRepo accountTransactionRepo;
    @Autowired
    private BankAccountRepo bankAccountRepo;

    @Test
    public void testSendMoneySuccess() throws Exception {
        BankAccount sender = bankAccountRepo.getById(1L);
        BankAccount receiver = bankAccountRepo.getById(2L);

        assertEquals(accountTransactionRepo.findAllByBankAccountId(1L).size(), 3);
        assertEquals(bankAccountRepo.getById(1L).getBalance().doubleValue(), 1150d);

        bankAccountService.sendMoney(sender, receiver, 100d);

        assertEquals(accountTransactionRepo.findAllByBankAccountId(1L).size(), 4);
        assertEquals(bankAccountRepo.getById(1L).getBalance().doubleValue(), 1050d);
    }

}