package io.study.springbootboard.web.configuration.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.study.springbootboard.web.base.response.BaseResponse;
import io.study.springbootboard.web.exception.ApiStatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static io.study.springbootboard.web.exception.ApiStatusCode.USER_UNAUTHORIZED;

@RequiredArgsConstructor
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

   private final ObjectMapper objectMapper = new ObjectMapper();

   @Override
   public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

      responseBuilder(response, USER_UNAUTHORIZED);
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
