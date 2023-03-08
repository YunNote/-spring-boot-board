package io.study.springbootboard.api.user.domain.wrapper;

import io.study.springbootboard.api.user.application.request.UserSignupRequest;
import io.study.springbootboard.api.user.domain.validate.UserValidator;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserSignupWrapper {
   private String email;
   private String password;

   public UserSignupWrapper(String email, String password) {
      this.email = email;
      this.password = password;
   }
   public static UserSignupWrapper from(UserSignupRequest request) {

      return new UserSignupWrapper(request.getEmail(), request.getPassword());
   }

   public void validator(UserValidator userValidator) {
      userValidator.validate(this);
   }
}
