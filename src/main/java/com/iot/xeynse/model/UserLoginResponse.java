package com.iot.xeynse.model;

import lombok.Data;

import java.util.List;

@Data
public class UserLoginResponse {


    String userId;
    String token;
    String email;
    String phone;
    //List<UserDetail> users;
}
