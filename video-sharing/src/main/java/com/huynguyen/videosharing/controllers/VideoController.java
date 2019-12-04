package com.huynguyen.videosharing.controllers;

import com.huynguyen.videosharing.domain.dto.request.VideoSharingDTO;
import com.huynguyen.videosharing.domain.dto.response.VideoSharingResponseDTO;
import com.huynguyen.videosharing.domain.model.User;
import com.huynguyen.videosharing.domain.model.Video;
import com.huynguyen.videosharing.mapper.videos.VideoMapper;
import com.huynguyen.videosharing.services.VideoService;
import com.huynguyen.videosharing.utils.ResponseFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoController {

  private final VideoMapper mapper;
  private final VideoService service;

  @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {
      MediaType.APPLICATION_JSON_VALUE})
  @PreAuthorize("isAuthenticated()")
  public ResponseEntity<VideoSharingResponseDTO> create(
      @RequestBody VideoSharingDTO request, Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    Video model = mapper.transform(request);
    log.info("create videos with info {} and user {}", model, user);
    VideoSharingResponseDTO response = mapper
        .transform(service.create(model, user));
    return ResponseFactory.created(response);
  }


}
