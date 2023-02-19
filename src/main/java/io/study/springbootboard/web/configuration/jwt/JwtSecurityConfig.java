package io.study.springbootboard.web.configuration.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

   private final JwtProvider jwtTokenProvider;

   @Override
   public void configure(HttpSecurity httpSecurity) throws Exception {

      JwtFilter jwtTokenFilter = new JwtFilter(jwtTokenProvider);
      httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
   }
}
