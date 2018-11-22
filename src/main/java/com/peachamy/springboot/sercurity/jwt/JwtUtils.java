package com.peachamy.springboot.sercurity.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {


    public static String createJWT(String subject, long seconds,String salt) {
        String compactJws = Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS512, salt)
                .setExpiration(new Date(System.currentTimeMillis()+seconds*1000))
                .compact();
        return compactJws;
    }


    public static String parseJWT(String jwt,String salt) {
        try {
            Claims claims = Jwts.parser()  //得到DefaultJwtParser
                    .setSigningKey(salt)                 //设置签名的秘钥
                    .parseClaimsJws(jwt)
                    .getBody();     //设置需要解析的jwt
            return claims.getSubject();
        } catch (Exception e) {
            return null;
        }
    }



    public static void main(String[] args) {


        try {

            String token = JwtUtils.createJWT("dzm",10,"test");
            System.out.println(token);

            String info = JwtUtils.parseJWT("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkem0iLCJleHAiOjE1NDI3ODExNjZ9.9v9nOta2yYzTO2qyobLYotNnwFALjMJ-CYxslQwLFFK4bnNhdoXpnLibU8jciF0OHDzO6n6e2DYrmIOY3Dq2yw","test");
            System.out.println(info);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
