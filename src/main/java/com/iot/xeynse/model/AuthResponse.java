package com.iot.xeynse.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.Map;

public class AuthResponse {

    private static final String USER_ID = "userId";
    private static final String USER_NAME = "userName";
    private static final String ACCESS_TOKEN = "accessToken";
    //private static final Boolean EMAILVERIFIED = false;

    @ApiModelProperty(value = "userId", example = "userId")
    private String userId;
    @ApiModelProperty(value = "fisrtName", example = "John")
    private String userName;
    @ApiModelProperty(value = "JWT token valid 3 hours. Used for api requests" , example = "accessToken")
    private String accessToken;

    @ApiModelProperty(value = "Token valid until logout. Used to get new accessToken", example = "refreshToken" )
    private String refreshToken;

    @JsonCreator
    public AuthResponse(@JsonProperty("userId") String userId, @JsonProperty("firstName") String userName, @JsonProperty("accessToken") String accessToken) {
        this.userId = userId;
        this.userName = userName;
        this.accessToken = accessToken;
    }

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put(USER_ID, this.userId);
        map.put(USER_NAME, this.userName);
        map.put(ACCESS_TOKEN, this.accessToken);
        return map;
    }

    public AuthResponse withRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

}
