package io.study.springbootboard.web.base.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BaseResponse {

   private int code;
   private String description;

   public BaseResponse(int code, String description) {
      this.code = code;
      this.description = description;
   }
}
