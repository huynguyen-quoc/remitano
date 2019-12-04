package com.huynguyen.videosharing.services.impl;

import com.huynguyen.videosharing.domain.model.User;
import com.huynguyen.videosharing.repository.UserRepository;
import com.huynguyen.videosharing.services.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

  private final UserRepository repository;

  @Override
  public User create(User user) {
    return repository.save(user);
  }

  @Override
  public Optional<User> get(Long id) {
    return repository.findById(id);
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return repository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

  }
}
