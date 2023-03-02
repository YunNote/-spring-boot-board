package io.study.springbootboard.web.configuration.security;

import io.study.springbootboard.web.configuration.header.HeadersFilter;
import io.study.springbootboard.web.configuration.jwt.JwtAccessDeniedHandler;
import io.study.springbootboard.web.configuration.jwt.JwtAuthenticationEntryPoint;
import io.study.springbootboard.web.configuration.jwt.JwtProvider;
import io.study.springbootboard.web.configuration.jwt.JwtSecurityConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration{

   private final JwtProvider jwtProvider;
   private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
   private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

      log.info("[+] Spring Security Configuration! ");

      http
              .httpBasic().disable()
              .csrf().disable()
              .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and()
              .exceptionHandling()
              .authenticationEntryPoint(jwtAuthenticationEntryPoint)
              .accessDeniedHandler(jwtAccessDeniedHandler)
              .and()
              .authorizeHttpRequests()
              .antMatchers("/api/test").permitAll()
              .antMatchers("/api/users/**").permitAll()
              .anyRequest().authenticated()
              .and()
              .formLogin().disable()
              .addFilterBefore(new HeadersFilter(), UsernamePasswordAuthenticationFilter.class)
              .apply(new JwtSecurityConfig(jwtProvider));
      return http.build();
   }

   @Bean
   public PasswordEncoder bCryptPasswordEncoder() {
      return new BCryptPasswordEncoder();
   }
}
