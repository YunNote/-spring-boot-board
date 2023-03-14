package io.study.springbootboard.api.user.domain.event;

import lombok.Getter;

@Getter
public class ResetPasswordEvent {

   private String email;
   private String plainPassword;

   private ResetPasswordEvent(String email, String plainPassword) {
      this.email = email;
      this.plainPassword = plainPassword;
   }

   public static ResetPasswordEvent of(String email, String plainPassword) {
      return new ResetPasswordEvent(email, plainPassword);
   }
}
