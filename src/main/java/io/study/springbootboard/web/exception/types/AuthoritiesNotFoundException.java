package io.study.springbootboard.web.exception.types;

import io.study.springbootboard.web.exception.ApiStatusCode;

public class AuthoritiesNotFoundException extends BaseException{
   public AuthoritiesNotFoundException() {
      super(ApiStatusCode.JWT_AUTHORITIES_NOT_FOUND);
   }
}
