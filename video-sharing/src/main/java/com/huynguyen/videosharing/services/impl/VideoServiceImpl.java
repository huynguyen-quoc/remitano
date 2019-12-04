package com.huynguyen.videosharing.services.impl;

import com.huynguyen.videosharing.domain.model.User;
import com.huynguyen.videosharing.domain.model.Video;
import com.huynguyen.videosharing.repository.VideoRepository;
import com.huynguyen.videosharing.services.VideoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoServiceImpl implements VideoService {

  private final VideoRepository repository;

  @Override
  public Video create(Video video, User user) {
    video.setUser(user);
    return repository.save(video);
  }
}
