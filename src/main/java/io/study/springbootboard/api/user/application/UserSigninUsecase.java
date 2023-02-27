package io.study.springbootboard.api.user.application;

import io.study.springbootboard.api.user.application.wrapper.UserSigninWrapper;
import io.study.springbootboard.api.user.domain.UserDataprovider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSigninUsecase {

   private final UserDataprovider userDataprovider;

   public void login(UserSigninWrapper from) {

   }
}
