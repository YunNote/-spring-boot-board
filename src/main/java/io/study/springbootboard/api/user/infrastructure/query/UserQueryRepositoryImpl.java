package io.study.springbootboard.api.user.infrastructure.query;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.study.springbootboard.api.user.domain.entity.User;
import io.study.springbootboard.api.user.domain.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static io.study.springbootboard.api.user.domain.entity.QUser.user;

@RequiredArgsConstructor
@Repository
public class UserQueryRepositoryImpl implements UserQueryRepository {

   private final JPAQueryFactory queryFactory;

   @Override
   public Optional<User> findUsername(String email) {

      return Optional.ofNullable(
              queryFactory.selectFrom(user)
                      .where(
                              eqEmail(email)
                      )
                      .fetchOne()
      );
   }

   private BooleanExpression eqEmail(final String email) {
      if (Strings.isBlank(email)) {
         return null;
      }

      return user.email.eq(email);
   }
}
