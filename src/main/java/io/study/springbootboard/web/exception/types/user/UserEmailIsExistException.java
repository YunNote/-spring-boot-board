package io.study.springbootboard.web.exception.types.user;

import io.study.springbootboard.web.exception.ApiStatusCode;
import io.study.springbootboard.web.exception.types.BaseException;

public class UserEmailIsExistException extends BaseException {

   public UserEmailIsExistException() {
      super(ApiStatusCode.USER_EMAIL_IS_EXIST);
   }
}

