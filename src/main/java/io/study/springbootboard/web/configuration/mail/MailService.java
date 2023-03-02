package io.study.springbootboard.web.configuration.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Component
@RequiredArgsConstructor
public class MailService {

   private final JavaMailSender javaMailSender;

   public void sendMail() throws MessagingException, UnsupportedEncodingException {

      MimeMessage message = javaMailSender.createMimeMessage();

      MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

      String[] receivers = {"zzdd1558@naver.com"};

      messageHelper.setTo(receivers);
      messageHelper.setSubject(" 테스트 이메일 제목 ");
      messageHelper.setFrom(new InternetAddress("noreply.petnote@gmail.com", "hello", "UTF-8"));

      String content = "테스트 <br> <b>테스트</b>";

      messageHelper.setText(content, true);

      javaMailSender.send(message);
   }
}
