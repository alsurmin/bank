package com.example.bank.controller;

import com.example.bank.domain.AccountTransaction;
import com.example.bank.domain.BankAccount;
import com.example.bank.domain.Views;
import com.example.bank.exception.NotEnoughMoneyException;
import com.example.bank.repo.AccountTransactionRepo;
import com.example.bank.repo.BankAccountRepo;
import com.example.bank.service.BankAccountService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("account")
public class AccountController {

    private final BankAccountRepo bankAccountRepo;
    private final AccountTransactionRepo accountTransactionRepo;
    private final BankAccountService bankAccountService;

    @GetMapping("{id}")
    public BankAccount getBankAccountById(@PathVariable Long id) {
        return bankAccountRepo.getById(id);
    }

    @GetMapping("/{id}/transactions")
    @JsonView(Views.FullTransaction.class)
    public List<AccountTransaction> getTransactionsByAccountId(@PathVariable Long id) {
        return accountTransactionRepo.findAllByBankAccountId(id);
    }

    @PostMapping
    public ResponseEntity<String> sendMoney(@RequestParam BankAccount sender,
                                            @RequestParam BankAccount receiver,
                                            @RequestParam Double amount) {
        try {
            bankAccountService.sendMoney(sender, receiver, amount);
            return ResponseEntity.ok("SUCCESS");
        } catch (NotEnoughMoneyException e) {
            log.warn(e.getMessage(), e);
            return ResponseEntity.badRequest().body("NOT ENOUGH MONEY");
        }
    }

}
