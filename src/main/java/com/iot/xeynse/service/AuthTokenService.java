package com.iot.xeynse.service;

import com.iot.xeynse.entity.AccountEntity;
import com.iot.xeynse.entity.UserEntity;
import com.iot.xeynse.info.RequestInfo;
import com.iot.xeynse.model.AuthResponse;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class AuthTokenService {

    private static final Logger log = LoggerFactory.getLogger(AuthTokenService.class);

    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;



    private final String jwtSecret;
    private final long jwtExpiration;

    @Autowired
    public AuthTokenService(
                            @Value("${auth.token.secret}") String jwtSecret,
                            @Value("${auth.token.expiration}") long jwtExpiration) {
        this.jwtSecret = jwtSecret;
        this.jwtExpiration = jwtExpiration;
    }

    @Transactional(readOnly = true)
    public AuthResponse login(AccountEntity accountEntity) {
        log.debug("login web using provider, userId {}", accountEntity.getId());
        return getAuthResponse(accountEntity);
    }

    private AuthResponse getAuthResponse(AccountEntity user) {
        return new AuthResponse(user.getId(), user.getUserName(), getJwtAccessToken(auth(user)));
    }

    private String getJwtAccessToken(RequestInfo requestInfo) {
        long nowMillis = System.currentTimeMillis();
        Key signingKey = new SecretKeySpec(jwtSecret.getBytes(), signatureAlgorithm.getJcaName());

        Map<String, Object> customClaims = new HashMap<>();
        //customClaims.put("usa", userAgent.hashCode());
        //customClaims.put("prm", requestInfo.getPermissions());

        JwtBuilder builder = Jwts.builder().setClaims(customClaims).setId(UUID.randomUUID().toString()).setIssuer(String.valueOf(requestInfo.getUserId()))
                .setIssuedAt(new Date(nowMillis)).setExpiration(new Date(nowMillis + jwtExpiration))
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    private RequestInfo auth(AccountEntity user) {
        return RequestInfo.Builder.buildAuthInfo(user.getId());
    }



}