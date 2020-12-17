package com.iot.xeynse.info;

import com.google.common.base.Preconditions;

import javax.servlet.http.HttpServletRequest;

import static com.google.common.base.Strings.isNullOrEmpty;

public class HttpInfo {

    private final String userAgent;
    private final String userIP;

    public HttpInfo(HttpServletRequest request) {
        this(request.getHeader("User-Agent"), getUserIP(request));
    }

    public HttpInfo(String userAgent, String userIP) {
        this.userAgent = userAgent;
        this.userIP = userIP;
    }

    private static String getUserIP(HttpServletRequest request) {
        String headerIP = request.getHeader("X-Forwarded-For");
        if (isNullOrEmpty(headerIP)) {
            return request.getRemoteAddr();
        }
        if (headerIP.contains(",")) {
            headerIP = headerIP.split(",")[0].trim();
        }
        return headerIP;
    }

    public String getUserAgent() {
        Preconditions.checkArgument(!isNullOrEmpty(userAgent), "userAgent can't be null or empty");
        return userAgent;
    }

    public String getUserIP() {
        Preconditions.checkArgument(!isNullOrEmpty(userIP), "userIP can't be null or empty");
        return userIP;
    }

    @Override
    public String toString() {
        return "HttpInfo{" + "userAgent='" + userAgent + '\'' + ", userIP='" + userIP + '\'' + '}';
    }
}
