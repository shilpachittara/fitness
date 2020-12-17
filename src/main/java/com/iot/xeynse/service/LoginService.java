package com.iot.xeynse.service;

import com.iot.xeynse.dao.AccountDao;
import com.iot.xeynse.dao.UserDao;
import com.iot.xeynse.entity.AccountEntity;
import com.iot.xeynse.exception.UserException;
import com.iot.xeynse.model.AuthResponse;
import com.iot.xeynse.model.UserLoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static org.springframework.util.ObjectUtils.isEmpty;


@Service
@Slf4j
public class LoginService {

    @Autowired
    PasswordEncryptor passwordEncryptor;

    @Autowired
    AccountDao accountDao;

    @Autowired
    UserDao userDao;

    @Autowired
    private AuthTokenService authTokenService;

    public AuthResponse userLogin(UserLoginRequest request) {
        AccountEntity accountEntity = accountDao.findByEmailOrPhone(request.getEmail(), request.getPhone());
        if (isEmpty(accountEntity)) {
            throw new UserException("Invalid username or password", HttpStatus.BAD_REQUEST);
        }
        if (!(request.isFacebookLogin() || request.isGoogleLogin())) {
            if (!passwordEncryptor.checkPassword(request.getPassword(), accountEntity.getPassword())) {
                throw new UserException("Invalid username or password", HttpStatus.BAD_REQUEST);
            }
        }
        AuthResponse authResponse = authTokenService.login(accountEntity);
        return authResponse;
    }

    public AuthResponse userRegister(UserLoginRequest request) {
        AccountEntity accountEntity = accountDao.findByEmailOrPhone(request.getEmail(), request.getPhone());
        if (!isEmpty(accountEntity)) {
            throw new UserException("User Already Exists", HttpStatus.BAD_REQUEST);
        }
        if (!(request.isFacebookLogin() || request.isGoogleLogin())) {
            if(isEmpty(request.getPassword())){
                throw new UserException("Invalid password", HttpStatus.BAD_REQUEST);
            }
        }
        accountEntity = new AccountEntity();
        accountEntity.setPassword(passwordEncryptor.encryptPassword(request.getPassword()));
        accountEntity.setCreated(LocalDateTime.now());
        accountEntity.setUpdated(LocalDateTime.now());
        accountEntity.setEmail(request.getEmail());
        accountEntity.setPhone(request.getPhone());
        accountEntity = accountDao.save(accountEntity);
        AuthResponse authResponse = authTokenService.login(accountEntity);
        return authResponse;
    }


}
