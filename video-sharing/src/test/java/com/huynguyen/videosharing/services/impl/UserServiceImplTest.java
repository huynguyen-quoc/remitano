package com.huynguyen.videosharing.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.huynguyen.videosharing.domain.model.User;
import com.huynguyen.videosharing.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  @InjectMocks
  private UserServiceImpl service;

  @Mock
  private UserRepository userRepository;

  @Test
  void createShouldReturnCorrectUserAndCallRepositoryOneTimes() {
    User mockUser = mock(User.class);
    when(userRepository.save(any(User.class))).thenReturn(mockUser);
    when(mockUser.getId()).thenReturn(1L);

    User user = service.create(mockUser);

    assertThat(user.getId()).isEqualTo(1L);
    assertThat(user.getPassword()).isEqualTo(mockUser.getPassword());
    assertThat(user.getUsername()).isEqualTo(mockUser.getUsername());

    verify(userRepository, times(1)).save(any(User.class));


  }

  @Test
  void getShouldReturnCorrectUserAndCallRepositoryOneTimes() {
    User mockUser = mock(User.class);
    when(userRepository.findById(anyLong())).thenReturn(Optional.of(mockUser));
    when(mockUser.getId()).thenReturn(1L);

    Optional<User> optionalUser = service.get(1L);

    assertThat(optionalUser).isNotEmpty();
    User user = optionalUser.get();
    assertThat(user.getId()).isEqualTo(mockUser.getId());
    assertThat(user.getPassword()).isEqualTo(mockUser.getPassword());
    assertThat(user.getUsername()).isEqualTo(mockUser.getUsername());

    verify(userRepository, times(1)).findById(anyLong());
  }

  @Test
  void loadUserByUsernameShouldReturnCorrectUserAndCallRepositoryOneTimes() {

    User mockUser = mock(User.class);
    when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(mockUser));

    UserDetails user = service.loadUserByUsername("username");

    assertThat(user).isNotNull();
    assertThat(user.getPassword()).isEqualTo(mockUser.getPassword());
    assertThat(user.getUsername()).isEqualTo(mockUser.getUsername());

    verify(userRepository, times(1)).findByEmail(anyString());
  }

  @Test
  void loadUserByUsernameShouldThrowExceptionAndCallRepositoryOneTimesWhenUserNameNotFound() {

    when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

    assertThatThrownBy(() -> {
      service.loadUserByUsername("username");
    }).isInstanceOf(UsernameNotFoundException.class);

    verify(userRepository, times(1)).findByEmail(anyString());
  }
}