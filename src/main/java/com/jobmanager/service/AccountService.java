package com.jobmanager.service;

import com.jobmanager.entitiy.Account;
import com.jobmanager.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Account findByUsername(String name) {
        return repository.findByUsername(name).orElse(null);
    }

    public void createAccount(Account account) {
        repository.save(account);
    }

    public void updateAccount(Account account) {
        repository.save(account);
    }

    public void deleteAccount(Long id) {
        repository.deleteById(id);
    }
}
