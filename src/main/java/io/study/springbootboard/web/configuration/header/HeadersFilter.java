package io.study.springbootboard.web.configuration.header;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.study.springbootboard.web.base.response.BaseResponse;
import io.study.springbootboard.web.exception.ApiStatusCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class HeadersFilter extends OncePerRequestFilter {

   private static final String X_API_VERSION = "x-api-version";
   private final ObjectMapper objectMapper = new ObjectMapper();

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      log.info("[+] --------------------");
      log.info("[+] | Header :: X-API-VERSION :: " + request.getHeader(X_API_VERSION));
      log.info("[+] --------------------");

      if(Objects.isNull(request.getHeader(X_API_VERSION))) {
         responseBuilder(response, ApiStatusCode.X_API_VERSION_NOT_FOUND);
         return;
      }

      filterChain.doFilter(request, response);
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
