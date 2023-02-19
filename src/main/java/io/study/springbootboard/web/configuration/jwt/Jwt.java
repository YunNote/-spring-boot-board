package io.study.springbootboard.web.configuration.jwt;

import lombok.Builder;

public class Jwt {

   private String grantType;
   private String accessToken;
   private Long accessTokenExpiresIn;
   private String refreshToken;

   @Builder
   private Jwt(String grantType, String accessToken, Long accessTokenExpiresIn, String refreshToken) {
      this.grantType = grantType;
      this.accessToken = accessToken;
      this.accessTokenExpiresIn = accessTokenExpiresIn;
      this.refreshToken = refreshToken;
   }
}
