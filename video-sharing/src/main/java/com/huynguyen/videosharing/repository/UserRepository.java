package com.huynguyen.videosharing.repository;

import com.huynguyen.videosharing.domain.model.User;
import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
  Optional<User> findByUsername(String username);
}
