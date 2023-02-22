package io.study.springbootboard.api.user.ui;

import io.study.springbootboard.api.user.application.request.UserSignupRequest;
import io.study.springbootboard.web.base.BaseResource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource extends BaseResource {

   @PostMapping(value = "/users", headers = X_API_VERSION)
   public String signup(@Validated @RequestBody UserSignupRequest request) {


      return request.getEmail() + " :: " + request.getPassword();
   }
}
