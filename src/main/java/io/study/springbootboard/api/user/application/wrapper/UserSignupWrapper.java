package io.study.springbootboard.api.user.application.wrapper;

import io.study.springbootboard.api.user.ui.request.UserSignupRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignupWrapper {
   private String email;
   private String password;

   private UserSignupWrapper(String email, String password) {
      this.email = email;
      this.password = password;
   }
   public static UserSignupWrapper from(UserSignupRequest request) {

      return new UserSignupWrapper(request.getEmail(), request.getPassword());
   }
}
