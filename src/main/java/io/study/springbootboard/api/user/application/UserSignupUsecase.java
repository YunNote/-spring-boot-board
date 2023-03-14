package io.study.springbootboard.api.user.application;

import io.study.springbootboard.api.user.domain.event.SignupEvent;
import io.study.springbootboard.api.user.domain.wrapper.UserSignupWrapper;
import io.study.springbootboard.api.user.domain.UserDataprovider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserSignupUsecase {

   private final UserDataprovider userDataprovider;
   private final ApplicationEventPublisher applicationEventPublisher;

   @Transactional
   public void basicSignup(UserSignupWrapper wrapper) {

      String userEmail = userDataprovider.registed(wrapper);
      applicationEventPublisher.publishEvent(SignupEvent.of(userEmail));
      
   }
}
