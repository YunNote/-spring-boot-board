package io.study.springbootboard.api.user.domain.entity;

import io.study.springbootboard.web.base.entity.BaseEntity;
import io.study.springbootboard.web.base.types.AuthenticationType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Table(name = "user_authentications")
@Entity
public class UserAuthentication extends BaseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Enumerated
   private AuthenticationType type;

}
