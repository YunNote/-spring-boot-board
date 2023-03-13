package io.study.springbootboard.api.user.domain;

import io.study.springbootboard.api.user.domain.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class SecurityUserDetails implements UserDetails {

   private User user;

   public SecurityUserDetails(User user) {
      this.user = user;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {

      return user.getAuthority()
         .stream()
         .map(v -> new SimpleGrantedAuthority(v.name()))
         .collect(Collectors.toList());
   }

   @Override
   public String getPassword() {
      return user.getPassword();
   }

   @Override
   public String getUsername() {
      return user.getEmail();
   }


   // 계정이 만료되었는지 확인 true - 만료되지 않음
   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   // 계정이 잠겨있는지 확인 true - 잠겨있지 않음.
   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   // 계정의 패스워드가 만료되었는지 확인 true - 만료되지 않음.
   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   // 계정이 사용가능한지 확인 true - 사용 가능.
   @Override
   public boolean isEnabled() {
      return true;
   }
}
