package io.study.springbootboard.web.configuration.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import io.study.springbootboard.web.exception.ApiStatusCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
   private static final String ATTRIBUTE = "token_exception";
   private static final String BEARER_PREFIX = "Bearer ";
   private final JwtProvider jwtTokenProvider;

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

      log.info("[+] JwtFilter 실행! ");
      String jwtToken = resolveToken(request);

      try {
         if (StringUtils.hasText(jwtToken) && jwtTokenProvider.validationToken(jwtToken)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(jwtToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
         }
      } catch (SecurityException | MalformedJwtException e) {
         log.info("잘못된 JWT 서명입니다.");
         request.setAttribute(ATTRIBUTE, ApiStatusCode.INVALID_JWT_SIGNATURE);
      } catch (ExpiredJwtException e) {
         request.setAttribute(ATTRIBUTE, ApiStatusCode.EXPIRED_JWT_TOKEN);
         log.info("만료된 JWT 토큰입니다.");
      } catch (UnsupportedJwtException e) {
         log.info("지원되지 않는 JWT 토큰입니다.");
         request.setAttribute(ATTRIBUTE, ApiStatusCode.UNSUPPORTED_JWT_TOKEN);
      } catch (IllegalArgumentException e) {
         log.error("JWT 토큰이 잘못되었습니다.");
         request.setAttribute(ATTRIBUTE, ApiStatusCode.INVALID_JWT_TOKEN);
      } catch (Exception e) {
         log.error("================================================");
         log.error("JwtFilter - doFilterInternal() 오류발생");
         log.error("token : {}", jwtToken);
         log.error("Exception Message : {}", e.getMessage());
         log.error("Exception StackTrace : {");
         e.printStackTrace();
         log.error("}");
         log.error("================================================");
         request.setAttribute(ATTRIBUTE, ApiStatusCode.JWT_UNKNOWN_ERROR);
      }

      filterChain.doFilter(request, response);
   }

   private String resolveToken(HttpServletRequest request) {
      String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);

      if (isExistBearer(bearerToken)) {
         return bearerToken.substring(7);
      }

      return null;
   }

   private boolean isExistBearer(String bearerToken) {
      return StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX);
   }
}
