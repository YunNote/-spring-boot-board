package io.study.springbootboard.api.user.domain;


import io.study.springbootboard.api.user.domain.entity.User;
import io.study.springbootboard.api.user.domain.wrapper.UserSigninWrapper;
import io.study.springbootboard.api.user.domain.wrapper.UserSignupWrapper;

public interface UserDataprovider {

   User loginBasicUser(UserSigninWrapper wrapper);
   String registed(UserSignupWrapper wrapper);

   void resetPassword(String email, String plainPassword);
}
