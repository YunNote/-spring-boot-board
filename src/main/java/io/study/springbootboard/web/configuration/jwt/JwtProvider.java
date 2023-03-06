package io.study.springbootboard.web.configuration.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.study.springbootboard.web.exception.types.user.AuthoritiesNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtProvider {
   private static final String AUTHORITIES_KEY = "auth_authorities";
   private static final String BEARER_TYPE = "Bearer";
   private static final String DELIMITER = ",";
   private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
   private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일
   private final Key key;

   public JwtProvider(@Value("${jwt.secret}") String secretKey) {
      byte[] keyBytes = Decoders.BASE64.decode(secretKey);
      this.key = Keys.hmacShaKeyFor(keyBytes);
   }

   public Jwt generateJwtToken(Authentication authentication) {

      String authorities = getAuthorities(authentication.getAuthorities());
      long now = (new Date()).getTime();

      Date accessTokenExpiresIn = generateTokenExpireTime(now, ACCESS_TOKEN_EXPIRE_TIME);
      String accessToken = generateAccessToken(authentication, authorities, accessTokenExpiresIn);

      Date refreshTokenExpiresIn = generateTokenExpireTime(now, REFRESH_TOKEN_EXPIRE_TIME);
      String refreshToken = generateRefreshToken(refreshTokenExpiresIn);

      return Jwt.builder()
              .grantType(BEARER_TYPE)
              .accessToken(accessToken)
              .accessTokenExpiresIn(accessTokenExpiresIn.getTime())
              .refreshToken(refreshToken)
              .build();
   }

   public Authentication getAuthentication(String accessToken) {

      Claims claims = parseClaims(accessToken);

      if (Objects.isNull(claims.get(AUTHORITIES_KEY))) {
         throw new AuthoritiesNotFoundException();
      }

      List<SimpleGrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString()
                      .split(DELIMITER))
              .map(SimpleGrantedAuthority::new)
              .collect(Collectors.toList());

      UserDetails principal = new User(claims.getSubject(), "", authorities);

      return new UsernamePasswordAuthenticationToken(principal, "", authorities);
   }

   public boolean validationToken(String jwtToken) {
      Jwts.parserBuilder()
              .setSigningKey(key)
              .build()
              .parseClaimsJws(jwtToken);

      return true;
   }

   private String generateAccessToken(Authentication authentication, String authorities, Date accessTokenExpiresIn) {
      return Jwts.builder()
              .setSubject(authentication.getName())
              .claim(AUTHORITIES_KEY, authorities)
              .setExpiration(accessTokenExpiresIn)
              .signWith(key, SignatureAlgorithm.HS512)
              .compact();
   }

   private String generateRefreshToken(Date refreshTokenExpiresIn) {
      return Jwts.builder()
              .setExpiration(refreshTokenExpiresIn)
              .signWith(key, SignatureAlgorithm.HS512)
              .compact();
   }


   // 인증된 권한 가져오기.
   private String getAuthorities(Collection<? extends GrantedAuthority> grantedAuthorities) {

      return grantedAuthorities.stream()
              .map(GrantedAuthority::getAuthority)
              .collect(Collectors.joining(DELIMITER));
   }

   // Token Expire 시간 설정.
   private Date generateTokenExpireTime(long now, long expireTime) {

      return new Date(now + expireTime);
   }

   private Claims parseClaims(String accessToken) {

      try {
         return Jwts.parserBuilder()
                 .setSigningKey(key)
                 .build()
                 .parseClaimsJws(accessToken)
                 .getBody();
      } catch (ExpiredJwtException e) {
         return e.getClaims();
      }
   }
}
