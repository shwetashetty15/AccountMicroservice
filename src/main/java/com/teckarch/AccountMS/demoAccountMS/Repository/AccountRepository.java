package com.teckarch.AccountMS.demoAccountMS.Repository;

import com.teckarch.AccountMS.demoAccountMS.Model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<AccountModel,Long> {
    List<AccountModel> findByUserId(Long userId);
    List<AccountModel> findByUserIdAndAccountType(Long userId, String accountType);
}
