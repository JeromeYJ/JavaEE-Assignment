package edu.cn.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTTokenUtil {

    // token的密钥
    private String secret = "db9d654c4860d4d37406d1ffcd92be5756a9ea94c03464aba0e475dc88932fee2278a23f6e6953a24d089ad09d99ac9d07ebd223a7f264fa2a7bea8a779c7946";

    // 生成Token
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> infoMap = new HashMap<>();
        String token = Jwts.builder()
                       .setClaims(infoMap)
                       .setSubject(userDetails.getUsername())
                       .setIssuedAt(new Date(System.currentTimeMillis()))
                       .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                       .signWith(SignatureAlgorithm.HS512, secret)
                       .compact();
        return token;
    }

    // 解析token
    public Claims getClaimFromToken(String token) {
        return  Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token).getBody();
    }

    public boolean validateClaim(Claims claim,UserDetails userDetails) {
        //可以验证其他信息，如角色
        return userDetails != null &&
                claim.getSubject().equals(userDetails.getUsername())
                && !claim.getExpiration().before(new Date());
    }

}
