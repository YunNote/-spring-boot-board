package io.study.springbootboard.api.user.ui;

import io.study.springbootboard.api.user.application.UserSigninUsecase;
import io.study.springbootboard.api.user.application.UserSignupUsecase;
import io.study.springbootboard.api.user.application.wrapper.UserSigninWrapper;
import io.study.springbootboard.api.user.application.wrapper.UserSignupWrapper;
import io.study.springbootboard.api.user.ui.request.UserSigninRequest;
import io.study.springbootboard.api.user.ui.request.UserSignupRequest;
import io.study.springbootboard.web.base.BaseResource;
import io.study.springbootboard.web.base.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static io.study.springbootboard.web.exception.ApiStatusCode.CREATED;

@RequiredArgsConstructor
@RestController
public class UserResource extends BaseResource {

   private final UserSignupUsecase userSignupUsecase;
   private final UserSigninUsecase userSigninUsecase;

   @PostMapping(value = "/users", headers = X_API_VERSION)
   public BaseResponse signup(@Validated @RequestBody UserSignupRequest request) {

      userSignupUsecase.basicSignup(UserSignupWrapper.from(request));
      return new BaseResponse(CREATED.getCode(), CREATED.getDescription());
   }

   @PostMapping(value = "/users/signin" , headers = X_API_VERSION)
   public BaseResponse signin(@Validated @RequestBody UserSigninRequest request) {

      userSigninUsecase.login(UserSigninWrapper.from(request));

      return null;
   }
}