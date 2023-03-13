package io.study.springbootboard.api.user.application.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResetPasswordRequest {
   private String email;
}
