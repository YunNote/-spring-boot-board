package io.study.springbootboard.api.test.domain.repository;

import io.study.springbootboard.api.test.domain.entity.TestEntity;

import java.util.List;

public interface TestQueryRepository {

   List<TestEntity> findAll();
}
