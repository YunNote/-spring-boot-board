package io.study.springbootboard.web.configuration.security;

import io.study.springbootboard.web.configuration.jwt.JwtAccessDeniedHandler;
import io.study.springbootboard.web.configuration.jwt.JwtAuthenticationEntryPoint;
import io.study.springbootboard.web.configuration.jwt.JwtProvider;
import io.study.springbootboard.web.configuration.jwt.JwtSecurityConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

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
              .antMatchers("/test").permitAll()
              .anyRequest().authenticated()
              .and()
              .formLogin().disable()
              .apply(new JwtSecurityConfig(jwtProvider));
      return http.build();
   }

   @Override
   public void configure(WebSecurity web){

      web.ignoring()
              .antMatchers(
                      "/favicon.ico"
              );
   }

   @Bean
   public PasswordEncoder bCryptPasswordEncoder() {
      return new BCryptPasswordEncoder();
   }
}
