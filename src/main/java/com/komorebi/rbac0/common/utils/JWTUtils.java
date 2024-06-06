package com.komorebi.rbac0.common.utils;

import io.jsonwebtoken.*;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

public class JWTUtils {
    // 过期时间，单位毫秒
    private static final int TIME_TO_LIVE_MS = 24 * 60 * 60 * 1000;
    // 密钥
    private static final byte[] SECRET_KEY_BYTES = "Jin".getBytes(); // 替换为你自己的密钥

    public static String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)
                .setIssuer("issuer")
                .setAudience("audience")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TIME_TO_LIVE_MS))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY_BYTES)
                .compact();
    }

    public static String validateToken(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException {
        try {
            Key key = new SecretKeySpec(SECRET_KEY_BYTES, SignatureAlgorithm.HS256.getJcaName());
            Claims claims = Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            throw e;
        }
    }
}
