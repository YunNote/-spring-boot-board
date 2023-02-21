package io.study.springbootboard.api.user.domain;

import io.study.springbootboard.api.user.domain.entity.User;
import io.study.springbootboard.api.user.domain.repository.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class SecurityUserDetailsDataprovider implements UserDetailsService {

   private final UserQueryRepository userQueryRepository;

   @Transactional(readOnly = true)
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

      User user = userQueryRepository.findUsername(username)
              .orElseThrow(() -> new UsernameNotFoundException(username + "없다.."));

      return new SecurityUserDetails(user);
   }
}
