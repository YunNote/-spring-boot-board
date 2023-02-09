package io.study.springbootboard.api.test.domain;

import io.study.springbootboard.api.test.domain.entity.TestEntity;
import io.study.springbootboard.api.test.domain.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestDataProviderImpl implements TestDataProvider{

   private final TestRepository testRepository;

   @Override
   public void save() {
      testRepository.save(TestEntity.sample());
   }
}
