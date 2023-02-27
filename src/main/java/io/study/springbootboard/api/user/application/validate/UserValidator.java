package io.study.springbootboard.api.user.application.validate;

import io.study.springbootboard.api.user.domain.entity.User;
import io.study.springbootboard.web.exception.types.UserNotMatchedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

   private final PasswordEncoder passwordEncoder;

   public void validate(User user) {
      // user 생성에 필요한 validator 체크 해당 부분에서 진행 .
   }

   public void loginValidate(User user, String plainPassword) {

      if(!passwordEncoder.matches(plainPassword, user.getPassword())) {
         throw new UserNotMatchedException();
      }
   }
}
