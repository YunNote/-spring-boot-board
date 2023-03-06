package io.study.springbootboard.web.exception.types.user;

import io.study.springbootboard.web.exception.ApiStatusCode;
import io.study.springbootboard.web.exception.types.BaseException;

public class AuthoritiesNotFoundException extends BaseException {
   public AuthoritiesNotFoundException() {
      super(ApiStatusCode.JWT_AUTHORITIES_NOT_FOUND);
   }
}
