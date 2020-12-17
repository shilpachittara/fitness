package com.iot.xeynse.info;


import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class RequestInfo {

    private final AuthInfo authInfo;
    private final AppInfo appInfo;
    private final HttpInfo httpInfo;

    public RequestInfo(HttpServletRequest request) {
        this.authInfo = new AuthInfo(request);
        this.appInfo = new AppInfo(request);
        this.httpInfo = new HttpInfo(request);
    }

    public RequestInfo(AuthInfo authInfo, AppInfo appInfo, HttpInfo httpInfo) {
        this.authInfo = authInfo;
        this.appInfo = appInfo;
        this.httpInfo = httpInfo;
    }

    //auth
    public String getUserId() {
        return authInfo.getUserId();
    }

    public List<String> getPermissions() {
        return authInfo.getPermissions();
    }

    //app
    /*public String getVersion() {
        return appInfo.getVersion();
    }

    public AppOS getOS() {
        return appInfo.getOS();
    }

    public AppName getName() {
        return appInfo.getName();
    }*/

    public String getBuildVersion() {
        return appInfo.getBuildVersion();
    }

    //http
    public String getUserAgent() {
        return httpInfo.getUserAgent();
    }

    public String getUserIP() {
        return httpInfo.getUserIP();
    }

    @Override
    public String toString() {
        return "RequestInfo{" + "authInfo=" + authInfo + ", appInfo=" + appInfo + ", httpInfo=" + httpInfo + '}';
    }

    public AuthInfo getAuthInfo() {
        return authInfo;
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public HttpInfo getHttpInfo() {
        return httpInfo;
    }

    public static class Builder {
        public static com.iot.xeynse.info.RequestInfo buildAuthInfo(String userId) {
            return new com.iot.xeynse.info.RequestInfo(new AuthInfo(userId), null, null);
        }

       /* public static com.kirana.retailer.info.RequestInfo buildAuthInfo(String userId, List<String> permissions) {
            return new com.kirana.retailer.info.RequestInfo(new AuthInfo(userId, permissions), null, null);
        }*/

        /*public static com.incasafit.codefit.common.info.RequestInfo buildHttpInfo(String userAgent, String userIP) {
            return new com.incasafit.codefit.common.info.RequestInfo(null, null, new HttpInfo(userAgent, userIP));
        }

        public static com.incasafit.codefit.common.info.RequestInfo buildAppInfo(String version, String os, String name, String buildVersion) {
            return new com.incasafit.codefit.common.info.RequestInfo(null, new AppInfo(version, os, name, buildVersion), null);
        }

        public static com.incasafit.codefit.common.info.RequestInfo buildRequestInfo(AuthInfo authInfo, AppInfo appInfo) {
            return new com.incasafit.codefit.common.info.RequestInfo(authInfo, appInfo, null);
        }

        public static com.incasafit.codefit.common.info.RequestInfo buildRequestInfo(AuthInfo authInfo, HttpInfo httpInfo) {
            return new com.incasafit.codefit.common.info.RequestInfo(authInfo, null, httpInfo);
        }

        public static com.incasafit.codefit.common.info.RequestInfo buildRequestInfo(String userId, AppInfo appInfo, HttpInfo httpInfo) {
            return new com.incasafit.codefit.common.info.RequestInfo(new AuthInfo(userId), appInfo, httpInfo);
        }*/
    }
}
