package com.huynguyen.videosharing.services.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.huynguyen.videosharing.domain.model.User;
import com.huynguyen.videosharing.domain.model.Video;
import com.huynguyen.videosharing.repository.UserRepository;
import com.huynguyen.videosharing.repository.VideoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VideoServiceImplTest {

  @InjectMocks
  private VideoServiceImpl service;

  @Mock
  private VideoRepository videoRepository;

  @Test
  void createShouldReturnCorrectVideoAndCallRepositoryOneTimes() {
    Video mockVideo = mock(Video.class);
    User mockUser = mock(User.class);
    when(videoRepository.save(any(Video.class))).thenReturn(mockVideo);
    when(mockVideo.getId()).thenReturn(1L);

    Video video = service.create(mockVideo, mockUser);

    assertThat(video.getId()).isEqualTo(1L);
    assertThat(video.getDescription()).isEqualTo(mockVideo.getDescription());
    assertThat(video.getTitle()).isEqualTo(mockVideo.getDescription());
    assertThat(video.getUrl()).isEqualTo(mockVideo.getUrl());

    verify(videoRepository, times(1)).save(any(Video.class));


  }
}