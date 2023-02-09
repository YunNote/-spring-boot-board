package io.study.springbootboard.api.test.application;

import io.study.springbootboard.api.test.domain.TestDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestUsecase {

   private final TestDataProvider testDataProvider;

   public void saveTest() {

      testDataProvider.save();
   }

   @Transactional(readOnly = true)
   public void findAll() {
      testDataProvider.findAll();
   }
}
