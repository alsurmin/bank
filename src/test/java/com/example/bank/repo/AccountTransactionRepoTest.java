package com.example.bank.repo;

import com.example.bank.domain.BankAccount;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@TestPropertySource("/application-test.properties")
@Sql(value = {"/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class AccountTransactionRepoTest {

    @Autowired
    private BankAccountRepo bankAccountRepo;

    @Test
    public void testGetAccountBalance() throws Exception {
        Optional<BankAccount> bankAccount = bankAccountRepo.findById(1L);
        assertTrue(bankAccount.isPresent());
        assertEquals(bankAccount.get().getBalance().doubleValue(), 1150D);
    }

}