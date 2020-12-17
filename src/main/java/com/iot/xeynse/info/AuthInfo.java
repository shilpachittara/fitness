package com.iot.xeynse.info;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

public class AuthInfo {

    public static final String TOKEN_USER_ID = "tokenUserId";
    public static final String TOKEN_PERMISSIONS = "tokenPermissions";

    private final String userId;
    private final List<String> permissions;

    @SuppressWarnings("unchecked")
    public AuthInfo(HttpServletRequest request) {
        this((String) request.getAttribute(TOKEN_USER_ID), (List<String>) request.getAttribute(TOKEN_PERMISSIONS));
    }

    public AuthInfo(String userId) {
        this(userId, Collections.emptyList());
    }

    public AuthInfo(String userId, List<String> permissions) {
        this.userId = userId;
        this.permissions = permissions;
    }

    public String getUserId() {
        return userId;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    @Override
    public String toString() {
        return "AuthInfo{" + "userId='" + userId + '\'' + ", permissions=" + permissions + '}';
    }
}
