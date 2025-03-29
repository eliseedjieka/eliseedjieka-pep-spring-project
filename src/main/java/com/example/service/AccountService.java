package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account account) {
        if (accountRepository.findByUsername(account.getUsername()) != null) {
            throw new IllegalArgumentException("Username already exists.");
        }
        if (account.getPassword().length() < 4) {
            throw new IllegalArgumentException("Password must be at least 4 characters.");
        }
        return accountRepository.save(account);
    }

    public Account loginAccount(Account account) {
        Account foundAccount = accountRepository.findByUsername(account.getUsername());
        if (foundAccount != null && foundAccount.getPassword().equals(account.getPassword())) {
            return foundAccount;
        }
        return null;
    }
}
