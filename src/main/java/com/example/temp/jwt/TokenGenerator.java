package com.example.temp.jwt;

import com.example.temp.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@RequiredArgsConstructor
@Component
public class TokenGenerator {
    private static final long validityInMilliseconds = 1000L * 60;
    private static final String MEMBER_ID_CLAIM_KEY = "Id";

    private final byte[] secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256).getEncoded();

    private String generateToken(String id, String email, String name) {
        return Jwts.builder()
                .setSubject(id)
                .claim("name",name)
                .claim("email", email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .signWith(Keys.hmacShaKeyFor(secretKey))
                .compact();
    }

    public String getToken(Member member) {
        return generateToken(String.valueOf(member.getId()), member.getEmail(),member.getName());
    }

    public Claims getClaims(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
