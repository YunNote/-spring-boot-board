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

   public void sendMail(String[] to, String title, String content)
      throws MessagingException, UnsupportedEncodingException {

      MimeMessage message = javaMailSender.createMimeMessage();

      MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

      messageHelper.setTo(to);
      messageHelper.setSubject(title);
      messageHelper.setFrom(new InternetAddress("noreply.yunnote@gmail.com", "yunnote", "UTF-8"));
      messageHelper.setText(content, true);

      javaMailSender.send(message);
   }
}
