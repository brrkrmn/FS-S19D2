package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.AccountResponse;
import com.workintech.s18d4.entity.Account;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.AccountService;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;
    private CustomerService customerService;

    @Autowired
    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping()
    public List<AccountResponse> findAll() {
        List<Account> accounts = accountService.findAll();
        return accounts.stream().map(account -> new AccountResponse(
                account.getId(),
                account.getAccountName(),
                account.getMoneyAmount(),
                account.getCustomer()
        )).toList();
    }

    @GetMapping("/{id}")
    public AccountResponse findById(@PathVariable Integer id) {
        Account account = accountService.find(id);
        return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(), account.getCustomer());
    }

    @PostMapping("/{customerId}")
    public AccountResponse save(@PathVariable Integer customerId, @RequestBody Account account) {
        Customer customer = customerService.find(customerId);
        customer.addAccount(account);
        account.setCustomer(customer);
        Account a = accountService.save(account);
        return new AccountResponse(a.getId(), a.getAccountName(), a.getMoneyAmount(), a.getCustomer());
    }

    @PutMapping("/{customerId}")
    public AccountResponse update(@PathVariable Integer customerId, @RequestBody Account account) {
        Customer customer = customerService.find(customerId);
        account.setCustomer(customer);
        Account a = accountService.save(account);
        return new AccountResponse(a.getId(), a.getAccountName(), a.getMoneyAmount(), a.getCustomer());
    }

    @DeleteMapping("/{id}")
    public AccountResponse delete(@PathVariable Integer id) {
        Account account = accountService.delete(id);
        return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(), account.getCustomer());
    }
}
