package io.study.springbootboard.web.exception.types.user;

import io.study.springbootboard.web.exception.ApiStatusCode;
import io.study.springbootboard.web.exception.types.BaseException;

public class UserPasswordValidationException extends BaseException {

   public UserPasswordValidationException() {
      super(ApiStatusCode.USER_PASSWORD_VALIDATION_FAILED);
   }
}

