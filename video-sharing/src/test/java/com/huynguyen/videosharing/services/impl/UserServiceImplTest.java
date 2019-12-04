package com.huynguyen.videosharing.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.huynguyen.videosharing.domain.model.User;
import com.huynguyen.videosharing.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @InjectMocks
  private UserServiceImpl service;

  @Mock
  private UserRepository userRepository;

  @Test
  void createShouldReturnCorrectUserAndCallRepositoryATimes() {
    User mockUser = mock(User.class);
    when(userRepository.save(any(User.class))).thenReturn(mockUser);
    when(mockUser.getId()).thenReturn(1L);

    User user = service.create(mockUser);

    assertThat(user.getId()).isEqualTo(1L);
    assertThat(user.getEmail()).isEqualTo(mockUser.getEmail());
    assertThat(user.getPassword()).isEqualTo(mockUser.getPassword());
    assertThat(user.getUsername()).isEqualTo(mockUser.getUsername());

    verify(userRepository, times(1)).save(any(User.class));


  }
}