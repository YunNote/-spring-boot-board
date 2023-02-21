package io.study.springbootboard.api.test.ui;


import io.study.springbootboard.api.test.application.TestUsecase;
import io.study.springbootboard.web.base.BaseResource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestResource extends BaseResource {

   private final TestUsecase testUsecase;


   @GetMapping("/all")
   public void findAllTest() {
      testUsecase.findAll();
   }

   @GetMapping("/test")
   public String test() {
      testUsecase.saveTest();

      return "Sample Save";
   }
}
