package io.study.springbootboard.api.test.application;

import io.study.springbootboard.api.test.domain.TestDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestUsecase {

   private final TestDataProvider testDataProvider;

   public void saveTest() {

      testDataProvider.save();
   }
}
