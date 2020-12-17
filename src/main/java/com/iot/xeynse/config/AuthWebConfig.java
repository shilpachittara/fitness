package com.iot.xeynse.config;

import com.iot.xeynse.filter.AuthenticationFilter;
import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthWebConfig {

    @Value("${auth.token.secret}")
    private String jwtSecret;

    @Value("${http.port}")
    private int httpPort;

    @Bean
    public FilterRegistrationBean authenticationFilter() {
        AuthenticationFilter filter = new AuthenticationFilter();
        filter.setJwtSecret(jwtSecret);
        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(filter);
        filterRegistrationBean.addUrlPatterns("/api");
        return filterRegistrationBean;
    }

    @Bean
    public ServletWebServerFactory servletContainer() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
        tomcat.addAdditionalTomcatConnectors(createStandardConnector());
        return tomcat;
    }


    private Connector createStandardConnector() {
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setPort(httpPort);
        return connector;
    }


}
