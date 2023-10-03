package com.mistergamarra.shopnow.accountsvc.service;

import com.mistergamarra.shopnow.accountsvc.dto.AccountDto;
import com.mistergamarra.shopnow.accountsvc.dto.JwtDto;
import com.mistergamarra.shopnow.accountsvc.dto.LoginDto;
import com.mistergamarra.shopnow.accountsvc.exception.LoginException;
import com.mistergamarra.shopnow.accountsvc.jwt.JwtUtil;
import com.mistergamarra.shopnow.accountsvc.model.Account;
import com.mistergamarra.shopnow.accountsvc.repository.AccountRepository;
import com.mistergamarra.shopnow.accountsvc.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class AccountService {

    AccountRepository accountRepository;
    DozerBeanMapper dozerBeanMapper;

    PasswordEncoder passwordEncoder;

    JwtUtil jwt;

    @Value("${com.account-svc.default-password}")
    String defaultPassword;

    public AccountService(AccountRepository accountRepository, DozerBeanMapper dozerBeanMapper, PasswordEncoder passwordEncoder,final JwtUtil jwt) {
        this.accountRepository = accountRepository;
        this.dozerBeanMapper = dozerBeanMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwt = jwt;
    }

    public AccountDto getByUuid(String uuid){
        Optional<Account> account = accountRepository.findAccountByUuid(uuid);
        if (account.isPresent()){
            Account p = account.get();
            log.info("msg=account was found uuid={} username={}",p.getUuid(),p.getUsername());
            return dozerBeanMapper.map(p, AccountDto.class);
        }
        throw new NotFoundException("account not found");
    }

    public AccountDto save(AccountDto accountDto){
        log.info("msg=creating account username={}",accountDto.getUsername());
        Account account = dozerBeanMapper.map(accountDto, Account.class);
        account.setUuid(UUID.randomUUID().toString());
        account.setCreatedAt(LocalDateTime.now(ZoneOffset.UTC));
        account.setModifiedAt(LocalDateTime.now(ZoneOffset.UTC));
        account.setCreatedBy("admin");
        account.setModifiedBy("admin");
        String encodedPassword = passwordEncoder.encode(defaultPassword);
        account.setPassword(encodedPassword);
        Account saved = accountRepository.save(account);
        return dozerBeanMapper.map(saved, AccountDto.class);
    }

    public JwtDto login(LoginDto loginDto){
        Optional<Account> accountDto = accountRepository.findAccountByUsername(loginDto.getUsername());
        if (accountDto.isEmpty()){
            log.warn("msg=user not found username={}",loginDto.getUsername());
            throw new LoginException("user not found");
        }

        boolean matches = passwordEncoder.matches(loginDto.getPassword(), accountDto.get().getPassword());
        if (!matches){
            log.warn("msg=password does not match username={}",loginDto.getUsername());
            throw new LoginException("invalid password");
        }
        String accessToken = jwt.generate(accountDto.get(), "ACCESS");
        String refreshToken = jwt.generate(accountDto.get(), "REFRESH");

        return new JwtDto(accessToken,refreshToken);
    }

}
