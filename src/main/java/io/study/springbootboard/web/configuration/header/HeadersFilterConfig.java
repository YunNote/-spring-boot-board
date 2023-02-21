package io.study.springbootboard.web.configuration.header;

import io.study.springbootboard.web.configuration.jwt.JwtFilter;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

public class HeadersFilterConfig  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
   @Override
   public void configure(HttpSecurity httpSecurity) throws Exception {
      HeadersFilter headersFilter = new HeadersFilter();
      httpSecurity.addFilterBefore(headersFilter, JwtFilter.class);
   }
}
