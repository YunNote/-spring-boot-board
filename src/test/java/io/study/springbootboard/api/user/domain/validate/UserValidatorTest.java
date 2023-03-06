package io.study.springbootboard.api.user.domain.validate;

import io.study.springbootboard.api.user.domain.entity.User;
import io.study.springbootboard.web.exception.types.user.UserEmailValidationException;
import io.study.springbootboard.web.exception.types.user.UserPasswordValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


public class UserValidatorTest {

   private UserValidator userValidator = new UserValidator(null, null);

   @Test
   @DisplayName(value = "[User] 이메일 검증 성공 테스트")
   public void userEmailValidationSuccess() {
      User user = User.generatedBasicUser("zzdd1558@gmail.com", "1234");

      assertThatThrownBy(() -> userValidator.validate(user))
              .isInstanceOf(UserPasswordValidationException.class);
   }

   @Test
   @DisplayName(value = "[User] 이메일 검증 Exception ")
   public void userEmailValidationFail(){
      User user = User.generatedBasicUser("zzdd1@558@gmail.com", "1234");
      assertThatThrownBy(() -> userValidator.validate(user))
              .isInstanceOf(UserEmailValidationException.class);
   }
}