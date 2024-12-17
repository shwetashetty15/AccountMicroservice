package com.teckarch.AccountMS.demoAccountMS.Controller;


import com.teckarch.AccountMS.demoAccountMS.Model.AccountModel;
import com.teckarch.AccountMS.demoAccountMS.Service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // Create Account
    @PostMapping
    public ResponseEntity<AccountModel> createAccount(@RequestBody AccountModel account) {
        AccountModel newAccount = accountService.createAccount(account);
        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

    // Update Account
    @PutMapping
    public ResponseEntity<AccountModel> updateAccount(@RequestBody AccountModel account) {
        AccountModel updatedAccount = accountService.updateAccount(account);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    // Update Account using userId and accountId
    @PutMapping(params = {"userId", "accountId"})
    public ResponseEntity<AccountModel> updateAccountByQuery(@RequestParam Long userId, @RequestParam Long accountId, @RequestBody AccountModel account) {
        account.setUserId(userId);
        account.setAccountId(accountId);
        AccountModel updatedAccount = accountService.updateAccount(account);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    // Retrieve all accounts
    @GetMapping
    public ResponseEntity<List<AccountModel>> getAllAccounts() {
        List<AccountModel> accounts = accountService.getAccountsByUserId(null);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    // Retrieve account by userId
    @GetMapping("/{userId}/account")
    public ResponseEntity<List<AccountModel>> getUserAccounts(@PathVariable Long userId) {
        List<AccountModel> accounts = accountService.getAccountsByUserId(userId);
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    // Retrieve account by accountId
    @GetMapping("/account/{accountId}")
    public ResponseEntity<AccountModel> getAccountById(@PathVariable Long accountId) {
        AccountModel account = accountService.getAccountById(accountId);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    // Delete account by accountId
    @DeleteMapping("/account/{accountId}")
    public ResponseEntity<Void> deleteAccountById(@PathVariable Long accountId) {
        accountService.deleteAccountById(accountId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

