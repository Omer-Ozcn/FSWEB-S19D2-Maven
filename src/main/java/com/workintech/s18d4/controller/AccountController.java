package com.workintech.s18d4.controller;

import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    private final AccountService accountService;
    private final CustomerService customerService;

    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping("/account")
    public ResponseEntity<List<Account>> findAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<Account> find(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.find(id));
    }

    @PostMapping("/account/{customerId}")
    public ResponseEntity<Account> save(@PathVariable Long customerId, @RequestBody Account account) {
        Customer customer = customerService.find(customerId);
        account.setCustomer(customer);
        return ResponseEntity.ok(accountService.save(account));
    }

    @PutMapping("/account/{customerId}")
    public ResponseEntity<Account> update(@PathVariable Long customerId, @RequestBody Account updated) {
        Customer customer = customerService.find(customerId);
        Account existing = accountService.find(updated.getId());
        if (existing != null) {
            existing.setAccountName(updated.getAccountName());
            existing.setMoneyAmount(updated.getMoneyAmount());
            existing.setCustomer(customer);
            return ResponseEntity.ok(accountService.save(existing));
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/account/{id}")
    public ResponseEntity<Account> delete(@PathVariable Long id) {
        Account found = accountService.find(id);
        accountService.delete(id);
        return ResponseEntity.ok(found);
    }
}
