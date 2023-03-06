package io.study.springbootboard.api.user.infrastructure.query;

import static io.study.springbootboard.api.user.domain.entity.QUser.user;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.study.springbootboard.api.user.domain.entity.User;
import io.study.springbootboard.api.user.domain.repository.UserQueryRepository;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Repository;

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

   @Override
   public boolean isExistEmail(String email) {
      return Objects.nonNull(
         queryFactory
            .selectFrom(user)
            .where(eqEmail(email))
            .fetchFirst()
      );
   }

   private BooleanExpression eqEmail(final String email) {
      if (Strings.isBlank(email)) {
         return null;
      }

      return user.email.eq(email);
   }
}
