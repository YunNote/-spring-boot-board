package io.study.springbootboard.web.exception;

import io.study.springbootboard.web.base.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static io.study.springbootboard.web.exception.ApiStatusCode.BAD_REQUEST;

@RestControllerAdvice
public class ExceptionEndpoint {



   @ExceptionHandler(MethodArgumentNotValidException.class)
   @ResponseStatus(code = HttpStatus.BAD_REQUEST)
   public BaseResponse badRequest(MethodArgumentNotValidException exception) {

      BindingResult bindingResult = exception.getBindingResult();
      String fieldError = bindingResult
              .getFieldErrors()
              .stream()
              .map(e -> String.format("[ field: %s , message: %s, input: %s]", e.getField(), e.getDefaultMessage(), e.getRejectedValue()))
              .collect(Collectors.joining(","));

      return new BaseResponse(BAD_REQUEST.getCode(), String.format("%s - %s", BAD_REQUEST.getDescription(), fieldError));

   }


}
