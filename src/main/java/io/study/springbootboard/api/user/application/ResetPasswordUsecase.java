package io.study.springbootboard.api.user.application;

import io.study.springbootboard.api.user.application.request.UserResetPasswordRequest;
import io.study.springbootboard.api.user.domain.UserDataprovider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResetPasswordUsecase {

   private final UserDataprovider userDataprovider;

   @Transactional
   public void reset(UserResetPasswordRequest request) {

      userDataprovider.resetPassword(request.getEmail());
   }
}
