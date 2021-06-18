package com.example.bank.repo;

import com.example.bank.domain.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AccountTransactionRepo extends JpaRepository<AccountTransaction, Long> {
    public List<AccountTransaction> findAllByBankAccountId(Long accountId);
}
