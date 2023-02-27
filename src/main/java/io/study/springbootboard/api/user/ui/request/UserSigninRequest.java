package io.study.springbootboard.api.user.ui.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class UserSigninRequest {

   @NotBlank(message = "required email")
   private String email;

   @NotBlank(message = "required password")
   private String password;
}
