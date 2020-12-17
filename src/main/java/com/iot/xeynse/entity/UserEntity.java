package com.iot.xeynse.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "xn_user_entity")
public class UserEntity {

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
    @Column(name = "user_first_name")
    private String firstName;
    @Column(name = "user_last_name")
    private String lastName;
    @Column(name = "user_birthday")
    private String birthday;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "access_level")
    private String accessLevel;
    @ManyToOne
    @JoinColumn(name = "account_detail_id")
    private AccountEntity account;


}
