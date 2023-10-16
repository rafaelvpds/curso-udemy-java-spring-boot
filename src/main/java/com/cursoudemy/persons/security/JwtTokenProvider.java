package com.cursoudemy.persons.security;

import java.util.Date;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.auth0.jwt.algorithms.Algorithm;
import com.cursoudemy.persons.dto.dtoV01.security.TokenDTO;

import jakarta.annotation.PostConstruct;

@Service
public class JwtTokenProvider {
    @Value("${security.jwt.token.secret-key:secret}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-lenght:3600000}")
    private long validityInMilliseconds = 3600000;

    @Autowired
    private UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenDTO createAccessToken(String userName, List<String> roles) {
        Date now = new Date();
        Date validityDate = new Date(now.getTime() + validityInMilliseconds);
        var accesToken = getAccesToken(userName, roles, now, validityDate);
        var refreshToken = getAccesToken(userName, roles, now);
        return new TokenDTO(userName, true, now, validityDate, accesToken, refreshToken);
    }

    private String getAccesToken(String userName, List<String> roles, Date now) {
        return null;
    }

    private String getAccesToken(String userName, List<String> roles, Date now, Date validityDate) {
        return null;
    }
}
