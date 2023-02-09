package io.study.springbootboard.api.test.domain;

import io.study.springbootboard.api.test.domain.entity.TestEntity;
import io.study.springbootboard.api.test.domain.repository.TestQueryRepository;
import io.study.springbootboard.api.test.domain.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestDataProviderImpl implements TestDataProvider {

   private final TestRepository testRepository;
   private final TestQueryRepository testQueryRepository;

   @Override
   public void save() {
      testRepository.save(TestEntity.sample());
   }

   @Override
   public void findAll() {
      List<TestEntity> all = testQueryRepository.findAll();
      all.stream()
              .forEach(System.out::println);
   }
}
