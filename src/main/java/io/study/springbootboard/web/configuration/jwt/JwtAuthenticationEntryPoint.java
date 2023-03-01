package io.study.springbootboard.web.configuration.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.study.springbootboard.web.base.response.BaseResponse;
import io.study.springbootboard.web.exception.ApiStatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static io.study.springbootboard.web.exception.ApiStatusCode.*;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

   private static final String ATTRIBUTE = "token_exception";
   private final ObjectMapper objectMapper = new ObjectMapper();

   @Override
   public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

      ApiStatusCode status = (ApiStatusCode) request.getAttribute(ATTRIBUTE);

      authException.printStackTrace();

      if (authException.getClass() == BadCredentialsException.class) {
         responseBuilder(response, USER_LOGIN_NOT_MATCHED);
         return;
      }

      if (Objects.isNull(status)) {
         responseBuilder(response, JWT_UNKNOWN_ERROR);
         return;
      }

      if (status == INVALID_JWT_SIGNATURE) {
         responseBuilder(response, INVALID_JWT_SIGNATURE);
         return;
      }

      if (status == EXPIRED_JWT_TOKEN) {
         responseBuilder(response, EXPIRED_JWT_TOKEN);
         return;
      }
      if (status == UNSUPPORTED_JWT_TOKEN) {
         responseBuilder(response, UNSUPPORTED_JWT_TOKEN);
         return;
      }
      if (status == INVALID_JWT_TOKEN) {
         responseBuilder(response, INVALID_JWT_TOKEN);
         return;
      }
      if (status == JWT_UNKNOWN_ERROR) {
         responseBuilder(response, JWT_UNKNOWN_ERROR);
         return;
      }
   }

   private void responseBuilder(HttpServletResponse response, ApiStatusCode statusCode) throws IOException {

      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getWriter().print(
              objectMapper.writeValueAsString(
                      new BaseResponse(
                              statusCode.getCode(),
                              statusCode.getDescription()
                      )
              )
      );
   }
}
