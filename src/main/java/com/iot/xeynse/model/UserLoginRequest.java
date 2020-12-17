package com.iot.xeynse.model;

import lombok.Data;

@Data
public class UserLoginRequest {

    String email;
    String phone;
    String password;
    boolean facebookLogin;
    boolean googleLogin;
}
