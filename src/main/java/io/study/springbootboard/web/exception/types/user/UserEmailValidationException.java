package io.study.springbootboard.web.exception.types.user;

import io.study.springbootboard.web.exception.ApiStatusCode;
import io.study.springbootboard.web.exception.types.BaseException;

public class UserEmailValidationException extends BaseException {

   public UserEmailValidationException() {
      super(ApiStatusCode.USER_EMAIL_VALIDATION_FAILED);
   }
}

