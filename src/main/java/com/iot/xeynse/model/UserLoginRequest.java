package com.iot.xeynse.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginRequest {

    String email;
    String phone;
    String password;
    boolean facebookLogin;
    boolean googleLogin;
}
