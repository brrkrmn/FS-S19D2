package com.workintech.s18d4.service;

import com.workintech.s18d4.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();
    Account find(Integer id);
    Account save(Account account);
    Account delete(Integer id);
}
