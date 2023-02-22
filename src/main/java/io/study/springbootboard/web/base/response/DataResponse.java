package io.study.springbootboard.web.base.response;

import lombok.Getter;

@Getter
public class DataResponse<T> extends BaseResponse{

   private T data;

   public DataResponse(int code, String description, T data) {
      super(code, description);
      this.data = data;
   }
}
