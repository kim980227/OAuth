package com.example.temp.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class TokenAuthenticationFilter{
    private final byte[] secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();

    private static final String BEARER_PREFIX = "Bearer ";
    private static final int SUBSTRING_BEARER_INDEX = 7;
    private static final String AUTHORIZATION_HEADER = "authorization";

    public String resolveToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(AUTHORIZATION_HEADER);

        if (validateToken(jwtToken)) {
            return parseBearerToken(jwtToken);
        }
        return null;
    }

    private boolean validateToken(String jwtToken) {
        if (jwtToken == null || !jwtToken.startsWith(BEARER_PREFIX)) {
            return false;
        }

        Jws<Claims> claims =
                Jwts.parserBuilder()
                        .setSigningKey(secretKey)
                        .build()
                        .parseClaimsJws(parseBearerToken(jwtToken));

        return !claims.getBody().getExpiration().before(new Date());
    }

    private String parseBearerToken(String jwtToken) {
        return jwtToken.substring(SUBSTRING_BEARER_INDEX);
    }
}
