package io.study.springbootboard.api.test.domain.repository;

import io.study.springbootboard.api.test.domain.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TestRepository {

   TestEntity save(TestEntity test);
}

@Repository
interface JpaTestRepository extends TestRepository, JpaRepository<TestEntity, Long> {

}