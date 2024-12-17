package com.teckarch.AccountMS.demoAccountMS.Service;

import com.teckarch.AccountMS.demoAccountMS.Model.AccountModel;

import java.util.List;

public interface AccountService {

    AccountModel createAccount(AccountModel account);
    AccountModel updateAccount(AccountModel account);
    AccountModel getAccountById(Long accountId);
    List<AccountModel> getAccountsByUserId(Long userId);
    void deleteAccountById(Long accountId);
}
