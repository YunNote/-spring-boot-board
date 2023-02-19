package io.study.springbootboard.api.test.domain.entity;

import io.study.springbootboard.web.base.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TestEntity extends BaseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String test;

   public TestEntity(String test) {
      this.test = test;
   }

   public static TestEntity sample() {

      return new TestEntity("Hello");
   }
}
