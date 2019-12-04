package com.huynguyen.videosharing.repository;

import com.huynguyen.videosharing.domain.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

}
