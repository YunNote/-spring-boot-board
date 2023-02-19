package io.study.springbootboard.web.exception.types;

import io.study.springbootboard.web.exception.ApiStatusCode;

public class AuthoritiesNotFound extends BaseException{
   public AuthoritiesNotFound() {
      super(ApiStatusCode.JWT_AUTHORITIES_NOT_FOUND);
   }
}
