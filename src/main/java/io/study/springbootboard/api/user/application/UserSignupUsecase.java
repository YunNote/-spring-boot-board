package io.study.springbootboard.api.user.application;

import io.study.springbootboard.api.user.domain.wrapper.UserSignupWrapper;
import io.study.springbootboard.api.user.domain.UserDataprovider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSignupUsecase {

   private final UserDataprovider userDataprovider;

   public void basicSignup(UserSignupWrapper wrapper) {

      userDataprovider.registed(wrapper);
   }
}
