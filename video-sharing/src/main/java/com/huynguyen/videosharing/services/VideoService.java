package com.huynguyen.videosharing.services;

import com.huynguyen.videosharing.domain.model.User;
import com.huynguyen.videosharing.domain.model.Video;

public interface VideoService {
  public Video create(Video video, User user);
}
