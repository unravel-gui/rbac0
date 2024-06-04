package com.komorebi.rbac0.common.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import javax.xml.bind.DatatypeConverter;

public class JWTUtils {
    // 过期时间，单位毫秒
    private static final int TIME_TO_LIVE_MS = 24*60 * 60 * 1000;
    // 密钥
    private static final String SECRET_KEY = "your_secret_key";

    public static String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuer("issuer")
                .setAudience("audience")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TIME_TO_LIVE_MS))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    public static void validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY)
                    .parseClaimsJws(token);
        } catch (Exception e) {
            // Token 无效或已过期
            System.out.println("Token validation failed: " + e.getMessage());
        }
    }
}