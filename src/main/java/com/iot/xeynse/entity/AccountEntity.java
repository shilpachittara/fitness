package com.iot.xeynse.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "xn_account_detail")
public class AccountEntity {

    @Column(name = "be_created")
    protected LocalDateTime created = LocalDateTime.now();
    @Column(name = "be_updated")
    protected LocalDateTime updated;
    @Column(name = "be_disabled", nullable = false)
    protected boolean disabled;
    @Id
    @GenericGenerator(name = "uuid-gen", strategy = "uuid2")
    @GeneratedValue(generator = "uuid-gen")
    private String id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "account_email", unique = true, nullable = false)
    private String email;
    @Column(name = "account_phone")
    private String phone;
    @Column(name = "password")
    private String password;
    @Column(name = "account_phone_verified", nullable = false)
    private boolean phoneVerified;
    @Column(name = "account_email_verified", nullable = false)
    private boolean emailVerified;

}
