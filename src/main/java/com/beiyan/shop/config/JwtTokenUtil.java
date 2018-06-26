package com.beiyan.shop.config;


import com.beiyan.shop.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.Date;
import java.util.function.Function;

import static com.beiyan.shop.config.Constants.*;

@Component
public class JwtTokenUtil implements Serializable {


    /**
     * 获取用户名
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * 获取过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 解析Token
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 判断Token 是否过期
     */
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(String username) {
        String token = doGenerateToken(username);
        TokenCache.put(token, username);
        return token;
    }

    /**
     * 刷新Token
     */
    private void refreshToken(String token) {
        long distance = getClaimFromToken(token, Claims::getExpiration).getTime() - System.currentTimeMillis();
        if (distance > 0 && distance <= REFRESH_TOKEN_TIME * 1000) {
            String username = getUsernameFromToken(token);
            String newToken = doGenerateToken(username);
            TokenCache.put(newToken, username);
            HttpServletResponse response = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
            response.setHeader(HEADER_STRING, newToken);

        }
    }


    private String doGenerateToken(String subject) {

        //用户名
        Claims claims = Jwts.claims().setSubject(subject);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("BeiYan")//签发人
                .setIssuedAt(new Date(System.currentTimeMillis()))//签发时间
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))//过期时间
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)//加密方式
                .compact();
    }

    /**
     * 验证Token
     */
    public Boolean validateToken(String token, String username) {
        final String tokenUsername = getUsernameFromToken(token);
        boolean result = TokenCache.tokenExist(token) && tokenUsername.equals(username)
                && !isTokenExpired(token);
        //refresh  token
        if (result) {
            refreshToken(token);
        }
        return result;
    }

}
