package com.example.bank.repo;

import com.example.bank.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepo extends JpaRepository<BankAccount, Long> {
}
