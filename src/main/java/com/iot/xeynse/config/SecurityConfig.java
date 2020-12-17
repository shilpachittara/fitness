package com.iot.xeynse.config;

import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncryptor passwordEncryptor() {
        StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
        return passwordEncryptor;
    }
}