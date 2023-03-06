package io.study.springbootboard.api.user.domain.validate;

import io.study.springbootboard.api.user.domain.entity.User;
import io.study.springbootboard.api.user.domain.repository.UserQueryRepository;
import io.study.springbootboard.web.exception.types.user.UserEmailIsExistException;
import io.study.springbootboard.web.exception.types.user.UserEmailValidationException;
import io.study.springbootboard.web.exception.types.user.UserNotMatchedException;
import io.study.springbootboard.web.exception.types.user.UserPasswordValidationException;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserValidator {

   private final UserQueryRepository userQueryRepository;
   private final PasswordEncoder passwordEncoder;
   private static final Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
   private static final Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9!@#$%^&*()-_+=]{8,16}$");

   /*
   * 1. 이메일 유효성 검사
   * 2. email 중복 검사
   * 3. 비밀번호 유효성 체크
   * */
   public void validate(User user) {

      final String email = user.getEmail();

      emailPatternValidation(email);
      emailDuplicatedValidation(email);
      passwordPatternValidation(user.getPassword());
   }

   public void loginValidate(User user, String plainPassword) {

      if (!passwordEncoder.matches(plainPassword, user.getPassword())) {
         throw new UserNotMatchedException();
      }
   }

   private void emailPatternValidation(String email) {

      if (!emailPattern.matcher(email).matches()) {
         throw new UserEmailValidationException();
      }
   }

   private void emailDuplicatedValidation(String email) {
      if(userQueryRepository.isExistEmail(email)){
         throw new UserEmailIsExistException();
      }
   }

   private void passwordPatternValidation(String password) {
      if(!passwordPattern.matcher(password).matches()) {
         throw new UserPasswordValidationException();
      }
   }
}
