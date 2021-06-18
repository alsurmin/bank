package com.example.bank.service;

import com.example.bank.domain.AccountTransaction;
import com.example.bank.domain.AccountTransactionType;
import com.example.bank.domain.BankAccount;
import com.example.bank.exception.NotEnoughMoneyException;
import com.example.bank.repo.AccountTransactionRepo;
import com.example.bank.repo.BankAccountRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
@AllArgsConstructor
public class BankAccountService {
    private final AccountTransactionRepo accountTransactionRepo;
    private final BankAccountRepo bankAccountRepo;

    @Transactional
    public void sendMoney(BankAccount sender, BankAccount receiver, Double amount) throws NotEnoughMoneyException {
        if (sender.getBalance().doubleValue() < amount) {
            throw new NotEnoughMoneyException("NOT ENOUGH MONEY");
        }
        AccountTransaction sendTransaction = AccountTransaction.builder().bankAccount(sender)
                .amount(new BigDecimal(amount)).type(AccountTransactionType.SEND)
                .createdDate(LocalDateTime.now()).updatedDate(LocalDateTime.now()).build();
        AccountTransaction receiveTransaction = AccountTransaction.builder().bankAccount(receiver)
                .amount(new BigDecimal(amount)).type(AccountTransactionType.RECEIVE)
                .createdDate(LocalDateTime.now()).updatedDate(LocalDateTime.now()).build();

        accountTransactionRepo.saveAll(Arrays.asList(sendTransaction, receiveTransaction));

        sender.setBalance(sender.getBalance().subtract(new BigDecimal(amount)));
        receiver.setBalance(sender.getBalance().add(new BigDecimal(amount)));

        bankAccountRepo.saveAll(Arrays.asList(sender, receiver));
    }

}
