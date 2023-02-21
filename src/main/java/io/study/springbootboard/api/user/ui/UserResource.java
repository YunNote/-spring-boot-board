package io.study.springbootboard.api.user.ui;

import io.study.springbootboard.web.base.BaseResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserResource extends BaseResource {

   @PostMapping(value = "/users", headers = X_API_VERSION)
   public String test() {
      return "Abc";
   }
}
