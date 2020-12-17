package com.iot.xeynse.info;


import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class AppInfo {

    public static final String VERSION_HEADER = "Xeynse-App-Version";
    public static final String OS_HEADER = "Xeynse-App-OS";
    public static final String NAME_HEADER = "Xeynse-App-Name";
    public static final String BUILD_VERSION_HEADER = "Xeynse-App-Build-Version";

    private final String version;
    private final String os;
    private final String name;
    private final String buildVersion;

    public AppInfo(HttpServletRequest request) {
        this(request.getHeader(VERSION_HEADER), request.getHeader(OS_HEADER), request.getHeader(NAME_HEADER),
                request.getHeader(BUILD_VERSION_HEADER));
    }

    public AppInfo(String version, String os, String name, String buildVersion) {
        this.version = version;
        this.os = os;
        this.name = name;
        this.buildVersion = buildVersion;
    }

    private static boolean isValidVersion(String version) {
        return Pattern.compile("(\\d+)\\.(\\d+)\\.(\\d+)").matcher(version).matches();
    }

    /*public String getVersion() {
        Preconditions.checkArgument(!isNullOrEmpty(version), VERSION_HEADER + " header must be present");
        Preconditions.checkArgument(isValidVersion(version), VERSION_HEADER + " should match D.D.D pattern");
        return version;
    }

    public AppOS getOS() {
        Preconditions.checkArgument(!isNullOrEmpty(os), OS_HEADER + " header must be present");
        return AppOS.valueOf(os);
    }

    public AppName getName() {
        Preconditions.checkArgument(!isNullOrEmpty(name), NAME_HEADER + " header must be present");
        return AppName.valueOf(name);
    }
*/
    public String getBuildVersion() {
        return buildVersion;
    }

    @Override
    public String toString() {
        return "AppInfo{" + "version='" + version + '\'' + ", os='" + os + '\'' + ", name='" + name + '\'' + ", buildVersion='"
                + buildVersion + '\'' + '}';
    }
}
