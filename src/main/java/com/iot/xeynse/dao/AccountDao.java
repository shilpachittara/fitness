package com.iot.xeynse.dao;

import com.iot.xeynse.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDao extends JpaRepository<AccountEntity, Long> {

    AccountEntity findByEmailOrPhone(String email, String phone);

}
