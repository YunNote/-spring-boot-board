package io.study.springbootboard.api.test.infrastructure.query;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.study.springbootboard.api.test.domain.entity.QTestEntity;
import io.study.springbootboard.api.test.domain.entity.TestEntity;
import io.study.springbootboard.api.test.domain.repository.TestQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class TestQueryRepositoryImpl implements TestQueryRepository {

   private final JPAQueryFactory queryFactory;

   @Override
   public List<TestEntity> findAll() {
      QTestEntity qTestEntity = QTestEntity.testEntity;
      return queryFactory.selectFrom(qTestEntity)
              .fetch();
   }
}
