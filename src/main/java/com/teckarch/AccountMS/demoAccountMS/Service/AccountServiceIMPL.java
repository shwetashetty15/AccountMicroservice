package com.teckarch.AccountMS.demoAccountMS.Service;

import com.teckarch.AccountMS.demoAccountMS.DTO.UserDTO;
import com.teckarch.AccountMS.demoAccountMS.Model.AccountModel;
import com.teckarch.AccountMS.demoAccountMS.Repository.AccountRepository;
import com.teckarch.AccountMS.demoAccountMS.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceIMPL implements AccountService {
    @Autowired
    private RestTemplate restTemplate;
    private final String USER_MS_URL = "http://localhost:8080/users"; // Assuming your User MS is on port 8080

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountModel createAccount(AccountModel account) {
        ResponseEntity<UserDTO> userResponse = restTemplate.exchange(
                USER_MS_URL + "/" + account.getUserId(),
                HttpMethod.GET,
                null,
                UserDTO.class
        );
        if (userResponse.getStatusCode() == HttpStatus.OK) {
            // User exists, proceed with account creation
            return accountRepository.save(account);
        } else {
            throw new UserNotFoundException("User with ID " + account.getUserId() + " not found.");
        }

    }

    @Override
    public AccountModel updateAccount(AccountModel account) {
        return accountRepository.save(account);
    }

    @Override
    public AccountModel getAccountById(Long accountId) {
        Optional<AccountModel> account = accountRepository.findById(accountId);
        return account.orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @Override
    public List<AccountModel> getAccountsByUserId(Long userId) {
        return accountRepository.findByUserId(userId);
    }

    @Override
    public void deleteAccountById(Long accountId) {
        accountRepository.deleteById(accountId);
    }
}

