package io.study.springbootboard.api.user.domain.entity;


import io.study.springbootboard.api.user.domain.validate.UserValidator;
import io.study.springbootboard.web.base.entity.BaseEntity;
import io.study.springbootboard.web.base.types.AuthorityType;
import io.study.springbootboard.web.base.types.UserStatusType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(unique = true)
   private String email;
   private String password;

   @ElementCollection
   @CollectionTable(
      name = "user_authorities",
      joinColumns = @JoinColumn(name = "id")
   )
   @Enumerated(EnumType.STRING)
   private Set<AuthorityType> authority = new HashSet<>(Arrays.asList(AuthorityType.ROLE_USER));

   @Enumerated(EnumType.STRING)
   private UserStatusType userStatus = UserStatusType.ACTIVE;

   @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
   private Set<UserAuthentication> authentications = new HashSet<>();

   public User(String email, String password) {
      this.email = email;
      this.password = password;
   }

   public static User generatedBasicUser(String email, String encryptPassword) {

      return new User(email, encryptPassword);
   }

   public void loginValidate(UserValidator userValidator, String plainPassword) {
      userValidator.loginValidate(this, plainPassword);
   }

   public void resetPassword(String encryptPassword) {
      this.password = encryptPassword;
   }
}
