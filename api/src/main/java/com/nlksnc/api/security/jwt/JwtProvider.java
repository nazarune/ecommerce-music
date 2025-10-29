package com.nlksnc.api.security.jwt;

import com.nlksnc.api.security.userPrinciple.UserPrinciple;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {
    private static final Logger log = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Value("${jwt.expiration}")
    private int jwtExpiration;
    @Value("${jwt.refresh.expiration}")
    private int jwtRefreshExpiration;

    public String createToken(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

        List<String> authorities = userPrinciple.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return Jwts.builder()
                .header()
                .and()
                .subject(userPrinciple.email())
                .claim("authorities", authorities)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtExpiration * 1000L))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
                .compact();
    }

    public String createRefreshToken(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
                .header()
                .and()
                .subject(userPrinciple.email())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + jwtRefreshExpiration * 1000L))
                .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
                .compact();
    }

    public String reduceTokenExpiration(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            claims.getExpiration().setTime(0);

            return Jwts.builder()
                    .claims(claims)
                    .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
                    .compact();
        } catch (JwtException e) {
            log.error("Something went wrong. Message: ", e);
        }
        return "";
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature. Message: ", e);
        } catch (MalformedJwtException e) {
            log.error("Invalid format token. Message: ", e);
        } catch (ExpiredJwtException e) {
            log.error("Expired JWT token. Message: ", e);
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token. Message: ", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty. Message: ", e);
        }
        return false;
    }

    public String getUserFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }
}
