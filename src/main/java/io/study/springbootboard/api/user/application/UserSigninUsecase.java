package io.study.springbootboard.api.user.application;

import io.study.springbootboard.api.user.domain.UserDataprovider;
import io.study.springbootboard.api.user.domain.wrapper.UserSigninWrapper;
import io.study.springbootboard.web.configuration.jwt.Jwt;
import io.study.springbootboard.web.configuration.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSigninUsecase {

   private final UserDataprovider userDataprovider;
   private final AuthenticationManagerBuilder authenticationManagerBuilder;
   private final JwtProvider jwtProvider;

   @Transactional
   public Jwt login(UserSigninWrapper wrapper) {

      UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(wrapper.getEmail(), wrapper.getPassword());
      Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
      Jwt jwt = jwtProvider.generateJwtToken(authentication);
      return jwt;
   }
}
