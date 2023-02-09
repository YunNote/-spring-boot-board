package io.study.springbootboard.api.test.ui;


import io.study.springbootboard.api.test.application.TestUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/test")
public class TestResources {

   private final TestUsecase testUsecase;

   @GetMapping
   public String test() {
      testUsecase.saveTest();

      return "Sample Save";
   }
}
