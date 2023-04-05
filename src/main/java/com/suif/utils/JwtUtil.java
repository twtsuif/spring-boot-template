package com.suif.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {

    // 48小时
//    @Value("${JWT.TTL}")
    private static final Long JWT_TTL = 48*60*60*1000L;

    // 设置秘钥明文
//    @Value("${JWT.KEY}")
    private static final String JWT_KEY="twtsuif";

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    // 密钥加密
    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getEncoder().encode(JwtUtil.JWT_KEY.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }

    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, getUUID());
        return builder.compact();
    }

    /**
     * 生成JWT
     *
     * @param subject 内容
     * @param uuid    唯一id
     * @return JwtBuilder
     */
    private static JwtBuilder getJwtBuilder(String subject, String uuid) {
        // 算法和密钥
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();

        // 设置过期时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + JwtUtil.JWT_TTL;
        Date expDate = new Date(expMillis);

        return Jwts.builder()
                .setId(uuid)           //唯一ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer("qnhd")     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) // 加密信息
                .setExpiration(expDate);
    }

    /**
     * 解析
     *
     * @param JWT token
     * @return JWT Body
     */
    public static Claims parseJWT(String JWT) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(JWT)
                .getBody();
    }
}