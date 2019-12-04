package com.huynguyen.videosharing.provider.impl;

import com.huynguyen.videosharing.domain.model.User;
import com.huynguyen.videosharing.provider.TokenProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JwtTokenProvider implements TokenProvider {

  private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
  private static final String SECRET = "SecretKeyToGenJWTs";

  @Override
  public String generate(Authentication authentication) {
    User user = (User) authentication.getPrincipal();

    Map<String, Object> claims = new HashMap<>();
    claims.put("id", user.getId());
    claims.put("username", user.getUsername());
    return Jwts.builder()
        .setSubject(user.getId().toString())
        .setClaims(claims)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
  }

  @Override
  public boolean verify(String jwtToken) {
    try {
      Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwtToken);
      return true;
    } catch (Exception ex) {
      log.error("Invalid JWT", ex);
    }

    return false;
  }

  @Override
  public Map<String, Object> parse(String jwtToken) {
    return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwtToken).getBody();
  }
}
