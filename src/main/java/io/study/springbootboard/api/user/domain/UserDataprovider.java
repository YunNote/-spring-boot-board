package io.study.springbootboard.api.user.domain;


import io.study.springbootboard.api.user.application.wrapper.UserSigninWrapper;
import io.study.springbootboard.api.user.application.wrapper.UserSignupWrapper;

public interface UserDataprovider {

   void loginBasicUser(UserSigninWrapper wrapper);
   void registed(UserSignupWrapper wrapper);
}
