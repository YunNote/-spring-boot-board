package io.study.springbootboard.api.user.domain.event;

import io.study.springbootboard.web.configuration.mail.MailService;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;


@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventListener {

   private final MailService mailService;

   /*
    * 회원가입 이벤트 이메일 발송
    */
   @Async
   @TransactionalEventListener
   public void signupEventListener(SignupEvent event)
      throws MessagingException, UnsupportedEncodingException {

      String[] to = mailTo(event.getEmail());
      mailService.sendMail(to, "회원가입 축하축하", "헬로우 축축 가입 축축");
   }

   /*
    * 비밀번호 초기화 이벤트 이메일 발송
    */
   @Async
   @TransactionalEventListener
   public void resetPasswordEventListener(ResetPasswordEvent event)
      throws MessagingException, UnsupportedEncodingException {
      String[] to = mailTo(event.getEmail());
      mailService.sendMail(to, "비밀번호 초기화", "임시 비밀번호 : " + event.getPlainPassword());
   }

   private String[] mailTo(String... to) {
      return to;
   }
}
