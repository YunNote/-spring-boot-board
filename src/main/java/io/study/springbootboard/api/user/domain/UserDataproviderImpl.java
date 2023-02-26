package io.study.springbootboard.api.user.domain;

import io.study.springbootboard.api.user.application.mapper.UserMapper;
import io.study.springbootboard.api.user.application.validate.UserValidator;
import io.study.springbootboard.api.user.application.wrapper.UserSignupWrapper;
import io.study.springbootboard.api.user.domain.entity.User;
import io.study.springbootboard.api.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDataproviderImpl implements  UserDataprovider{

   private final UserRepository userRepository;
   private final UserMapper userMapper;
   private final UserValidator userValidator;

   @Override
   public void registed(UserSignupWrapper wrapper) {

      User user = userMapper.mapFrom(wrapper);
      user.validator(userValidator);

      userRepository.save(user);
   }
}
