package io.study.springbootboard.api.user.domain.repository;

import io.study.springbootboard.api.user.domain.entity.User;

import java.util.Optional;

public interface UserQueryRepository {
   Optional<User> findUsername(String email);

   boolean isExistEmail(String email);
}
