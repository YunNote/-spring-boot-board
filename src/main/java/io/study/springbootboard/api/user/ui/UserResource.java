package io.study.springbootboard.api.user.ui;

import static io.study.springbootboard.web.exception.ApiStatusCode.CREATED;
import static io.study.springbootboard.web.exception.ApiStatusCode.OK;
import static io.study.springbootboard.web.exception.ApiStatusCode.USER_PASSWORD_RESET;

import io.study.springbootboard.api.user.application.ResetPasswordUsecase;
import io.study.springbootboard.api.user.application.UserSigninUsecase;
import io.study.springbootboard.api.user.application.UserSignupUsecase;
import io.study.springbootboard.api.user.application.request.UserResetPasswordRequest;
import io.study.springbootboard.api.user.application.request.UserSigninRequest;
import io.study.springbootboard.api.user.application.request.UserSignupRequest;
import io.study.springbootboard.api.user.domain.wrapper.UserSigninWrapper;
import io.study.springbootboard.api.user.domain.wrapper.UserSignupWrapper;
import io.study.springbootboard.web.base.BaseResource;
import io.study.springbootboard.web.base.response.BaseResponse;
import io.study.springbootboard.web.base.response.DataResponse;
import io.study.springbootboard.web.base.types.AuthenticationType;
import io.study.springbootboard.web.configuration.jwt.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserResource extends BaseResource {

   private final UserSignupUsecase userSignupUsecase;
   private final UserSigninUsecase userSigninUsecase;
   private final ResetPasswordUsecase resetPasswordUsecase;


   /**
    * 유저 회원가입
    *
    * @param request - 회원 가입 정보
    */
   @PostMapping(value = "/users", headers = X_API_VERSION)
   public BaseResponse signup(@Validated @RequestBody UserSignupRequest request) {

      userSignupUsecase.basicSignup(UserSignupWrapper.from(request));
      return new BaseResponse(CREATED.getCode(), CREATED.getDescription());
   }

   /**
    * 유저 로그인
    *
    * @param request - 로그인 정보
    */
   @PostMapping(value = "/users/signin" , headers = X_API_VERSION)
   public BaseResponse signin(@Validated @RequestBody UserSigninRequest request) {

      Jwt login = userSigninUsecase.login(UserSigninWrapper.from(request));

      return new DataResponse<>(OK.getCode(), OK.getDescription(), login);
   }

   /**
    * 이메일을 통한 비밀번호 찾기.
    *
    * @param request - 로그인 정보
    */
   @PostMapping(value = "/users/reset-password" , headers = X_API_VERSION)
   public BaseResponse resetPassword(@Validated @RequestBody UserResetPasswordRequest request) {

      resetPasswordUsecase.reset(request);

      return new BaseResponse(USER_PASSWORD_RESET.getCode(), USER_PASSWORD_RESET.getDescription());
   }

   @PostMapping(value = "/users/authentication/{authenticationType}", headers = X_API_VERSION)
   public BaseResponse authenticationRegistered(
      @PathVariable(value = "authenticationType") AuthenticationType authenticationType,
      @AuthenticationPrincipal UserDetails user) {

      return new BaseResponse(CREATED.getCode(), CREATED.getDescription());
   }
}