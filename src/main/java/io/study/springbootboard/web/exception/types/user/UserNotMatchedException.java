package io.study.springbootboard.web.exception.types.user;

import io.study.springbootboard.web.exception.ApiStatusCode;
import io.study.springbootboard.web.exception.types.BaseException;

public class UserNotMatchedException extends BaseException {

   public UserNotMatchedException() {
      super(ApiStatusCode.USER_LOGIN_NOT_MATCHED);
   }
}
