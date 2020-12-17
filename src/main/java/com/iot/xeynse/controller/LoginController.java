package com.iot.xeynse.controller;


import com.iot.xeynse.model.AuthResponse;
import com.iot.xeynse.model.UserLoginRequest;
import com.iot.xeynse.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    @Autowired
    LoginService service;

    @RequestMapping(method = RequestMethod.POST, value = "/auth/login")
    public ResponseEntity<AuthResponse> login(@RequestBody UserLoginRequest request) {
        AuthResponse response = service.userLogin(request);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.POST, value = "/auth/register")
    public ResponseEntity<AuthResponse> register(@RequestBody UserLoginRequest request) {
        AuthResponse response = service.userRegister(request);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


}
