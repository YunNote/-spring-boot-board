package io.study.springbootboard.api.user.domain.wrapper;

import io.study.springbootboard.api.user.application.request.UserSigninRequest;
import lombok.Getter;

@Getter
public class UserSigninWrapper {

   private final String email;
   private final String password;

   private UserSigninWrapper(String email, String password) {
      this.email = email;
      this.password = password;
   }

   public static UserSigninWrapper from(UserSigninRequest request) {

      return new UserSigninWrapper(request.getEmail(), request.getPassword());
   }
}
