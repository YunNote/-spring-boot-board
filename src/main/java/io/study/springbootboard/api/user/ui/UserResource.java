package io.study.springbootboard.api.user.ui;

import io.study.springbootboard.api.user.application.UserSigninUsecase;
import io.study.springbootboard.api.user.application.UserSignupUsecase;
import io.study.springbootboard.api.user.application.request.UserSigninRequest;
import io.study.springbootboard.api.user.application.request.UserSignupRequest;
import io.study.springbootboard.api.user.domain.wrapper.UserSigninWrapper;
import io.study.springbootboard.api.user.domain.wrapper.UserSignupWrapper;
import io.study.springbootboard.web.base.BaseResource;
import io.study.springbootboard.web.base.response.BaseResponse;
import io.study.springbootboard.web.base.response.DataResponse;
import io.study.springbootboard.web.configuration.jwt.Jwt;
import io.study.springbootboard.web.configuration.mail.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

import java.io.UnsupportedEncodingException;

import static io.study.springbootboard.web.exception.ApiStatusCode.CREATED;
import static io.study.springbootboard.web.exception.ApiStatusCode.OK;

@RequiredArgsConstructor
@RestController
public class UserResource extends BaseResource {

   private final UserSignupUsecase userSignupUsecase;
   private final UserSigninUsecase userSigninUsecase;

   private final MailService mailService;

   @GetMapping("/test")
   public String test () throws MessagingException, UnsupportedEncodingException {

      mailService.sendMail();
      return "Aaa";
   }

   @PostMapping(value = "/users", headers = X_API_VERSION)
   public BaseResponse signup(@Validated @RequestBody UserSignupRequest request) {

      userSignupUsecase.basicSignup(UserSignupWrapper.from(request));
      return new BaseResponse(CREATED.getCode(), CREATED.getDescription());
   }

   @PostMapping(value = "/users/signin" , headers = X_API_VERSION)
   public BaseResponse signin(@Validated @RequestBody UserSigninRequest request) {

      Jwt login = userSigninUsecase.login(UserSigninWrapper.from(request));

      return new DataResponse<>(OK.getCode(), OK.getDescription(), login);
   }
}