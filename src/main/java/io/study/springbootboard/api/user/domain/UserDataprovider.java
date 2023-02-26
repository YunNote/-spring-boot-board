package io.study.springbootboard.api.user.domain;


import io.study.springbootboard.api.user.application.wrapper.UserSignupWrapper;

public interface UserDataprovider {
   void registed(UserSignupWrapper wrapper);
}
