package io.study.springbootboard.api.user.application.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UserSignupRequest {

   @NotBlank(message = "required email")
   private String email;

   @NotBlank(message = "required password")
   private String password;
   private String nickname;
}
