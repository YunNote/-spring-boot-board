package io.study.springbootboard.web.exception.types;

import io.study.springbootboard.web.exception.ApiStatusCode;

public class UserNotMatchedException extends BaseException{

   public UserNotMatchedException() {
      super(ApiStatusCode.USER_LOGIN_NOT_MATCHED);
   }
}
