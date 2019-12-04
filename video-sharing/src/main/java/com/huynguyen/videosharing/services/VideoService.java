package com.huynguyen.videosharing.services;

import com.huynguyen.videosharing.domain.model.User;
import com.huynguyen.videosharing.domain.model.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VideoService {
  public Video create(Video video, User user);

  Page<Video> findAll(Pageable pageable);

}
