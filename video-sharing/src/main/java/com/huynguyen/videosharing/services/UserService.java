package com.huynguyen.videosharing.services;

import com.huynguyen.videosharing.domain.model.User;
import java.util.Optional;

public interface UserService {

  User create(User user);

  Optional<User> get(Long id);
}
