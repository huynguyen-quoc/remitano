package com.huynguyen.videosharing.services.impl;

import com.huynguyen.videosharing.domain.model.User;
import com.huynguyen.videosharing.repository.UserRepository;
import com.huynguyen.videosharing.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository repository;

  @Override
  public User create(User user) {
    return repository.save(user);
  }
}
