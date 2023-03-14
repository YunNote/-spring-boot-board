package io.study.springbootboard.api.user.application;

import io.study.springbootboard.api.user.application.request.UserResetPasswordRequest;
import io.study.springbootboard.api.user.domain.UserDataprovider;
import io.study.springbootboard.api.user.domain.event.ResetPasswordEvent;
import java.util.Random;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResetPasswordUsecase {

   private final UserDataprovider userDataprovider;
   private final ApplicationEventPublisher applicationEventPublisher;

   private static final String VALID_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()-_+=";
   private static final int MIN_LENGTH = 8;
   private static final int MAX_LENGTH = 16;

   @Transactional
   public void reset(UserResetPasswordRequest request) {

      String plainPassword = generatePassword();
      userDataprovider.resetPassword(request.getEmail(), plainPassword);

      applicationEventPublisher.publishEvent(ResetPasswordEvent.of(request.getEmail(), plainPassword));
   }

   private String generatePassword() {

      Random random = new Random();
      final int passwordLength = MIN_LENGTH +  random.nextInt(MAX_LENGTH - MIN_LENGTH + 1);

      return random.ints(passwordLength, 0 , VALID_CHARACTERS.length())
         .mapToObj(VALID_CHARACTERS::charAt)
         .map(Object::toString)
         .collect(Collectors.joining());
   }
}
