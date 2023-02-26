package io.study.springbootboard.api.user.domain.repository;

import io.study.springbootboard.api.user.domain.entity.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

   User save(User user);
}