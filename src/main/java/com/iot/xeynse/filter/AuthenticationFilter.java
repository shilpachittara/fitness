package com.iot.xeynse.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.DefaultClaims;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Strings.isNullOrEmpty;
import static com.iot.xeynse.info.AuthInfo.TOKEN_PERMISSIONS;
import static com.iot.xeynse.info.AuthInfo.TOKEN_USER_ID;


public class AuthenticationFilter extends OncePerRequestFilter {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private String jwtSecret;
    private Collection<String> exclusions = Collections.emptyList();

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request.getMethod().equals(HttpMethod.OPTIONS.name())) {
            chain.doFilter(request, response);
            return;
        }

        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (isNullOrEmpty(authorization) || !authorization.startsWith("Bearer ")) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "no authorization found");
            return;
        }

        final String token = authorization.replace("Bearer ", "");
        DefaultClaims claims;
        try {
            claims = (DefaultClaims) Jwts.parser().setSigningKey(jwtSecret.getBytes()).parse(token).getBody();
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
            return;
        }

        request.setAttribute(TOKEN_PERMISSIONS, claims.get("prm", List.class));
        request.setAttribute(TOKEN_USER_ID, claims.getIssuer());

        try {
            MDC.put("app_user_id", claims.getIssuer());
            chain.doFilter(request, response);
        } finally {
            MDC.remove("app_user_id");
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return exclusions.stream()
                .anyMatch(p -> pathMatcher.match(p, request.getRequestURI()));
    }

    public void setJwtSecret(String jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public void setExclusions(Collection<String> exclusions) {
        this.exclusions = exclusions;
    }
}
