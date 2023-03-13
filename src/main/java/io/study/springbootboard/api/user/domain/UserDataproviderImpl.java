package io.study.springbootboard.api.user.domain;

import io.study.springbootboard.api.user.application.mapper.UserMapper;
import io.study.springbootboard.api.user.domain.entity.User;
import io.study.springbootboard.api.user.domain.repository.UserQueryRepository;
import io.study.springbootboard.api.user.domain.repository.UserRepository;
import io.study.springbootboard.api.user.domain.validate.UserValidator;
import io.study.springbootboard.api.user.domain.wrapper.UserSigninWrapper;
import io.study.springbootboard.api.user.domain.wrapper.UserSignupWrapper;
import io.study.springbootboard.web.event.mail.SignupEvent;
import io.study.springbootboard.web.exception.types.user.UserNotMatchedException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDataproviderImpl implements UserDataprovider {

   private final UserRepository userRepository;
   private final UserQueryRepository userQueryRepository;
   private final UserMapper userMapper;
   private final UserValidator userValidator;

   private final ApplicationEventPublisher applicationEventPublisher;


   @Override
   public User loginBasicUser(UserSigninWrapper wrapper) {

      User user = availableUser(userQueryRepository.findUsername(wrapper.getEmail()));
      user.loginValidate(userValidator, wrapper.getPassword());

      return user;
   }

   @Override
   public void registed(UserSignupWrapper wrapper) {

      wrapper.validator(userValidator);
      User user = userMapper.mapFrom(wrapper);
      userRepository.save(user);
      applicationEventPublisher.publishEvent(SignupEvent.of(user.getEmail()));
   }

   @Override
   public void resetPassword(String email) {
      User user = availableUser(userQueryRepository.findUsername(email));
      userMapper.resetPassword(user);

      applicationEventPublisher.publishEvent();
   }

   private User availableUser(Optional<User> user) {

      return user.orElseThrow(() -> new UserNotMatchedException());
   }
}
