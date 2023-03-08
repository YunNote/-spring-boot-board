package io.study.springbootboard.web.exception.types;

import io.study.springbootboard.web.exception.ApiStatusCode;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

   private int code;
   private String description;

   public BaseException(ApiStatusCode statusCode) {
      super(statusCode.getDescription());
      this.code = statusCode.getCode();
      this.description = statusCode.getDescription();
   }
}
