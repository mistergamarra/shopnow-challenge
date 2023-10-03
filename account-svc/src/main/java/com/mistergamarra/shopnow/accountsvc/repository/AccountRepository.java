package com.mistergamarra.shopnow.accountsvc.repository;

import com.mistergamarra.shopnow.accountsvc.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    Optional<Account> findAccountByUuid(String uuid);

    Optional<Account> findAccountByUsername(String username);

}
