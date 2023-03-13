package io.study.springbootboard.web.event.mail;

import io.study.springbootboard.web.configuration.mail.MailService;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;


@Slf4j
@Component
@RequiredArgsConstructor
public class MailEventListener {

   private final MailService mailService;

   @Async
   @TransactionalEventListener
   public void signupEventListener(SignupEvent signupEvent)
      throws MessagingException, UnsupportedEncodingException {

      String[] to = Arrays.asList(signupEvent.getEmail()).toArray(String[]::new);
      mailService.sendMail(to, "회원가입 축하축하", "헬로우 축축 가입 축축");
   }
}
