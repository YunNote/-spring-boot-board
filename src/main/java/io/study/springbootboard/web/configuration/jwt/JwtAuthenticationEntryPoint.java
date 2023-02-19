package io.study.springbootboard.web.configuration.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.study.springbootboard.web.base.BaseResponse;
import io.study.springbootboard.web.exception.ApiStatusCode;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static io.study.springbootboard.web.exception.ApiStatusCode.*;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

   private static final String ATTRIBUTE = "token_exception";
   private final ObjectMapper objectMapper = new ObjectMapper();

   @Override
   public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

      ApiStatusCode status = (ApiStatusCode) request.getAttribute(ATTRIBUTE);

      if (Objects.isNull(status)) {
         responseBuilder(response, JWT_UNKNOWN_ERROR);
      }

      if (status == INVALID_JWT_SIGNATURE) {
         responseBuilder(response, INVALID_JWT_SIGNATURE);
      }

      if (status == EXPIRED_JWT_TOKEN) {
         responseBuilder(response, EXPIRED_JWT_TOKEN);
      }
      if (status == UNSUPPORTED_JWT_TOKEN) {
         responseBuilder(response, UNSUPPORTED_JWT_TOKEN);
      }
      if (status == INVALID_JWT_TOKEN) {
         responseBuilder(response, INVALID_JWT_TOKEN);
      }
      if (status == JWT_UNKNOWN_ERROR) {
         responseBuilder(response, JWT_UNKNOWN_ERROR);
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
